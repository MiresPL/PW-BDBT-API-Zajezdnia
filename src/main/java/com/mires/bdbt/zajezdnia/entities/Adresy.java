package com.mires.bdbt.zajezdnia.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "ADRESY")
@Getter
@Setter
public class Adresy {
    @Id
    @Column(name = "ID_ADRESU")
    public Long idAdresu;

    @Column(name = "KRAJ", length = 40)
    public String kraj;

    @Column(name = "MIASTO", length = 40)
    public String miasto;

    @Column(name = "ULICA", length = 20)
    public String ulica;

    @Column(name = "NUMER_BUDYNKU", length = 20)
    public String numerBudynku;

    @Column(name = "NUMER_LOKALU", length = 20)
    public String numerLokalu;

    @Column(name = "KOD_POCZTOWY", length = 20)
    public String kodPocztowy;

    @OneToMany(mappedBy = "adres", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Kierowca> kierowcaList;
}
