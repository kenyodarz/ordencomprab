package com.cdmservicios.ordencomprabackend.services.apis;

import com.cdmservicios.ordencomprabackend.core.archive.GenericServiceAPI;
import com.cdmservicios.ordencomprabackend.models.Factura;

import java.util.List;

public interface FacturaServiceAPI extends GenericServiceAPI<Factura, Integer> {
    List<Factura> findFacturasByOrdenDeCompra(Integer idOrden);
}
