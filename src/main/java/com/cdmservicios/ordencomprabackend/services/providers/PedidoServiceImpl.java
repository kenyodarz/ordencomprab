package com.cdmservicios.ordencomprabackend.services.providers;

import com.cdmservicios.ordencomprabackend.core.archive.GenericServiceImpl;
import com.cdmservicios.ordencomprabackend.models.Pedido;
import com.cdmservicios.ordencomprabackend.repositories.PedidoRepository;
import com.cdmservicios.ordencomprabackend.services.apis.PedidoServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class PedidoServiceImpl extends GenericServiceImpl<Pedido, Integer> implements PedidoServiceAPI {

    private final PedidoRepository repository;

    @Autowired
    public PedidoServiceImpl(PedidoRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<Pedido, Integer> getRepository() {
        return repository;
    }

    @NotNull
    @Transactional
    @Override
    public Iterable<Long> findPedidosByRequisition(Integer id) {
        return repository.findPedidosByRequisition(id);
    }

    @NotNull
    @Transactional
    @Override
    public List<Pedido> findPedidosByOrdenDeCompra(Integer id) {
        return repository.findPedidosByOrdenDeCompra(id);
    }

    @NotNull
    @Transactional
    @Override
    public Integer PedidosPorOrden(Integer idOrden) {
        return this.repository.PedidosPorOrden(idOrden);
    }

    @NotNull
    @Transactional
    @Override
    public Iterable<Long> findPedidosByRequisitionAndOrOrdenDeCompraNull(Integer id) {
        return this.repository.findPedidosByRequisitionAndOrOrdenDeCompraNull(id);
    }
}
