package com.electroArreglos.service;

import com.electroArreglos.dto.AdministradorDto;
import com.electroArreglos.dto.RolDto;
import com.electroArreglos.entity.AdministradorEntity;
import com.electroArreglos.entity.RolEntity;
import com.electroArreglos.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RolService {

    @Autowired
    private RolRepository repository;

    public RolDto create(RolDto dto){

        RolEntity entity = new RolEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());

        repository.save(entity);

        return dto;
    }
    /*Metodo para obtener todos los Rol*/
    public List<RolDto> findAll() {

        List<RolEntity> entities = this.repository.findAll();
        List<RolDto> dtos = new ArrayList<>();

        for (RolEntity entity : entities) {

            RolDto dto = RolDto.builder()
                    .id(entity.getId())
                    .title(entity.getTitle())
                    .build();
            dtos.add(dto);
        }
        return dtos;
    }

    /*Método para obtener un administrador por id*/
    public RolDto getById(Long id) {
        Optional<RolEntity> optEntity =this.repository.findById(id);

        if (optEntity.isPresent()) {
            RolEntity entity = optEntity.get();
            return RolDto.builder()
                    .id(entity.getId())
                    .title(entity.getTitle())
                    .build();
        }
        return null; //Devuelve true si no se encuentra la entidad//
    }

    public RolEntity getRolById(Long id) {
        Optional<RolEntity> optionalRolEntity = this.repository.findById(id);
        return optionalRolEntity.get();
    }

    //Metodo para eliminar un Rol por ID
    public RolDto deleteById(Long id) {
        if (id <= 0) {
            return null;  // Devuelve null si el ID no es válido
        }

        Optional<RolEntity> optEntity = this.repository.findById(id);

        if (!optEntity.isPresent()) {
            return null;  // Devuelve null si el registro no se encuentra
        }

        RolEntity entity = optEntity.get();
        RolDto dto = RolDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .build();

        this.repository.deleteById(id);  // Elimina la entidad de la base de datos

        return dto;  // Devuelve el RolDto eliminado
    }
    // Método para actualizar un Rol por ID
    public RolDto updateById(Long id, RolDto newData) {
        if(id <= 0){
            return null;
        }
        Optional<RolEntity> optEntity = this.repository.findById(id);

        if (!optEntity.isPresent()){
            return null;
        }

        RolEntity entity = optEntity.get();
        entity.setId(newData.getId());
        entity.setTitle(newData.getTitle());

        this.repository.save(entity); // Guardamos los cambios
        newData.setId(entity.getId()); // Actualizamos el DTO con el ID

        return newData;

    }
}

