package com.mires.bdbt.zajezdnia.repositories;

import com.mires.bdbt.zajezdnia.entities.Wlasciciel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WlascicielRepository extends JpaRepository<Wlasciciel, Long> {
    Wlasciciel findByLogin(String login);
}
