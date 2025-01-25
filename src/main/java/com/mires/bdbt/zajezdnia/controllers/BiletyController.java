package com.mires.bdbt.zajezdnia.controllers;


import com.mires.bdbt.zajezdnia.entities.Bilet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/bilety")
@SessionAttributes("pasazer")
public class BiletyController {
    @GetMapping
    public String tickets(final Model model, HttpServletRequest request) {
        model.addAttribute("bilet", new Bilet());
        model.addAttribute("request", request);
        return "tickets/Tickets";
    }
}