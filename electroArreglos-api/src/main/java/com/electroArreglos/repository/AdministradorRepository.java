package com.electroArreglos.repository;

import com.electroArreglos.entity.AdministradorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepository extends
        JpaRepository<AdministradorEntity, Long>,
        JpaSpecificationExecutor<AdministradorEntity> {

}



