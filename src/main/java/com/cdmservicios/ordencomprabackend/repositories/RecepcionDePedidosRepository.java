package com.cdmservicios.ordencomprabackend.repositories;

import com.cdmservicios.ordencomprabackend.models.RecepcionDePedidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecepcionDePedidosRepository extends JpaRepository<RecepcionDePedidos, Integer> {

    @Query("select r from RecepcionDePedidos r join r.pedido p " +
            "where p.idpedido =?1 group by r.idrecepciondepedido")
    List<RecepcionDePedidos> findRecepcionDePedidosByPedido(Integer idPedido);

    @Query("select sum(rp.cantidadrecibida) as recibidos from RecepcionDePedidos rp " +
            "where rp.pedido.ordenDeCompra.idordendecompra =?1")
    Integer RecepcionesPorPedidos(Integer idOrden);
}
