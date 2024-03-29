package com.cdmservicios.ordencomprabackend.core.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
