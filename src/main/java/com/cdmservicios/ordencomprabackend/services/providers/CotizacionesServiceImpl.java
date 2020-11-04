package com.cdmservicios.ordencomprabackend.services.providers;

import com.cdmservicios.ordencomprabackend.core.archive.GenericServiceImpl;
import com.cdmservicios.ordencomprabackend.models.Cotizaciones;
import com.cdmservicios.ordencomprabackend.repositories.CotizacionesRepository;
import com.cdmservicios.ordencomprabackend.services.apis.CotizacionesServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CotizacionesServiceImpl extends GenericServiceImpl<Cotizaciones, Integer>  implements CotizacionesServiceAPI {

    private final CotizacionesRepository repository;

    @Autowired
    public CotizacionesServiceImpl(CotizacionesRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<Cotizaciones, Integer> getRepository() {
        return repository;
    }
}
