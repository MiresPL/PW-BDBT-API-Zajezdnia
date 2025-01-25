package com.mires.bdbt.zajezdnia.services;

import com.mires.bdbt.zajezdnia.entities.Pasazer;
import com.mires.bdbt.zajezdnia.repositories.PasazerRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PasazerService {
    private final PasazerRepository pasazerRepository;
    private final PasswordEncoder passwordEncoder;

    public PasazerService(PasazerRepository pasazerRepository, PasswordEncoder passwordEncoder) {
        this.pasazerRepository = pasazerRepository;
        this.passwordEncoder = passwordEncoder;
        encodeExistingPasswords();
    }

    public Pasazer login(final String login, final String password) {
        Pasazer pasazer = pasazerRepository.findByLogin(login);

        if (pasazer != null && passwordEncoder.matches(password, pasazer.getHaslo())) return pasazer;
        return null;
    }

    @Transactional
    public void encodeExistingPasswords() {
        List<Pasazer> users = pasazerRepository.findAll();
        for (Pasazer user : users) {
            if (!user.getHaslo().startsWith("$2a$")) {
                user.setHaslo(passwordEncoder.encode(user.getHaslo()));
                pasazerRepository.save(user);
            }
        }
    }

    public List<Pasazer> findAll() {
        return pasazerRepository.findAll();
    }

    public Pasazer findById(Long id) {
        return pasazerRepository.findById(id).orElse(null);
    }

    public void save(Pasazer pasazer) {
        pasazerRepository.save(pasazer);
    }

    public void remove(Long id) {
        pasazerRepository.deleteById(id);
    }
}
