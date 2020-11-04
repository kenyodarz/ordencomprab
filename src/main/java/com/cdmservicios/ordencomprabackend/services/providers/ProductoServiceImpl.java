package com.cdmservicios.ordencomprabackend.services.providers;

import com.cdmservicios.ordencomprabackend.core.archive.GenericServiceImpl;
import com.cdmservicios.ordencomprabackend.models.Producto;
import com.cdmservicios.ordencomprabackend.repositories.ProductoRepository;
import com.cdmservicios.ordencomprabackend.services.apis.ProductoServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl extends GenericServiceImpl<Producto, Integer> implements ProductoServiceAPI {


    private final ProductoRepository repository;

    @Autowired
    public ProductoServiceImpl(ProductoRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<Producto, Integer> getRepository() {
        return repository;
    }
}
