package com.cdmservicios.ordencomprabackend.controllers;

import com.cdmservicios.ordencomprabackend.models.Producto;
import com.cdmservicios.ordencomprabackend.models.Proveedor;
import com.cdmservicios.ordencomprabackend.services.apis.ProductoServiceAPI;
import com.cdmservicios.ordencomprabackend.services.apis.ProveedorServiceAPI;
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
@RequestMapping("/api/proveedores")
public class ProveedorRestController {

    private final ProveedorServiceAPI serviceAPI;
    private final ProductoServiceAPI productoServiceAPI;

    @Autowired
    public ProveedorRestController(ProveedorServiceAPI serviceAPI, ProductoServiceAPI productoServiceAPI) {
        this.serviceAPI = serviceAPI;
        this.productoServiceAPI = productoServiceAPI;
    }

    @GetMapping("/all")
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<Proveedor>> getAll() {
        return ResponseEntity.ok(this.serviceAPI.getAll());
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Proveedor entity = this.serviceAPI.get(id);
        if (entity == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(entity);
    }

    @PostMapping("/save")
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> save(@Valid @RequestBody Proveedor entity, BindingResult result) {
        if (result.hasErrors()) return this.validar(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.serviceAPI.save(entity));
    }

    @GetMapping("/delete/{id}")
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Proveedor entity = this.serviceAPI.get(id);
        if (entity == null) return ResponseEntity.notFound().build();
        this.serviceAPI.delete(id);
        return ResponseEntity.ok(entity);
    }

    @PutMapping("/{id}/productos/cargar")
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> CargarProductos(@RequestBody List<Producto> productos, @PathVariable Integer id){
        Proveedor proveedor = this.serviceAPI.get(id);
        if (proveedor == null) return ResponseEntity.notFound().build();
        productos.forEach(proveedor::addProducto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.serviceAPI.save(proveedor));
    }

    @PutMapping("/{id}/productos/eliminar")
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> EliminarProductos(@RequestBody Producto producto, @PathVariable Integer id){
        Proveedor proveedor = this.serviceAPI.get(id);
        if (proveedor == null) return ResponseEntity.notFound().build();
        Producto producto1 = this.productoServiceAPI.get(producto.getIdproducto());
        if (producto1 == null) return ResponseEntity.notFound().build();
        proveedor.removeProducto(producto);
        return ResponseEntity.status(HttpStatus.OK).body(this.serviceAPI.save(proveedor));
    }

    public ResponseEntity<?> validar(BindingResult result) {
        Map<String, Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> errores.put(err.getField(), " El campo " + err.getField() + " " + err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errores);
    }

}
