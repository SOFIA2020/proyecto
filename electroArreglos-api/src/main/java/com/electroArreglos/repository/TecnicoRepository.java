package com.electroArreglos.repository;

import com.electroArreglos.entity.TecnicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TecnicoRepository extends
        JpaRepository<TecnicoEntity, Long>,
        JpaSpecificationExecutor<TecnicoEntity> {
}
