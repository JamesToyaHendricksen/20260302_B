package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @Value("${hello.message}")
    private String helloMessage;

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("message", helloMessage);
        return "hello";
    }
}
