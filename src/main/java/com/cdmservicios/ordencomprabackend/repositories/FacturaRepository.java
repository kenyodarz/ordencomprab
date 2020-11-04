package com.cdmservicios.ordencomprabackend.repositories;

import com.cdmservicios.ordencomprabackend.models.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer> {

    @Query("select f from Factura f where f.ordenDeCompra.idordendecompra =?1 order by f.idfactura")
    List<Factura> findFacturasByOrdenDeCompra(Integer idOrden);
}
