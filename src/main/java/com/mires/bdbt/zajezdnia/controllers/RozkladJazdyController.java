package com.mires.bdbt.zajezdnia.controllers;


import com.mires.bdbt.zajezdnia.services.LiniaService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rozklad_jazdy")
public class RozkladJazdyController {
    private final LiniaService liniaService;

    public RozkladJazdyController(LiniaService liniaService) {
        this.liniaService = liniaService;
    }

    @GetMapping
    public String listRozkladJazdy(final Model model, HttpServletRequest request) {
        model.addAttribute("rozklad", liniaService.getRozkladJazdy());
        model.addAttribute("request", request);
        return "rozklad_jazdy/RozkladJazdy";
    }
}