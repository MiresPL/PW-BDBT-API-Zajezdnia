package com.mires.bdbt.zajezdnia.services;


import com.mires.bdbt.zajezdnia.entities.Kierowca;
import com.mires.bdbt.zajezdnia.repositories.KierowcyRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class KierowcyService {
    private final KierowcyRepository kierowcyRepository;

    public KierowcyService(KierowcyRepository kierowcyRepository) {
        this.kierowcyRepository = kierowcyRepository;
    }

    @Transactional
    public Kierowca findById(final Long id) {
        final Kierowca Kierowca = kierowcyRepository.findByIdWithAdres(id);
        if (Kierowca == null) {
            throw new IllegalArgumentException("Kierowca not found");
        }
        if (Kierowca.getAdres() != null) {
            Kierowca.getAdres().getKraj();
        }
        return Kierowca;
    }

    @Transactional
    public Long getNextId() {
        return kierowcyRepository.findAll().stream().mapToLong(Kierowca::getIdKierowcy).max().orElse(0) + 1;
    }

    @Transactional
    public List<Kierowca> getAllKierowcy() {
        return kierowcyRepository.getAllKierowcaInfo();
    }

    public void save(final Kierowca kierowca) {
        this.kierowcyRepository.save(kierowca);
    }

    public void remove(final Long id) {
        this.kierowcyRepository.deleteById(id);
    }
}
