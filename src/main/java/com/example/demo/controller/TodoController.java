package com.example.demo.controller;

import com.example.demo.model.Todo;
import com.example.demo.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping({"", "/"})
    public String list(Model model) {
        model.addAttribute("todos", todoService.findAll());
        return "todo/list";
    }

    @GetMapping("/new")
    public String showNewForm() {
        return "todo/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("todo", todoService.findById(id));
        return "todo/edit";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id,
                         @RequestParam("title") String title,
                         RedirectAttributes redirectAttributes) {
        Todo current = todoService.findById(id);
        if (current == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "譖ｴ譁ｰ縺ｫ螟ｱ謨励＠縺ｾ縺励◆");
            return "redirect:/todo";
        }

        Todo todo = new Todo();
        todo.setId(id);
        todo.setTitle(title);
        todo.setCompleted(current.getCompleted());

        int updatedCount = todoService.update(todo);
        if (updatedCount > 0) {
            redirectAttributes.addFlashAttribute("successMessage", "譖ｴ譁ｰ縺悟ｮ御ｺ・＠縺ｾ縺励◆");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "譖ｴ譁ｰ縺ｫ螟ｱ謨励＠縺ｾ縺励◆");
        }
        return "redirect:/todo";
    }

    @PostMapping("/confirm")
    public String confirm(@RequestParam("title") String title, Model model) {
        model.addAttribute("title", title);
        return "todo/confirm";
    }

    @PostMapping("/complete")
    public String complete(@RequestParam("title") String title) {
        Todo todo = new Todo();
        todo.setTitle(title);
        todo.setCompleted(false);
        todoService.insert(todo);
        return "redirect:/todo";
    }

    @PostMapping("/{id}/toggle")
    public String toggleCompleted(@PathVariable("id") Long id) {
        todoService.toggleCompleted(id);
        return "redirect:/todo";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            int deletedCount = todoService.deleteById(id);
            if (deletedCount > 0) {
                redirectAttributes.addFlashAttribute("successMessage", "ToDo繧貞炎髯､縺励∪縺励◆");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "蜑企勁縺ｫ螟ｱ謨励＠縺ｾ縺励◆");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "蜑企勁縺ｫ螟ｱ謨励＠縺ｾ縺励◆");
        }
        return "redirect:/todo";
    }
}
