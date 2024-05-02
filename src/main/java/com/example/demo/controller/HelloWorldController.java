package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/helloWorld")
public class HelloWorldController {

    @GetMapping
    public String test(Model model) {
        model.addAttribute("message", "Hello World");
        return "HelloWorld";
    }

}
