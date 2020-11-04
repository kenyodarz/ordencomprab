package com.cdmservicios.ordencomprabackend.controllers;

import com.cdmservicios.ordencomprabackend.models.Cotizaciones;
import com.cdmservicios.ordencomprabackend.services.apis.CotizacionesServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/cotizaciones")
public class CotizacionesRestController {

    private final CotizacionesServiceAPI serviceAPI;

    @Autowired
    public CotizacionesRestController(CotizacionesServiceAPI serviceAPI) {
        this.serviceAPI = serviceAPI;
    }

    @GetMapping
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<Cotizaciones>> getAll(){
        return ResponseEntity.ok(this.serviceAPI.getAll());
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Cotizaciones find(@PathVariable Integer id){
        return serviceAPI.get(id);
    }


    @GetMapping("/archivo/{id}")
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> verArchivo(@PathVariable Integer id) {
        Cotizaciones entity = serviceAPI.get(id);
        if (entity== null || entity.getArchivo() == null) return ResponseEntity.notFound().build();

        Resource resource = new ByteArrayResource(entity.getArchivo());

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(resource);
    }
}
