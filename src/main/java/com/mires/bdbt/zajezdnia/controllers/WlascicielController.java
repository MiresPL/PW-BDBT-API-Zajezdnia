package com.mires.bdbt.zajezdnia.controllers;


import com.mires.bdbt.zajezdnia.entities.Wlasciciel;
import com.mires.bdbt.zajezdnia.services.KierowcyService;
import com.mires.bdbt.zajezdnia.services.WlascicielService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/admin")
@SessionAttributes("admin")
public class WlascicielController {
    private final WlascicielService wlascicielService;
    private KierowcyService kierowcyService;

    public WlascicielController(WlascicielService wlascicielService, KierowcyService kierowcyService) {
        this.wlascicielService = wlascicielService;
        this.kierowcyService = kierowcyService;
    }

    @GetMapping
    public String admin(final Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Wlasciciel wlasciciel = wlascicielService.findByLogin(username);
        model.addAttribute("admin", wlasciciel);
        model.addAttribute("kierowcy", kierowcyService.getAllKierowcy());

        return "admin/Admin";
    }

    @PostMapping("/loginRequest")
    public String loginRequest() {
        return "redirect:/admin"; // On success, redirect to the admin dashboard.
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Niepoprawne dane logowania.");
        }
        return "admin/Login";
    }
}