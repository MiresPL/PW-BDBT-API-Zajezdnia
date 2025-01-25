package com.mires.bdbt.zajezdnia.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "KIEROWCA")
@Getter
@Setter
public class Kierowca {
    @Id
    @Column(name = "ID_KIEROWCY")
    private Long idKierowcy;

    @Column(name = "IMIE", length = 20)
    private String imie;

    @Column(name = "NAZWISKO", length = 30)
    private String nazwisko;

    @Column(name = "PESEL", length = 11)
    private String pesel;

    @Column(name = "DATA_URODZENIA")
    private Date dataUrodzenia;

    @Column(name = "NR_TELEFONU", length = 16)
    private String nrTelefonu;

    @Column(name = "EMAIL", length = 30)
    private String email;

    @Column(name = "NR_KONTA", length = 26)
    private String nrKonta;

    @Column(name = "WYNAGRODZENIE", length = 26)
    private Integer wynagrodzenie;

    @Column(name = "DATA_ZATRUDNIENIA")
    private Date dataZatrudnienia;

    @ManyToOne(fetch = FetchType.EAGER) // Use FetchType.LAZY to load related entities on demand
    @JoinColumn(name = "ID_ADRESU")
    private Adresy adres;
}
