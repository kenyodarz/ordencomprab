package com.cdmservicios.ordencomprabackend.controllers;

import com.cdmservicios.ordencomprabackend.models.Producto;
import com.cdmservicios.ordencomprabackend.services.apis.ProductoServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@RequestMapping("/api/productos")
public class ProductoRestController {

    private final ProductoServiceAPI serviceAPI;

    @Autowired
    public ProductoRestController(ProductoServiceAPI serviceAPI) {
        this.serviceAPI = serviceAPI;
    }

    @GetMapping("all")
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<Producto>> getAll() {
        return ResponseEntity.ok(this.serviceAPI.getAll());
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> find(@PathVariable Integer id) {
        if (this.serviceAPI.get(id) == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(this.serviceAPI.get(id));
    }

    @GetMapping("image/{id}")
    public ResponseEntity<?> verImagen(@PathVariable Integer id) {
        if (this.serviceAPI.get(id) == null || this.serviceAPI.get(id).getImagen() == null)
            return ResponseEntity.notFound().build();

        Resource imagen = new ByteArrayResource(this.serviceAPI.get(id).getImagen());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen);
    }

    @PostMapping("/save")
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> save(@Valid @RequestBody Producto entity, BindingResult result) {
        if (result.hasErrors()) {
            return this.validar(result);
        }
        return ResponseEntity.status(HttpStatus.OK).body(serviceAPI.save(entity));
    }

    @GetMapping("delete/{id}")
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Producto producto = this.serviceAPI.get(id);
        if (producto == null) return ResponseEntity.notFound().build();
        this.serviceAPI.delete(id);
        return ResponseEntity.ok().body(producto);
    }

    public ResponseEntity<?> validar(BindingResult result) {
        Map<String, Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> errores.put(err.getField(), " El campo " + err.getField() + " " + err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errores);
    }

}
