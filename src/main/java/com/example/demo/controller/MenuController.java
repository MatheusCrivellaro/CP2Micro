package com.grupoa.pastelaria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @GetMapping("/pastel")
    public String listaPastel() {
        return "redirect:/pastel";
    }

    @GetMapping("/massa")
    public String listaMassa() {
        return "redirect:/massa";
    }

    @GetMapping("/ingrediente")
    public String listaIngrediente() {
        return "redirect:/ingrediente";
    }

    @GetMapping
    public String menu() {
        return "menu";
    }

}
