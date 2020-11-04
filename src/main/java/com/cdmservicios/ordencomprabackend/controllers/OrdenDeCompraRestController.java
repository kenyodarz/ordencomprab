package com.cdmservicios.ordencomprabackend.controllers;

import com.cdmservicios.ordencomprabackend.models.OrdenDeCompra;
import com.cdmservicios.ordencomprabackend.models.Pedido;
import com.cdmservicios.ordencomprabackend.services.apis.OrdenDeCompraAAPI;
import net.sf.jasperreports.engine.*;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/ordenes")
public class OrdenDeCompraRestController {

    private final OrdenDeCompraAAPI serviceAPI;

    public OrdenDeCompraRestController(OrdenDeCompraAAPI serviceAPI) {
        this.serviceAPI = serviceAPI;
    }

    @GetMapping("/all")
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<OrdenDeCompra>> getAll() {
        return ResponseEntity.ok(serviceAPI.getAll());
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> find(@PathVariable Integer id) {
        if (this.serviceAPI.get(id) == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(this.serviceAPI.get(id));
    }

    // Debido a la columna nro requisición vamos hacer un paso extra en la operación
    @PostMapping("/save")
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> save(@Valid @RequestBody OrdenDeCompra entity, BindingResult result) {
        if (result.hasErrors()) return validar(result);
        // Paso extra.
        OrdenDeCompra ordenDeCompra = serviceAPI.get(serviceAPI.save(entity).getIdordendecompra());
        return ResponseEntity.status(HttpStatus.OK).body(ordenDeCompra);
    }

    @GetMapping("/delete/{id}")
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        OrdenDeCompra entity = serviceAPI.get(id);
        if (entity == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(entity);
    }


    @GetMapping("por/{id}")
    public Iterable<Long> findOrdenDeCompraByRequisition(@PathVariable Integer id) {
        return this.serviceAPI.findOrdenDeCompraByRequisition(id);
    }

    @GetMapping("{id}/pedidos")
    public ResponseEntity<List<Pedido>> findPedidosByOrdenDeCompraAndProducto(@PathVariable Integer id) {
        return ResponseEntity.ok().body(this.serviceAPI.findPedidosByOrdenDeCompraAndProducto(id));
    }

    @GetMapping("pdf/{id}")
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> verArchivo(@PathVariable Integer id) throws JRException, IOException {
        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
//          con = DriverManager.getConnection("jdbc:postgresql://192.168.10.5:5432/REQUISICION", "postgres", "cdm");
//          con = DriverManager.getConnection("jdbc:postgresql://192.168.10.5:5432/REQUISICION", "postgres", "cdm");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/REQUISICION", "postgres", "cdm");
        } catch (ClassNotFoundException | SQLException ignored) {
        }
        String sourceFileName = ResourceUtils
                .getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "ORDEN_DE_COMPRA.jrxml")
                .getAbsolutePath();
        JasperReport jasperReport = JasperCompileManager.compileReport(sourceFileName);
        //List<OrdenDeCompra> dataList = new ArrayList<>();
        //List<Pedido> dataTable;
        //OrdenDeCompra ordenDeCompra = serviceAPI.get(id);
        //dataTable = serviceAPI.findPedidosByOrdenDeCompraAndProducto(id);
        //dataList.add(ordenDeCompra);
        //JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataList);
        //JRBeanCollectionDataSource tDataSource = new JRBeanCollectionDataSource(dataTable);
        Map<String, Object> parameters = new HashMap<>();
        //parameters.put("SubDataSource", tDataSource);
        parameters.put("IDORDEN", id);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, con);
        Resource resource = new ByteArrayResource(JasperExportManager.exportReportToPdf(jasperPrint));
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(resource);
    }

    public ResponseEntity<?> validar(BindingResult result) {
        Map<String, Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> errores.put(err.getField(), " El campo " + err.getField() + " " + err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errores);
    }

}
