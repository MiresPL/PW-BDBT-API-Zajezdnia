package com.mires.bdbt.zajezdnia.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "LINIA_PRZYSTANKI")
@Getter
@Setter
public class LiniaPrzystanki {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_LINII")
    private Linia linia;

    @ManyToOne
    @JoinColumn(name = "ID_PRZYSTANKU")
    private Przystanek przystanek;

    @Column(name = "KOLEJNOSC_NA_TRASIE")
    private Long kolejnoscNaTrasie;

    @Column(name = "CZAS_PRZEJAZDU")
    private Date czasPrzejazdu;

    @Column(name = "CZAS_POSTOJU")
    private Date czasPostoju;
}
