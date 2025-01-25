package com.mires.bdbt.zajezdnia.services;


import com.mires.bdbt.zajezdnia.entities.Adresy;
import com.mires.bdbt.zajezdnia.entities.Bilet;
import com.mires.bdbt.zajezdnia.repositories.BiletyRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BiletyService {
    private final BiletyRepository biletyRepository;

    public BiletyService(BiletyRepository biletyRepository) {
        this.biletyRepository = biletyRepository;
    }

    public List<Bilet> findAll() {
        return biletyRepository.findAll();
    }

    public List<Bilet> getTicketsByPasazer(Long id) {
        return biletyRepository.findBiletyByIdPasazera(id);
    }

    public Long getNextId() {
        return biletyRepository.findAll().stream().mapToLong(Bilet::getIdBiletu).max().orElse(0) + 1;
    }

    public Bilet findById(Long id) {
        return biletyRepository.findById(id).orElse(null);
    }

    public void save(Bilet bilet) {
        biletyRepository.save(bilet);
    }

}
