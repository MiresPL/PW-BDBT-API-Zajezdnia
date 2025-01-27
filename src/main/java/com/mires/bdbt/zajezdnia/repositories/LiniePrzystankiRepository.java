package com.mires.bdbt.zajezdnia.repositories;

import com.mires.bdbt.zajezdnia.entities.LiniaPrzystanki;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LiniePrzystankiRepository extends JpaRepository<LiniaPrzystanki, Long> {
    @Query("SELECT lp FROM LiniaPrzystanki lp WHERE lp.linia.idLinii = ?1")
    List<LiniaPrzystanki> findByLiniaId(Long id);
}
