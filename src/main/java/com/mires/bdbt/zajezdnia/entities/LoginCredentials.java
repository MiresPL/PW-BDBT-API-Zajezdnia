package com.mires.bdbt.zajezdnia.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class LoginCredentials {
    private String login;
    private String password;
}
