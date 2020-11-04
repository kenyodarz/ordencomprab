package com.cdmservicios.ordencomprabackend.repositories;

import com.cdmservicios.ordencomprabackend.models.OrdenDeCompra;
import com.cdmservicios.ordencomprabackend.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenDeCompraRepository extends JpaRepository<OrdenDeCompra, Integer> {

    @Query("select o.idordendecompra from OrdenDeCompra o join o.requisition r where r.idrequisicion =?1 group by o.idordendecompra")
    Iterable<Long> findOrdenDeCompraByRequisition(Integer idrequisicion);

    @Query("select ped from Pedido ped join ped.producto p " +
            "where ped.ordenDeCompra.idordendecompra =?1 ORDER BY p.nombreproducto")
    List<Pedido> findPedidosByOrdenDeCompraAndProducto(Integer id);
}
