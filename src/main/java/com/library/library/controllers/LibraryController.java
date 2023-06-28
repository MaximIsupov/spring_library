package com.library.library.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LibraryController {

    @GetMapping("/library")
    public String library(Model model) {
        return "library";
    }

}
