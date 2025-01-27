package com.mires.bdbt.zajezdnia.repositories;

import com.mires.bdbt.zajezdnia.entities.Linia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LinieRepository extends JpaRepository<Linia, Long> {
    @Query("SELECT l FROM Linia l WHERE l.idLinii = :id")
    Linia findByIdLinii(Long id);

    @Query("SELECT l FROM Linia l")
    List<Linia> findAll();
}
