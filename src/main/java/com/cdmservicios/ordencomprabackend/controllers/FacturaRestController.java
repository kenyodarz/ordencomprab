package com.cdmservicios.ordencomprabackend.controllers;

import com.cdmservicios.ordencomprabackend.models.Factura;
import com.cdmservicios.ordencomprabackend.services.apis.FacturaServiceAPI;
import com.cdmservicios.ordencomprabackend.services.apis.OrdenDeCompraAAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/facturas")
public class FacturaRestController {

    private final FacturaServiceAPI serviceAPI;
    private final OrdenDeCompraAAPI ordenServiceAPI;

    @Autowired
    public FacturaRestController(FacturaServiceAPI serviceAPI, OrdenDeCompraAAPI ordenServiceAPI) {
        this.serviceAPI = serviceAPI;
        this.ordenServiceAPI = ordenServiceAPI;
    }

    @GetMapping
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<Factura>> getAll() {
        return ResponseEntity.ok(this.serviceAPI.getAll());
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> find(@PathVariable Integer id) {
        if (serviceAPI.get(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(serviceAPI.get(id));
    }

    @PostMapping("/save")
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> save(@Valid @RequestBody Factura entity, BindingResult result) {
        if (result.hasErrors()) {
            return this.validar(result);
        }
        return ResponseEntity.status(HttpStatus.OK).body(this.serviceAPI.save(entity));
    }

    @GetMapping("delete/{id}")
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Factura entity = this.serviceAPI.get(id);
        if (entity == null) return ResponseEntity.notFound().build();
        this.serviceAPI.delete(id);
        return ResponseEntity.ok().body(entity);
    }

    @GetMapping("/orden/{idOrden}")
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> findFacturasByOrdenDeCompra(@PathVariable Integer idOrden) {
        return ResponseEntity.ok(this.serviceAPI.findFacturasByOrdenDeCompra(idOrden));
    }

    @GetMapping("/archivo/{id}")
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> verArchivo(@PathVariable Integer id) {
        Factura entity = serviceAPI.get(id);
        if (entity == null || entity.getArchivo() == null) return ResponseEntity.notFound().build();

        Resource resource = new ByteArrayResource(entity.getArchivo());

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(resource);
    }

    @PostMapping("/save-file/{id}")
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> guardarFactura(@Valid Factura entity, BindingResult result,
                                            @RequestParam MultipartFile archivo,
                                            @PathVariable Integer id) throws IOException {
        if (entity == null) {
            return ResponseEntity.notFound().build();
        }

        if (!archivo.isEmpty()) {
            entity.setArchivo(archivo.getBytes());
            entity.setOrdenDeCompra(ordenServiceAPI.get(id));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceAPI.save(entity));
    }

    public ResponseEntity<?> validar(BindingResult result) {
        Map<String, Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> errores.put(err.getField(), " El campo " + err.getField() + " " + err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errores);
    }
}
