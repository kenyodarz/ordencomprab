package com.cdmservicios.ordencomprabackend.core.requests;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank
    private String usuario;

    @NotBlank
    private String password;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
