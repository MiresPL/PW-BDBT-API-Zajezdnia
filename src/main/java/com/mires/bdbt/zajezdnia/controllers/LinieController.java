package com.mires.bdbt.zajezdnia.controllers;

import com.mires.bdbt.zajezdnia.entities.Linia;
import com.mires.bdbt.zajezdnia.entities.LiniaPrzystanki;
import com.mires.bdbt.zajezdnia.entities.RozkladJazdyEntity;
import com.mires.bdbt.zajezdnia.services.LiniePrzystankiService;
import com.mires.bdbt.zajezdnia.services.LinieService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/linie")
public class LinieController {
    private final LinieService linieService;
    private final LiniePrzystankiService liniePrzystankiService;

    public LinieController(LinieService linieService, LiniePrzystankiService liniePrzystankiService) {
        this.linieService = linieService;
        this.liniePrzystankiService = liniePrzystankiService;
    }

    @GetMapping
    public String linie(final Model model, final HttpServletRequest request) {
        model.addAttribute("linie", linieService.findAll());
        model.addAttribute("request", request);
        return "linie/Linie";
    }

    @GetMapping("/list/{id}")
    public String linieList(@PathVariable Long id, final Model model, final HttpServletRequest request) {
        final Linia linia = linieService.findByIdLinii(id);
        final List<LiniaPrzystanki> przystanki = liniePrzystankiService.findByLiniaId(id);

        final List<RozkladJazdyEntity> rozkladJazdy = new ArrayList<>();

        for (LiniaPrzystanki przystanek : przystanki) {
            rozkladJazdy.add(new RozkladJazdyEntity(linia, przystanek.getPrzystanek(), przystanek));
        }

        //Sorty rozkladJazdy by idLini and by kolejnoscNaTrasie
        rozkladJazdy.sort(Comparator.comparing((RozkladJazdyEntity r) -> r.getLiniaPrzystanki().getLinia().getIdLinii())
                .thenComparingLong(r -> r.getLiniaPrzystanki().getKolejnoscNaTrasie()));
        model.addAttribute("rozkladJazdy", rozkladJazdy);
        model.addAttribute("request", request);
        model.addAttribute("linia", linia);
        return "linie/RozkladJazdy";
    }

}
