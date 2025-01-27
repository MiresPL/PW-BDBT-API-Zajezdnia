package com.mires.bdbt.zajezdnia.services;

import com.mires.bdbt.zajezdnia.entities.Zajezdnia;
import com.mires.bdbt.zajezdnia.repositories.ZajezdnieRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ZajezdnieService {
    private final ZajezdnieRepository zajezdnieRepository;

    public ZajezdnieService(ZajezdnieRepository zajezdnieRepository) {
        this.zajezdnieRepository = zajezdnieRepository;
    }

    public List<Zajezdnia> findAll() {
        return zajezdnieRepository.findAll();
    }
}
