package com.cdmservicios.ordencomprabackend.repositories;

import com.cdmservicios.ordencomprabackend.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    @Query("select p.idpedido from Pedido p join p.requisition r " +
            "where r.idrequisicion =?1 group by p.idpedido")
    Iterable<Long> findPedidosByRequisition(Integer id);

    @Query("select p from Pedido p join p.ordenDeCompra o " +
            "where o.idordendecompra = ?1 group by p.idpedido")
    List<Pedido> findPedidosByOrdenDeCompra(Integer id);

    @Query("select p.idpedido from Pedido p join p.requisition r " +
            "where r.idrequisicion =?1 and p.ordenDeCompra is null group by p.idpedido")
    Iterable<Long> findPedidosByRequisitionAndOrOrdenDeCompraNull(Integer id);

    @Query("select sum(p.cantidadsolicitada) as solicitados from Pedido p " +
            "where p.ordenDeCompra.idordendecompra=?1")
    Integer PedidosPorOrden(Integer idOrden);
}
