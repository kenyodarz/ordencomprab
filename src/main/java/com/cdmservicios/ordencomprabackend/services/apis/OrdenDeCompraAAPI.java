package com.cdmservicios.ordencomprabackend.services.apis;

import com.cdmservicios.ordencomprabackend.core.archive.GenericServiceAPI;
import com.cdmservicios.ordencomprabackend.models.OrdenDeCompra;
import com.cdmservicios.ordencomprabackend.models.Pedido;

import java.util.List;

public interface OrdenDeCompraAAPI extends GenericServiceAPI<OrdenDeCompra, Integer> {
    Iterable<Long> findOrdenDeCompraByRequisition(Integer id);
    List<Pedido> findPedidosByOrdenDeCompraAndProducto(Integer id);
}
