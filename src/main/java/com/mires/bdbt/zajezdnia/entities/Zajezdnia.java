package com.mires.bdbt.zajezdnia.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ZAJEZDNIA")
public class Zajezdnia {
    @Id
    @Column(name = "ID_ZAJEZDNI")
    private Long idZajezdni;

    @Column(name = "NAZWA")
    private String nazwa;

    @Column(name = "MA_BIURO")
    private Boolean maBiuro;

    @Column(name = "MA_SERWIS")
    private Boolean maSerwis;

    @Column(name = "ID_ADRESU")
    private Long idAdresu;
}
