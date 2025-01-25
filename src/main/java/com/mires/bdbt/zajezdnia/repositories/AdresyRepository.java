package com.mires.bdbt.zajezdnia.repositories;

import com.mires.bdbt.zajezdnia.entities.Adresy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdresyRepository extends JpaRepository<Adresy, Long> {
    @Query("SELECT a FROM Adresy a")
    List<Adresy> findAll();
}
