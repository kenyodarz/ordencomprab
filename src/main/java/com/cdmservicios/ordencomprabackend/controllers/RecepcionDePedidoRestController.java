package com.cdmservicios.ordencomprabackend.controllers;

import com.cdmservicios.ordencomprabackend.models.RecepcionDePedidos;
import com.cdmservicios.ordencomprabackend.security.services.UserDetailsServiceImpl;
import com.cdmservicios.ordencomprabackend.services.apis.RecepcionDePedidosServiceAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/recepciones")
@RequiredArgsConstructor
public class RecepcionDePedidoRestController {

    public final RecepcionDePedidosServiceAPI serviceAPI;
    private final UserDetailsServiceImpl userdetails;


    @GetMapping("/all")
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<RecepcionDePedidos>> getAll() {
        return ResponseEntity.ok(this.serviceAPI.getAll());
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> find(@PathVariable Integer id) {
        if (this.serviceAPI.get(id) == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(this.serviceAPI.get(id));
    }

    @PostMapping("/save")
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> save(@Valid @RequestBody RecepcionDePedidos entity, BindingResult result) {
        if (result.hasErrors()) return this.validar(result);
        entity.setUsuario(userdetails.findByEmail(entity.getUsuario().getEmail()));
        return ResponseEntity.status(HttpStatus.CREATED).body(this.serviceAPI.save(entity));
    }

    @GetMapping("/delete/{id}")
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        RecepcionDePedidos entity = this.serviceAPI.get(id);
        if (entity == null) return ResponseEntity.notFound().build();
        this.serviceAPI.delete(id);
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/porPedido/{id}")
    public ResponseEntity<?> findRecepcionDePedidosByPedido(@PathVariable Integer id) {
        return ResponseEntity.ok(this.serviceAPI.findRecepcionDePedidosByPedido(id));
    }

    @GetMapping("/recibidos/{id}")
    public ResponseEntity<?> RecepcionesPorPedidos(@PathVariable Integer id) {
        return ResponseEntity.ok(this.serviceAPI.RecepcionesPorPedidos(id));
    }

    public ResponseEntity<?> validar(BindingResult result) {
        Map<String, Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> errores.put(err.getField(), " El campo " + err.getField() + " " + err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errores);
    }
}

