package com.cdmservicios.ordencomprabackend.services.providers;

import com.cdmservicios.ordencomprabackend.core.archive.GenericServiceImpl;
import com.cdmservicios.ordencomprabackend.models.RecepcionDePedidos;
import com.cdmservicios.ordencomprabackend.repositories.RecepcionDePedidosRepository;
import com.cdmservicios.ordencomprabackend.services.apis.RecepcionDePedidosServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class RecepcionDePedidosServiceImpl extends GenericServiceImpl<RecepcionDePedidos, Integer> implements RecepcionDePedidosServiceAPI {

    private final RecepcionDePedidosRepository repository;

    @Autowired
    public RecepcionDePedidosServiceImpl(RecepcionDePedidosRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<RecepcionDePedidos, Integer> getRepository() {
        return repository;
    }

    @NotNull
    @Transactional
    @Override
    public List<RecepcionDePedidos> findRecepcionDePedidosByPedido(Integer idpedido){
        return this.repository.findRecepcionDePedidosByPedido(idpedido);
    }

    @NotNull
    @Transactional
    @Override
    public Integer RecepcionesPorPedidos(Integer idPedido) {
        return this.repository.RecepcionesPorPedidos(idPedido);
    }
}
