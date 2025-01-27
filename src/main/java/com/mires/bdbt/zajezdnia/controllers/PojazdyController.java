package com.mires.bdbt.zajezdnia.controllers;

import com.mires.bdbt.zajezdnia.entities.Pojazd;
import com.mires.bdbt.zajezdnia.services.PojazdyService;
import com.mires.bdbt.zajezdnia.services.ZajezdnieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pojazdy")
public class PojazdyController {
    private final PojazdyService pojazdyService;
    private final ZajezdnieService zajezdnieService;
    public PojazdyController(PojazdyService pojazdyService, ZajezdnieService zajezdnieService) {
        this.pojazdyService = pojazdyService;
        this.zajezdnieService = zajezdnieService;
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Pojazd pojazd = pojazdyService.findByIdPojazdu(id);
        model.addAttribute("pojazd", pojazd);
        model.addAttribute("zajezdnie", zajezdnieService.findAll());
        return "pojazdy/Update";
    }

    @GetMapping("/addNew")
    public String showNewPojazdForm(Model model) {
        model.addAttribute("pojazd", new Pojazd());
        model.addAttribute("zajezdnie", zajezdnieService.findAll());
        return "pojazdy/AddNew";
    }

    @PostMapping("/add")
    public String saveKierowca(@ModelAttribute("pojazd") Pojazd pojazd) {
        final Pojazd existingPojazd = pojazdyService.findByIdPojazdu(pojazd.getIdPojazdu());

        if (existingPojazd != null) {
            pojazd.setIdPojazdu(existingPojazd.getIdPojazdu());
        } else {
            pojazd.setIdPojazdu(pojazdyService.getNextId());
        }
        pojazdyService.save(pojazd);
        return "redirect:/admin";
    }

    @GetMapping("/remove/{id}")
    public String removeKierowca(@PathVariable Long id) {
        pojazdyService.remove(id);
        return "redirect:/admin";
    }
}
