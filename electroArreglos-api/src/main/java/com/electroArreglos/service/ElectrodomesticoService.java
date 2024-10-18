package com.electroArreglos.service;

import com.electroArreglos.dto.AdministradorDto;
import com.electroArreglos.dto.ClienteDto;
import com.electroArreglos.dto.ElectrodomesticoDto;
import com.electroArreglos.entity.ClienteEntity;
import com.electroArreglos.entity.ElectrodomesticoEntity;
import com.electroArreglos.repository.ElectrodomesticoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ElectrodomesticoService {

    @Autowired
    private ElectrodomesticoRepository repository;

    public ElectrodomesticoDto create(ElectrodomesticoDto dto){

        ElectrodomesticoEntity entity = new ElectrodomesticoEntity();
        entity.setId(dto.getId());
        entity.setIngreso(dto.getIngreso());
        entity.setMarca(dto.getMarca());
        entity.setSerial(dto.getSerial());
        entity.setReferencia(dto.getReferencia());

        repository.save(entity);

        dto.setId(entity.getId());

        return dto;

    }
    /*Metodo para obtener todos los Administradores*/
    public List<ElectrodomesticoDto> findAll() {

        List<ElectrodomesticoEntity> entities = this.repository.findAll();
        List<ElectrodomesticoDto> dtos = new ArrayList<>();

        for (ElectrodomesticoEntity entity : entities) {

            ElectrodomesticoDto dto = ElectrodomesticoDto.builder()
                    .id(entity.getId())
                    .ingreso(entity.getIngreso())
                    .marca(entity.getMarca())
                    .serial(entity.getSerial())
                    .referencia(entity.getReferencia())
                    .build();
            dtos.add(dto);
        }
        return dtos;
    }

    /*Método para obtener un cliente por id*/
    public ElectrodomesticoDto getById (Long id) {
        Optional<ElectrodomesticoEntity> optEntity =this.repository.findById(id);

        if (optEntity.isPresent()) {
            ElectrodomesticoEntity entity = optEntity.get();
            return ElectrodomesticoDto.builder()
                    .id(entity.getId())
                    .ingreso(entity.getIngreso())
                    .marca(entity.getMarca())
                    .serial(entity.getSerial())
                    .referencia(entity.getReferencia())
                    .build();
        }
        return null; //Devuelve true si no se encuentra la entidad//
    }
    //Metodo para eliminar un cliente por ID

    public ElectrodomesticoDto deleteById(Long id) {
        if (id <= 0) {
            return null;  // Devuelve null si el ID no es válido
        }

        Optional<ElectrodomesticoEntity> optEntity = this.repository.findById(id);

        if (!optEntity.isPresent()) {
            return null;  // Devuelve null si el registro no se encuentra
        }

        ElectrodomesticoEntity entity = optEntity.get();
        ElectrodomesticoDto dto = ElectrodomesticoDto.builder()
                .id(entity.getId())
                .ingreso(entity.getIngreso())
                .marca(entity.getMarca())
                .serial(entity.getSerial())
                .referencia(entity.getReferencia())
                .build();

        this.repository.deleteById(id);  // Elimina la entidad de la base de datos

        return dto;  // Devuelve el AdministradorDto eliminado
    }


    // Método para actualizar un administrador por ID
    public ElectrodomesticoDto updateById(Long id, ElectrodomesticoDto newData) {
        if(id <= 0){
            return null;
        }
        Optional<ElectrodomesticoEntity> optEntity = this.repository.findById(id);

        if (!optEntity.isPresent()){
            return null;
        }

        ElectrodomesticoEntity entity = optEntity.get();
        entity.setIngreso(newData.getIngreso());
        entity.setMarca(newData.getMarca());
        entity.setSerial(newData.getSerial());
        entity.setReferencia(newData.getReferencia());

        this.repository.save(entity); // Guardamos los cambios
        newData.setId(entity.getId()); // Actualizamos el DTO con el ID

        return newData;

    }
}

