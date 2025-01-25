package com.mires.bdbt.zajezdnia.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "WLASCICIEL")
@Getter
@Setter
public class Wlasciciel implements UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "ROLE_ADMIN"); // Assign roles dynamically
    }

    @Override
    public String getPassword() {
        return this.getHaslo();
    }

    @Override
    public String getUsername() {
        return this.getLogin();
    }

    @Id
    @Column(name = "ID_WLASCICIELA")
    private Long idWlasciciela;

    @Column(name = "IMIE", length = 20)
    private String imie;

    @Column(name = "NAZWISKO", length = 30)
    private String nazwisko;

    @Column(name = "LOGIN", length = 20)
    private String login;

    @Column(name = "HASLO", length = 1000)
    private String haslo;
}
