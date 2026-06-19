package com.anonchat.anonymousmessenger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PagesController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/chat/{dialogId}")
    public String chat(@PathVariable String dialogId, Model model) {
        model.addAttribute("dialogId", dialogId);
        return "chat";
    }
}
