package com.mires.bdbt.zajezdnia.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "LINIA")
@Getter
@Setter
public class Linia {
    @Id
    @Column(name = "ID_LINII")
    private Long idLinii;

    @Column(name = "OPIS_LINII", length = 400)
    private String opisLinii;

    @Column(name = "CZESTOTLIWOSC_KURSOWANIA")
    private Date czestotliwoscKursowania;

    @Column(name = "NAZWA", length = 4)
    private String nazwa;

    @OneToMany(mappedBy = "linia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LiniaPrzystanki> liniaPrzystankiList;
}
