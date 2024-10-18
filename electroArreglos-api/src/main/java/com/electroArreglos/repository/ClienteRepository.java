package com.electroArreglos.repository;

import com.electroArreglos.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends
        JpaRepository<ClienteEntity, Long>,
        JpaSpecificationExecutor<ClienteEntity> {
}
