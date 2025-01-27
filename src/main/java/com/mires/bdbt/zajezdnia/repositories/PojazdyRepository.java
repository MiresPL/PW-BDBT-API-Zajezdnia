package com.mires.bdbt.zajezdnia.repositories;

import com.mires.bdbt.zajezdnia.entities.Pojazd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PojazdyRepository extends JpaRepository<Pojazd, Long> {
    @Query("SELECT p FROM Pojazd p WHERE p.idPojazdu = :id")
    Pojazd findByIdPojazdu(Long id);

    @Query("SELECT p FROM Pojazd p")
    List<Pojazd> findAll();
}
