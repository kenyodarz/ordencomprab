package com.cdmservicios.ordencomprabackend.core.archive;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public abstract class GenericServiceImpl<T, ID extends Serializable> implements GenericServiceAPI<T, ID> {

    @Override
    public T save(T entity) {
        return getRepository().save(entity);
    }

    @Override
    public void delete(ID id) {
        getRepository().deleteById(id);
    }

    @Override
    public T get(ID id) {
        Optional<T> optional = getRepository().findById(id);
        return optional.orElse(null);
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<>(getRepository().findAll());
    }

    @Override
    public abstract JpaRepository<T, ID> getRepository();
}
