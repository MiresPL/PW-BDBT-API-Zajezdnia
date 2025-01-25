package com.mires.bdbt.zajezdnia.services;


import com.mires.bdbt.zajezdnia.entities.Wlasciciel;
import com.mires.bdbt.zajezdnia.repositories.WlascicielRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class WlascicielService implements UserDetailsService {
    private final WlascicielRepository wlascicielRepository;
    private final PasswordEncoder passwordEncoder;

    public WlascicielService(WlascicielRepository wlascicielRepository, PasswordEncoder passwordEncoder) {
        this.wlascicielRepository = wlascicielRepository;
        this.passwordEncoder = passwordEncoder;
        encodeExistingPasswords();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Wlasciciel wlasciciel = wlascicielRepository.findByLogin(username); // Replace this with your method
        System.out.println(username);
        if (wlasciciel == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return wlasciciel;
    }

    public Wlasciciel login(final String login, final String password) {
        Wlasciciel wlasciciel = wlascicielRepository.findByLogin(login);
        if (wlasciciel != null && passwordEncoder.matches(password, wlasciciel.getHaslo())) return wlasciciel;
        return null;
    }

    public Wlasciciel findByLogin(String login) {
        return wlascicielRepository.findByLogin(login);
    }

    @Transactional
    public void encodeExistingPasswords() {
        List<Wlasciciel> users = wlascicielRepository.findAll();
        for (Wlasciciel user : users) {
            if (!user.getHaslo().startsWith("$2a$")) { // Skip if already encoded
                user.setHaslo(passwordEncoder.encode(user.getHaslo()));
                wlascicielRepository.save(user);
            }
        }
    }

    public Wlasciciel findById(Long id) {
        return wlascicielRepository.findById(id).orElse(null);
    }

    public void save(Wlasciciel wlasciciel) {
        wlascicielRepository.save(wlasciciel);
    }

    public void remove(Long id) {
        wlascicielRepository.deleteById(id);
    }
}
