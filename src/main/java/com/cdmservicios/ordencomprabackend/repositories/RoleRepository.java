package com.cdmservicios.ordencomprabackend.repositories;

import com.cdmservicios.ordencomprabackend.models.ERole;
import com.cdmservicios.ordencomprabackend.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
