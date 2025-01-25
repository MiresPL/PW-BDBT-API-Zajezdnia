package com.mires.bdbt.zajezdnia.repositories;

import com.mires.bdbt.zajezdnia.entities.Kierowca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KierowcyRepository extends JpaRepository<Kierowca, Long> {
    @Query("SELECT k FROM Kierowca k " +
            "JOIN FETCH k.adres a "
    )
    List<Kierowca> getAllKierowcaInfo();

    @Query("SELECT k FROM Kierowca k")
    List<Kierowca> finAll();

    @Query("SELECT k FROM Kierowca k JOIN FETCH k.adres WHERE k.idKierowcy = :id")
    Kierowca findByIdWithAdres(@Param("id") Long id);
}
