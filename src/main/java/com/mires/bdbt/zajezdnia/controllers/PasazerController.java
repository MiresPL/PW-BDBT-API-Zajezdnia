package com.mires.bdbt.zajezdnia.controllers;


import com.mires.bdbt.zajezdnia.entities.Bilet;
import com.mires.bdbt.zajezdnia.entities.LoginCredentials;
import com.mires.bdbt.zajezdnia.entities.Pasazer;
import com.mires.bdbt.zajezdnia.services.BiletyService;
import com.mires.bdbt.zajezdnia.services.PasazerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/pasazer")
@SessionAttributes("pasazer")
public class PasazerController {
    private final PasazerService pasazerService;
    private final BiletyService biletyService;

    public PasazerController(PasazerService pasazerService, BiletyService biletyService) {
        this.pasazerService = pasazerService;
        this.biletyService = biletyService;
    }

    @PostMapping(path = "/loginRequest")
    public String loginRequest(@ModelAttribute LoginCredentials loginCredentials, final HttpSession session) {
        final Pasazer pasazer = pasazerService.login(loginCredentials.getLogin(),  loginCredentials.getPassword());

        if (pasazer != null) {
            session.setAttribute("pasazer", pasazer);
            return "redirect:/";
        } else return "redirect:/pasazer/login";


    }

    @GetMapping("/login")
    public String login(final Model model, HttpServletRequest request) {
        model.addAttribute("loginCredentials", new LoginCredentials());
        model.addAttribute("request", request);
        return "login/Login";
    }

    @GetMapping("/logout")
    public String logout(final Model model, final HttpSession session) {
        model.addAttribute("pasazer", null);
        session.setAttribute("pasazer", null);
        return "redirect:/";
    }

    @GetMapping("/profil")
    public String profil(final Model model, HttpSession session, HttpServletRequest request) {
        final Pasazer pasazer = (Pasazer) session.getAttribute("pasazer");
        model.addAttribute("pasazer", pasazer);
        model.addAttribute("tickets", biletyService.getTicketsByPasazer(pasazer.getIdPasazera()));
        model.addAttribute("request", request);
        return "profile/Profile";
    }

    @PostMapping("/add")
    public String savePasazer(@ModelAttribute("pasazer") Pasazer pasazer, final HttpSession session) {
        session.setAttribute("pasazer", pasazer);
        pasazerService.save(pasazer);
        return "redirect:/pasazer/profil";
    }

    @PostMapping("/buyTicket")
    public String buyTicket(@ModelAttribute Bilet bilet, final HttpSession session) {
        final Pasazer pasazer = (Pasazer) session.getAttribute("pasazer");
        bilet.setIdBiletu(biletyService.getNextId());
        bilet.setIdPasazera(pasazer.getIdPasazera());
        bilet.setDataZakupu(new Date(System.currentTimeMillis()));
        biletyService.save(bilet);
        return "redirect:/pasazer/profil";
    }
}