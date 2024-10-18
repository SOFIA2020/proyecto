package com.electroArreglos.repository;

import com.electroArreglos.entity.EstadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends
        JpaRepository<EstadoEntity, Long>,
        JpaSpecificationExecutor<EstadoEntity> {
}
