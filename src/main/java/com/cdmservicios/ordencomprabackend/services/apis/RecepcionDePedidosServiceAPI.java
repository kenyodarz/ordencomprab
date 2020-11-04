package com.cdmservicios.ordencomprabackend.services.apis;

import com.cdmservicios.ordencomprabackend.core.archive.GenericServiceAPI;
import com.cdmservicios.ordencomprabackend.models.RecepcionDePedidos;

import java.util.List;

public interface RecepcionDePedidosServiceAPI extends GenericServiceAPI<RecepcionDePedidos, Integer>{
    List<RecepcionDePedidos> findRecepcionDePedidosByPedido(Integer idPedido);
    Integer RecepcionesPorPedidos(Integer idPedido);
}
