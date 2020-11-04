package com.cdmservicios.ordencomprabackend.services.providers;

import com.cdmservicios.ordencomprabackend.core.archive.GenericServiceImpl;
import com.cdmservicios.ordencomprabackend.models.CentroDeCostos;
import com.cdmservicios.ordencomprabackend.repositories.CentroDeCostosRepository;
import com.cdmservicios.ordencomprabackend.services.apis.CentroDeCostoServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CentroDeCostosServiceImpl extends GenericServiceImpl<CentroDeCostos, Long> implements CentroDeCostoServiceAPI {

    private final CentroDeCostosRepository repository;

    @Autowired
    public CentroDeCostosServiceImpl(CentroDeCostosRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<CentroDeCostos, Long> getRepository() {
        return this.repository;
    }
}
