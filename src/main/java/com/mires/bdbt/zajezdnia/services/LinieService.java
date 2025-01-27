package com.mires.bdbt.zajezdnia.services;

import com.mires.bdbt.zajezdnia.entities.Linia;
import com.mires.bdbt.zajezdnia.repositories.LinieRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class LinieService {
    private final LinieRepository linieRepository;

    public LinieService(LinieRepository linieRepository) {
        this.linieRepository = linieRepository;
    }

    public List<Linia> findAll() {
        return linieRepository.findAll();
    }

    public Linia findByIdLinii(Long id) {
        return linieRepository.findByIdLinii(id);
    }
}
