package com.cdmservicios.ordencomprabackend.repositories;

import com.cdmservicios.ordencomprabackend.models.Cotizaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CotizacionesRepository extends JpaRepository<Cotizaciones, Integer> {
}
