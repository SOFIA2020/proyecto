package com.electroArreglos.repository;

import com.electroArreglos.entity.ElectrodomesticoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectrodomesticoRepository extends
        JpaRepository<ElectrodomesticoEntity, Long>,
        JpaSpecificationExecutor<ElectrodomesticoEntity>{
}
