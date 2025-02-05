package com.mires.bdbt.zajezdnia.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "BILET")
@Getter
@Setter
public class Bilet {
    @Id
    @Column(name = "ID_BILETU")
    private Long idBiletu;

    @Column(name = "DATA_SKASOWANIA")
    private Date dataSkasowania;

    @Column(name = "DATA_ZAKUPU")
    private Date dataZakupu;

    @Column(name = "TYPU_BILETU", length = 20)
    private String typuBiletu;

    @Column(name = "DLUGOSC_TRWANIA_BILETU", length = 200)
    private String dlugoscTrwaniaBiletu;

    @Column(name = "ID_PASAZERA")
    private Long idPasazera;
}
