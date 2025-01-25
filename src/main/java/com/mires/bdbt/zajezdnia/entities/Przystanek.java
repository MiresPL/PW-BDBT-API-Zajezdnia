package com.mires.bdbt.zajezdnia.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "PRZYSTANEK")
@Getter
@Setter
public class Przystanek {
    @Id
    @Column(name = "ID_PRZYSTANKU")
    private Long idPrzystanku;

    @Column(name = "NAZWA", length = 20)
    private String nazwa;

    @Column(name = "MA_WIATE")
    private boolean maWiate;

    @Column(name = "GOOGLE_MAPS_LINK", length = 200)
    private String googleMapsLink;

    @OneToMany(mappedBy = "przystanek", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LiniaPrzystanki> liniaPrzystankiList;
}
