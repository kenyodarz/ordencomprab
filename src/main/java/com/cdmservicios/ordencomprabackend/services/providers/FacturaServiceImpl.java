package com.cdmservicios.ordencomprabackend.services.providers;

import com.cdmservicios.ordencomprabackend.core.archive.GenericServiceImpl;
import com.cdmservicios.ordencomprabackend.models.Factura;
import com.cdmservicios.ordencomprabackend.repositories.FacturaRepository;
import com.cdmservicios.ordencomprabackend.services.apis.FacturaServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class FacturaServiceImpl extends GenericServiceImpl<Factura, Integer> implements FacturaServiceAPI {

    private final FacturaRepository repository;

    @Autowired
    public FacturaServiceImpl(FacturaRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<Factura, Integer> getRepository() {
        return repository;
    }

    @NotNull
    @Transactional
    @Override
    public List<Factura> findFacturasByOrdenDeCompra(Integer idOrden) {
        return this.repository.findFacturasByOrdenDeCompra(idOrden);
    }
}
