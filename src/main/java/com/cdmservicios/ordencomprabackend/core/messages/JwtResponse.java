package com.cdmservicios.ordencomprabackend.core.messages;

import java.util.List;

public class JwtResponse {

    private String token;
    private final String type = "Bearer";
    private Long idusuario;
    private String usuario;
    private String nombreusuario;
    private String email;
    private final List<String> roles;

    public JwtResponse(String accessToken, Long idusuario, String usuario, String nombreusuario, String email, List<String> roles) {
        this.token = accessToken;
        this.idusuario = idusuario;
        this.usuario = usuario;
        this.nombreusuario = nombreusuario;
        this.email = email;
        this.roles = roles;
    }

    public String getToken() { return token; }

    public void setToken(String token) { this.token = token; }

    public String getType() {
        return type;
    }

    public Long getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Long idusuario) {
        this.idusuario = idusuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombreusuario() {
        return nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }
}
