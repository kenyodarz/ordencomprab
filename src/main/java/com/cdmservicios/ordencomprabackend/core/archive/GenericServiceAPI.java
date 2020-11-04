package com.cdmservicios.ordencomprabackend.core.archive;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface GenericServiceAPI<T, ID extends Serializable> {


    T save (T entity);

    void delete (ID id);

    T get(ID id);

    List<T> getAll();

    JpaRepository<T, ID> getRepository();
}
