package com.mires.bdbt.zajezdnia.configs;


import com.mires.bdbt.zajezdnia.entities.Wlasciciel;
import com.mires.bdbt.zajezdnia.services.WlascicielService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final WlascicielService wlascicielService;

    public CustomAuthenticationProvider(WlascicielService wlascicielService) {
        this.wlascicielService = wlascicielService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String password = authentication.getCredentials().toString();

        // Custom login logic
        Wlasciciel wlasciciel = wlascicielService.login(login, password);
        if (wlasciciel == null) {
            throw new BadCredentialsException("Invalid login or password");
        }

        // Create an authenticated token with the roles/authorities
        return new UsernamePasswordAuthenticationToken(
                wlasciciel.getLogin(),
                wlasciciel.getHaslo(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))
        );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
