package com.cdmservicios.ordencomprabackend.services.providers;

import com.cdmservicios.ordencomprabackend.core.archive.GenericServiceImpl;
import com.cdmservicios.ordencomprabackend.models.Requisition;
import com.cdmservicios.ordencomprabackend.repositories.RequisitionRepository;
import com.cdmservicios.ordencomprabackend.services.apis.RequisitionServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class RequisitionServiceImpl extends GenericServiceImpl<Requisition, Integer> implements RequisitionServiceAPI {

    private final RequisitionRepository repository;

    @Autowired
    public RequisitionServiceImpl(RequisitionRepository repository){
        this.repository = repository;
    }

    @Override
    public JpaRepository<Requisition, Integer> getRepository() {
        return repository;
    }
}
