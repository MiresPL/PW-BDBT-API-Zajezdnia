package com.mires.bdbt.zajezdnia.controllers;


import com.mires.bdbt.zajezdnia.entities.Adresy;
import com.mires.bdbt.zajezdnia.entities.Kierowca;
import com.mires.bdbt.zajezdnia.entities.KierowcaAdresWrapper;
import com.mires.bdbt.zajezdnia.services.AdresyService;
import com.mires.bdbt.zajezdnia.services.KierowcyService;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/kierowcy")
public class KierowcyController {
    private final KierowcyService kierowcyService;
    private final AdresyService adresyService;

    public KierowcyController(KierowcyService kierowcyService, AdresyService adresyService) {
        this.kierowcyService = kierowcyService;
        this.adresyService = adresyService;
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Kierowca kierowca = kierowcyService.findById(id);

        Hibernate.initialize(kierowca.getAdres());

        System.out.println(kierowca.getAdres().getKraj());
        KierowcaAdresWrapper kierowcaAdresWrapper =  new KierowcaAdresWrapper(kierowca, kierowca.getAdres());
        model.addAttribute("kierowcaAdresWrapper", kierowcaAdresWrapper);
        return "kierowcy/Update";
    }

    @GetMapping("/addNew")
    public String showNewPracownikForm(Model model) {
        KierowcaAdresWrapper kierowcaAdresWrapper =  new KierowcaAdresWrapper(new Kierowca(), new Adresy());
        model.addAttribute("kierowcaAdresWrapper", kierowcaAdresWrapper);
        return "kierowcy/AddNew";
    }

    @PostMapping("/add")
    public String saveKierowca(@ModelAttribute("kierowcaAdresWrapper") KierowcaAdresWrapper kierowcaAdresWrapper) {
        Adresy adresy = kierowcaAdresWrapper.getAdresy();
        Kierowca kierowca = kierowcaAdresWrapper.getKierowca();

        if (adresy != null) {
            if (adresy.getIdAdresu() != null) {
                Adresy existingAdres = adresyService.findById(adresy.getIdAdresu());
                for (int i = 0; i < 10; i++) System.out.println("ADRESS");

                if (existingAdres != null) {
                    existingAdres.setKraj(adresy.getKraj());
                    existingAdres.setMiasto(adresy.getMiasto());
                    existingAdres.setUlica(adresy.getUlica());
                    existingAdres.setNumerBudynku(adresy.getNumerBudynku());
                    existingAdres.setNumerLokalu(adresy.getNumerLokalu());
                    existingAdres.setKodPocztowy(adresy.getKodPocztowy());

                    adresyService.save(existingAdres);
                    kierowca.setAdres(existingAdres);
                } else {
                    adresy.setIdAdresu(adresyService.getNextId());
                    adresyService.save(adresy);
                    kierowca.setAdres(adresy);
                }
            } else {
                adresy.setIdAdresu(adresyService.getNextId());
                adresyService.save(adresy);
                kierowca.setIdKierowcy(kierowcyService.getNextId());
                kierowca.setAdres(adresy);
            }
        }

        kierowcyService.save(kierowca);
        return "redirect:/admin";
    }

    @GetMapping("/remove/{id}")
    public String removeKierowca(@PathVariable Long id) {
        final Kierowca kierowca = kierowcyService.findById(id);
        final Long adresId = kierowca.getAdres().getIdAdresu();
        kierowcyService.remove(id);
        adresyService.remove(adresId);
        return "redirect:/admin";
    }
}
