package com.cdmservicios.ordencomprabackend.services.providers;

import com.cdmservicios.ordencomprabackend.core.archive.GenericServiceImpl;
import com.cdmservicios.ordencomprabackend.models.OrdenDeCompra;
import com.cdmservicios.ordencomprabackend.models.Pedido;
import com.cdmservicios.ordencomprabackend.repositories.OrdenDeCompraRepository;
import com.cdmservicios.ordencomprabackend.services.apis.OrdenDeCompraAAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class OrdenDeCompraServiceImpl extends GenericServiceImpl<OrdenDeCompra, Integer> implements OrdenDeCompraAAPI {

    private final OrdenDeCompraRepository repository;

    @Autowired
    public OrdenDeCompraServiceImpl(OrdenDeCompraRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<OrdenDeCompra, Integer> getRepository() {
        return this.repository;
    }

    @NotNull
    @Transactional
    @Override
    public Iterable<Long> findOrdenDeCompraByRequisition(Integer id) {
        return this.repository.findOrdenDeCompraByRequisition(id);
    }

    @NotNull
    @Transactional
    @Override
    public List<Pedido> findPedidosByOrdenDeCompraAndProducto(Integer id) {
        return this.repository.findPedidosByOrdenDeCompraAndProducto(id);
    }
}
