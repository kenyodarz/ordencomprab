package com.cdmservicios.ordencomprabackend.controllers;

import com.cdmservicios.ordencomprabackend.models.Requisition;
import com.cdmservicios.ordencomprabackend.services.apis.RequisitionServiceAPI;
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
@RequestMapping("/api/requisitions")
public class RequisitionRestController {

    private final RequisitionServiceAPI serviceAPI;

    @Autowired
    public RequisitionRestController(RequisitionServiceAPI serviceAPI) {
        this.serviceAPI = serviceAPI;
    }

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
