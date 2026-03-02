package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/todo")
public class TodoController {

    @GetMapping({"", "/"})
    public String list(Model model) {
        List<TodoItemView> todoList = List.of(
                new TodoItemView(1L, "Spring Bootの学習", "未完了"),
                new TodoItemView(2L, "ToDo一覧画面の作成", "完了"),
                new TodoItemView(3L, "Controllerの実装", "未完了"));
        model.addAttribute("todoList", todoList);
        return "todo/list";
    }

    @GetMapping("/new")
    public String showNewForm() {
        return "todo/form";
    }

    @PostMapping("/confirm")
    public String confirm(@RequestParam("title") String title, Model model) {
        model.addAttribute("title", title);
        return "todo/confirm";
    }

    @PostMapping("/complete")
    public String complete(@RequestParam("title") String title, Model model) {
        model.addAttribute("title", title);
        return "todo/complete";
    }

    public record TodoItemView(Long id, String title, String status) {
    }
}
