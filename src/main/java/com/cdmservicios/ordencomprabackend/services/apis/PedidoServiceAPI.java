package com.cdmservicios.ordencomprabackend.services.apis;

import com.cdmservicios.ordencomprabackend.core.archive.GenericServiceAPI;
import com.cdmservicios.ordencomprabackend.models.Pedido;

import java.util.List;

public interface PedidoServiceAPI extends GenericServiceAPI<Pedido, Integer> {
    Iterable<Long> findPedidosByRequisition(Integer id);
    List<Pedido> findPedidosByOrdenDeCompra(Integer id);
    Integer PedidosPorOrden(Integer idOrden);
    Iterable<Long> findPedidosByRequisitionAndOrOrdenDeCompraNull(Integer id);
}
