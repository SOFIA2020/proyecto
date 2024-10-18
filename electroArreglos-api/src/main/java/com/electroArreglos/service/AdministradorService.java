package com.electroArreglos.service;

import com.electroArreglos.dto.AdministradorDto;
import com.electroArreglos.entity.AdministradorEntity;
import com.electroArreglos.entity.RolEntity;
import com.electroArreglos.entity.UsuarioEntity;
import com.electroArreglos.repository.AdministradorRepository;
import com.electroArreglos.repository.UsuarioRepository;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository repository;

    @Autowired
    private RolService rolService;

    @Autowired
    private UsuarioService usuarioService;

    /*Metodo para crear un administrador*/
    public AdministradorDto create(AdministradorDto dto) {

        AdministradorEntity entity = new AdministradorEntity();
        entity.setNombre(dto.getNombre());
        entity.setCedula(dto.getCedula());
        entity.setCelular(dto.getCelular());

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setEmail(dto.getUsuarioEmail());
        usuario.setId(dto.getUsuarioId());
        //usuario.setRol(dto.getRol());

        entity = repository.save(entity);

        dto.setId(entity.getId());

        return dto;
    }
    /*Metodo para obtener todos los Administradores*/
    public List<AdministradorDto> findAll() {

        List<AdministradorEntity> entities = this.repository.findAll();
        List<AdministradorDto> dtos = new ArrayList<>();

        for (AdministradorEntity entity : entities) {

            AdministradorDto dto = AdministradorDto.builder()
                    .id(entity.getId())
                    .nombre(entity.getNombre())
                    .cedula(entity.getCedula())
                    .celular(entity.getCelular())
                    .usuarioEmail(entity.getUsusarioEmail().getEmail())
                    .usuarioId(entity.getUsuarioId().getId())
                    .build();

            dtos.add(dto);
        }
        return dtos;
    }

    /*Método para obtener un administrador por id*/
    public AdministradorDto getById(Long id) {
        Optional<AdministradorEntity> optEntity =this.repository.findById(id);
        /*AdministradorEntity entity = this.repository.findById(id).get();
        AdministradorDto dto = AdministradorDto.builder()*/

        if (optEntity.isPresent()) {
            AdministradorEntity entity = optEntity.get();
        return AdministradorDto.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .cedula(entity.getCedula())
                .celular(entity.getCelular())
                .usuarioEmail(entity.getUsusarioEmail().getEmail())
                .usuarioId(entity.getUsuarioId().getId())
                .build();
        }
        return null; //Devuelve true si no se encuentra la entidad//
    }
    //Metodo para eliminar un administrador por ID

     public AdministradorDto deleteById(Long id) {
        if (id <= 0) {
            return null;  // Devuelve null si el ID no es válido
        }

        Optional<AdministradorEntity> optEntity = this.repository.findById(id);

        if (!optEntity.isPresent()) {
            return null;  // Devuelve null si el registro no se encuentra
        }

        AdministradorEntity entity = optEntity.get();
        AdministradorDto dto = AdministradorDto.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .cedula(entity.getCedula())
                .celular(entity.getCelular())
                .usuarioEmail(entity.getUsusarioEmail().getEmail())
                .usuarioId(entity.getUsuarioId().getId())
                .build();

        this.repository.deleteById(id);  // Elimina la entidad de la base de datos

        return dto;  // Devuelve el AdministradorDto eliminado
    }
    // Método para actualizar un administrador por ID
    public AdministradorDto updateById(Long id, AdministradorDto newData) {
        if(id <= 0){
            return null;
        }
        Optional<AdministradorEntity> optEntity = this.repository.findById(id);

        if (!optEntity.isPresent()){
            return null;
        }

        AdministradorEntity entity = optEntity.get();
        entity.setNombre(newData.getNombre());
        entity.setCedula(newData.getCedula());
        entity.setCelular(newData.getCelular());

        this.repository.save(entity); // Guardamos los cambios
        newData.setId(entity.getId()); // Actualizamos el DTO con el ID

        return newData;

    }
}
