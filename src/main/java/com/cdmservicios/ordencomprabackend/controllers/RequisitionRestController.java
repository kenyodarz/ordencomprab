package com.cdmservicios.ordencomprabackend.controllers;

import com.cdmservicios.ordencomprabackend.models.Requisition;
import com.cdmservicios.ordencomprabackend.security.services.UserDetailsServiceImpl;
import com.cdmservicios.ordencomprabackend.services.apis.RequisitionServiceAPI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/requisitions")
@RequiredArgsConstructor
@Slf4j
public class RequisitionRestController {

    private final RequisitionServiceAPI serviceAPI;
    private final UserDetailsServiceImpl userdetails;


    @GetMapping("/all")
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<Requisition>> getAll() {
        return ResponseEntity.ok(serviceAPI.getAll());
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Requisition> find(@PathVariable Integer id) {
        return ResponseEntity.ok(serviceAPI.get(id));
    }

    // Debido a la columna nro requisición vamos hacer un paso extra en la operación
    @PostMapping("/save")
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> save(@Valid @RequestBody Requisition entity, BindingResult result) {
        if (result.hasErrors()) return validar(result);
        // Paso extra.
        entity.setUsuario(userdetails.findByEmail(entity.getUsuario().getEmail()));
        Requisition requisition = serviceAPI.get(serviceAPI.save(entity).getIdrequisicion());
        return ResponseEntity.status(HttpStatus.OK).body(requisition);
    }

    @GetMapping("/delete/{id}")
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Requisition entity = serviceAPI.get(id);
        if (entity == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(entity);
    }

    public ResponseEntity<?> validar(BindingResult result) {
        Map<String, Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> errores.put(err.getField(), " El campo " + err.getField() + " " + err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errores);
    }
}
