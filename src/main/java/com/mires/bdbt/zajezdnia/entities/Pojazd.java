package com.mires.bdbt.zajezdnia.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "POJAZD")
@Getter
@Setter
public class Pojazd {
    @Id
    @Column(name = "ID_POJAZDU")
    private Long idPojazdu;

    @Column(name = "NUMER_REJESTRACYJNY")
    private String numerRejestracyjny;

    @Column(name = "POJEMNOSC")
    private Integer pojemnosc;

    @Column(name = "TYP_POJAZDU", length = 50)
    private String typPojazdu;

    @Column(name = "STATUS", length = 50)
    private String status;

    @Column(name = "OSTATNI_PRZEGLAD")
    private Date ostatniPrzeglad;

    @Column(name = "ID_ZAJEZDNI")
    private Long idZajezdni;
}
