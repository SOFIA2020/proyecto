package com.electroArreglos.service;

import com.electroArreglos.dto.TecnicoDto;
import com.electroArreglos.entity.TecnicoEntity;
import com.electroArreglos.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    public TecnicoDto create(TecnicoDto dto){

        TecnicoEntity entity = new TecnicoEntity();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setCedula(dto.getCedula());
        entity.setCelular(dto.getCelular());
        entity.setTitulo(dto.getTitulo());

        repository.save(entity);

        return dto;
    }
    /*Metodo para obtener todos los Tecnico*/
    public List<TecnicoDto> findAll() {

        List<TecnicoEntity> entities = this.repository.findAll();
        List<TecnicoDto> dtos = new ArrayList<>();

        for (TecnicoEntity entity : entities) {

            TecnicoDto dto = TecnicoDto.builder()
                    .id(entity.getId())
                    .nombre(entity.getNombre())
                    .cedula(entity.getCedula())
                    .celular(entity.getCelular())
                    .titulo(entity.getTitulo())
                    .usuarioEmail(entity.getUsusarioEmail().getEmail())
                    .usuarioId(entity.getUsuarioId().getId())
                    .build();
            dtos.add(dto);
        }
        return dtos;
    }
    /*Método para obtener un Tecnico por id*/
    public TecnicoDto getById (Long id) {
        Optional<TecnicoEntity> optEntity =this.repository.findById(id);

        if (optEntity.isPresent()) {
            TecnicoEntity entity = optEntity.get();
            return TecnicoDto.builder()
                    .id(entity.getId())
                    .nombre(entity.getNombre())
                    .cedula(entity.getCedula())
                    .celular(entity.getCelular())
                    .titulo(entity.getTitulo())
                    .build();
        }
        return null; //Devuelve true si no se encuentra la entidad//
    }
    //Metodo para eliminar un Tecnico por ID

    public TecnicoDto deleteById(Long id) {
        if (id <= 0) {
            return null;  // Devuelve null si el ID no es válido
        }

        Optional<TecnicoEntity> optEntity = this.repository.findById(id);

        if (!optEntity.isPresent()) {
            return null;  // Devuelve null si el registro no se encuentra
        }

        TecnicoEntity entity = optEntity.get();
        TecnicoDto dto = TecnicoDto.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .cedula(entity.getCedula())
                .celular(entity.getCelular())
                .titulo(entity.getTitulo())
                .build();

        this.repository.deleteById(id);  // Elimina la entidad de la base de datos

        return dto;  // Devuelve el AdministradorDto eliminado
    }


    // Método para actualizar un administrador por ID
    public TecnicoDto updateById(Long id, TecnicoDto newData) {
        if(id <= 0){
            return null;
        }
        Optional<TecnicoEntity> optEntity = this.repository.findById(id);

        if (!optEntity.isPresent()){
            return null;
        }

        TecnicoEntity entity = optEntity.get();
        entity.setNombre(newData.getNombre());
        entity.setCedula(newData.getCedula());
        entity.setCelular(newData.getCelular());
        entity.setTitulo(newData.getTitulo());

        this.repository.save(entity); // Guardamos los cambios
        newData.setId(entity.getId()); // Actualizamos el DTO con el ID

        return newData;

    }
}

