package com.mires.bdbt.zajezdnia.services;


import com.mires.bdbt.zajezdnia.entities.Adresy;
import com.mires.bdbt.zajezdnia.repositories.AdresyRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AdresyService {
    private final AdresyRepository adresyRepository;

    public AdresyService(AdresyRepository adresyRepository) {
        this.adresyRepository = adresyRepository;
    }

    public List<Adresy> findAll() {
        return adresyRepository.findAll();
    }

    public Long getNextId() {
        return adresyRepository.findAll().stream().mapToLong(Adresy::getIdAdresu).max().orElse(0) + 1;
    }

    public Adresy findById(Long id) {
        return adresyRepository.findById(id).orElse(null);
    }

    public Adresy save(Adresy adres) {
        return adresyRepository.save(adres);
    }

    public void remove(Long id) {
        adresyRepository.deleteById(id);
    }

}
