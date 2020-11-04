package com.cdmservicios.ordencomprabackend.controllers;

import com.cdmservicios.ordencomprabackend.models.Cliente;
import com.cdmservicios.ordencomprabackend.services.apis.ClienteServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/clientes")
public class ClienteRestController {

    private final ClienteServiceAPI serviceAPI;

    @Autowired
    public ClienteRestController(ClienteServiceAPI serviceAPI) {
        this.serviceAPI = serviceAPI;
    }

    @GetMapping
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<Cliente>> getAll() {
        return ResponseEntity.ok(serviceAPI.getAll());
    }
}
