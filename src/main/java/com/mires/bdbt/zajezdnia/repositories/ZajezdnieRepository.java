package com.mires.bdbt.zajezdnia.repositories;

import com.mires.bdbt.zajezdnia.entities.Zajezdnia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ZajezdnieRepository extends JpaRepository<Zajezdnia, Long> {
    @Query("SELECT z FROM Zajezdnia z")
    List<Zajezdnia> findAll();
}
