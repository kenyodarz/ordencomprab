package com.cdmservicios.ordencomprabackend.repositories;

import com.cdmservicios.ordencomprabackend.models.CentroDeCostos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CentroDeCostosRepository extends JpaRepository<CentroDeCostos, Long> {
}
