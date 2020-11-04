package com.cdmservicios.ordencomprabackend.services.providers;

import com.cdmservicios.ordencomprabackend.core.archive.GenericServiceImpl;
import com.cdmservicios.ordencomprabackend.models.Proveedor;
import com.cdmservicios.ordencomprabackend.repositories.ProveedorRepository;
import com.cdmservicios.ordencomprabackend.services.apis.ProveedorServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ProveedorServiceImpl extends GenericServiceImpl<Proveedor, Integer> implements ProveedorServiceAPI {

    private final ProveedorRepository repository;

    @Autowired
    public ProveedorServiceImpl(ProveedorRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<Proveedor, Integer> getRepository() {
        return repository;
    }
}
