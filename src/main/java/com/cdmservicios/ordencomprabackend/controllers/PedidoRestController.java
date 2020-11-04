package com.cdmservicios.ordencomprabackend.controllers;

import com.cdmservicios.ordencomprabackend.models.Pedido;
import com.cdmservicios.ordencomprabackend.services.apis.PedidoServiceAPI;
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
@RequestMapping("/api/pedidos")
public class PedidoRestController {

    private final PedidoServiceAPI serviceAPI;

    @Autowired
    public PedidoRestController(PedidoServiceAPI serviceAPI) {
        this.serviceAPI = serviceAPI;
    }

    @GetMapping("/all")
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<Pedido>> getAll() {
        return ResponseEntity.ok(serviceAPI.getAll());
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> find(@PathVariable Integer id) {
        if (this.serviceAPI.get(id) == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(this.serviceAPI.get(id));
    }

    @GetMapping("/por/{id}")
    public Iterable<Long> findPedidosByRequisition(@PathVariable Integer id) {
        return this.serviceAPI.findPedidosByRequisition(id);
    }

    @GetMapping("/sinOrden/{id}")
    public ResponseEntity<?> findPedidosByRequisitionAndOrOrdenDeCompraNull(@PathVariable Integer id) {
        return ResponseEntity.ok(this.serviceAPI.findPedidosByRequisitionAndOrOrdenDeCompraNull(id));
    }

    @GetMapping("/orden/{id}")
    public ResponseEntity<?> findPedidosByOrdenDeCompra(@PathVariable Integer id) {
        return ResponseEntity.ok(this.serviceAPI.findPedidosByOrdenDeCompra(id));
    }

    @GetMapping("/solicitados/{id}")
    public ResponseEntity<?> PedidosPorOrden(@PathVariable Integer id) {
        return ResponseEntity.ok(this.serviceAPI.PedidosPorOrden(id));
    }

    @PostMapping("/save")
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> save(@Valid @RequestBody Pedido entity, BindingResult result) {
        if (result.hasErrors()) {
            return this.validar(result);
        }
        return ResponseEntity.status(HttpStatus.OK).body(serviceAPI.save(entity));
    }

    @GetMapping("delete/{id}")
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Pedido entity = this.serviceAPI.get(id);
        if (entity == null) return ResponseEntity.notFound().build();
        this.serviceAPI.delete(id);
        return ResponseEntity.ok().body(entity);
    }

    public ResponseEntity<?> validar(BindingResult result) {
        Map<String, Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> errores.put(err.getField(), " El campo " + err.getField() + " " + err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errores);
    }
}
