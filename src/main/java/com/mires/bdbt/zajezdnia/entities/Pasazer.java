package com.mires.bdbt.zajezdnia.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PASAZER")
@Getter
@Setter
public class Pasazer {
    @Id
    @Column(name = "ID_PASAZERA")
    private Long idPasazera;

    @Column(name = "LOGIN", length = 20)
    private String login;

    @Column(name = "HASLO", length = 1000)
    private String haslo;

    @Column(name = "IMIE", length = 30)
    private String imie;

    @Column(name = "NAZWISKO", length = 40)
    private String nazwisko;

    @Column(name = "EMAIL", length = 40)
    private String email;

    @Column(name = "NUMER_TELEFONU", length = 20)
    private String numerTelefonu;
}
