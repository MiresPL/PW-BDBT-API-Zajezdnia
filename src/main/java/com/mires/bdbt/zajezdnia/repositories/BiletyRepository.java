package com.mires.bdbt.zajezdnia.repositories;


import com.mires.bdbt.zajezdnia.entities.Bilet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BiletyRepository extends JpaRepository<Bilet, Long> {
    @Query("SELECT b FROM Bilet b")
    List<Bilet> findAll();

    @Query("SELECT b FROM Bilet b WHERE b.idPasazera = :idPasazera ORDER BY b.dataZakupu DESC")
    List<Bilet> findBiletyByIdPasazera(@Param("idPasazera") Long idPasazera);
}
