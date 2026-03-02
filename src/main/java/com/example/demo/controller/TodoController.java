package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return "todo/new";
    }

    @GetMapping("/confirm")
    public String showConfirm() {
        return "todo/confirm";
    }

    @GetMapping("/complete")
    public String showComplete() {
        return "todo/complete";
    }

    public record TodoItemView(Long id, String title, String status) {
    }
}
