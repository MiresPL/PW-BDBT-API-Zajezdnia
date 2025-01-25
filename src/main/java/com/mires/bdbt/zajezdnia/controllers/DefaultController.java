package com.mires.bdbt.zajezdnia.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mires.bdbt.zajezdnia.entities.Pasazer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/")
@SessionAttributes("pasazer")
public class DefaultController {
    @GetMapping("")
    public String home(final Model model, HttpSession session, HttpServletRequest request) {
        final Object pasazerObject = model.getAttribute("pasazer");
        System.out.println(pasazerObject);
        System.out.println(pasazerObject == null);
        if (pasazerObject != null) {
            Pasazer pasazer = new ObjectMapper().convertValue(pasazerObject, Pasazer.class);
            model.addAttribute("pasazer", pasazer);
            session.setAttribute("pasazer", pasazer);
        }
        model.addAttribute("request", request);
        return "home/Home";
    }
}
