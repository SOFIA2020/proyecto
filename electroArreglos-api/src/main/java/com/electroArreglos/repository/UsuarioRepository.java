package com.electroArreglos.repository;

import com.electroArreglos.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends
        JpaRepository<UsuarioEntity, Long>,
        JpaSpecificationExecutor<UsuarioEntity> { //Mejoramiento de las consultas
    UsuarioEntity findByEmail(String email);
}
