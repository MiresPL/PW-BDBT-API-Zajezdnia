package com.mires.bdbt.zajezdnia.services;

import com.mires.bdbt.zajezdnia.entities.LiniaPrzystanki;
import com.mires.bdbt.zajezdnia.repositories.LiniePrzystankiRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class LiniePrzystankiService {
    private final LiniePrzystankiRepository liniePrzystankiRepository;

    public LiniePrzystankiService(LiniePrzystankiRepository liniePrzystankiRepository) {
        this.liniePrzystankiRepository = liniePrzystankiRepository;
    }

    public List<LiniaPrzystanki> findByLiniaId(Long id) {
        return liniePrzystankiRepository.findByLiniaId(id);
    }
}
