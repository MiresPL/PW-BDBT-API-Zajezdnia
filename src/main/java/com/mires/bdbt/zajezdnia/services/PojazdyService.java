package com.mires.bdbt.zajezdnia.services;

import com.mires.bdbt.zajezdnia.entities.Kierowca;
import com.mires.bdbt.zajezdnia.entities.Pojazd;
import com.mires.bdbt.zajezdnia.repositories.PojazdyRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PojazdyService {
    private final PojazdyRepository pojazdyRepository;

    public PojazdyService(PojazdyRepository pojazdyRepository) {
        this.pojazdyRepository = pojazdyRepository;
    }

    public Pojazd findByIdPojazdu(Long id) {
        return pojazdyRepository.findByIdPojazdu(id);
    }

    @Transactional
    public Long getNextId() {
        return pojazdyRepository.findAll().stream().mapToLong(Pojazd::getIdPojazdu).max().orElse(0) + 1;
    }

    public List<Pojazd> findAll() {
        return pojazdyRepository.findAll();
    }

    public void save(final Pojazd pojazd) {
        this.pojazdyRepository.save(pojazd);
    }

    public void remove(final Long id) {
        this.pojazdyRepository.deleteById(id);
    }
}
