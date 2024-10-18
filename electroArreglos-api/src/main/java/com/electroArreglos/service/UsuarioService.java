package com.electroArreglos.service;

import com.electroArreglos.repository.UsuarioRepository;
import com.electroArreglos.entity.RolEntity;
import com.electroArreglos.dto.UsuarioDto;
import com.electroArreglos.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private RolService rolService;

    public boolean validateByEmail(String email) {
        UsuarioEntity entity = this.repository.findByEmail(email);
        if (Objects.isNull(entity)) {
            return false;
        }
        return true;
    }
    public UsuarioDto create(UsuarioDto dto){

        UsuarioEntity entity = new UsuarioEntity();
        entity.setNombre(dto.getNombre());
        entity.setEmail(dto.getEmail());
        entity.setAvatar(dto.getAvatar());

        RolEntity rol = new RolEntity();
        rol.setId(dto.getRolId());
        entity.setRol(rol);

        entity = repository.save(entity);

        dto.setId(entity.getId());
        return dto;
    }
    /*Metodo para obtener todos los Usuario*/
    public List<UsuarioDto> findAll() {

        List<UsuarioEntity> entities = this.repository.findAll();
        List<UsuarioDto> dtos = new ArrayList<>();

        for (UsuarioEntity entity : entities) {

            UsuarioDto dto = UsuarioDto.builder()
                    .id(entity.getId())
                    .nombre(entity.getNombre())
                    .email(entity.getEmail())
                    .avatar(entity.getAvatar())
                    .rolId(entity.getRol().getId())
                    .rolNombre(entity.getRol().getTitle())
                    .build();
            dtos.add(dto);
        }
        return dtos;
    }
    /*Método para obtener un Usuario por id*/
    public UsuarioDto getById (Long id) {
        Optional<UsuarioEntity> optEntity =this.repository.findById(id);

        if (optEntity.isPresent()) {
            UsuarioEntity entity = optEntity.get();
            return UsuarioDto.builder()
                    .id(entity.getId())
                    .nombre(entity.getNombre())
                    .email(entity.getEmail())
                    .avatar(entity.getAvatar())
                    .rolId(entity.getRol().getId())
                    .build();
        }
        return null; //Devuelve true si no se encuentra la entidad//
    }

    //Metodo para eliminar un Usuario por ID
    public UsuarioDto deleteById(Long id) {
        if (id <= 0) {
            return null;  // Devuelve null si el ID no es válido
        }

        Optional<UsuarioEntity> optEntity = this.repository.findById(id);
        if (!optEntity.isPresent()) {
            return null;  // Devuelve null si el registro no se encuentra
        }
        UsuarioEntity entity = optEntity.get();
        UsuarioDto dto = UsuarioDto.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .email(entity.getEmail())
                .avatar(entity.getAvatar())
                .rolId(entity.getRol().getId())
                .build();

        this.repository.deleteById(id);  // Elimina la entidad de la base de datos

        return dto;  // Devuelve el UsuarioDto eliminado
    }
    // Método para actualizar un Usuario por ID
    public UsuarioDto updateById(Long id, UsuarioDto newData) {
        if(id <= 0){
            return null;
        }
        Optional<UsuarioEntity> optEntity = this.repository.findById(id);

        if (!optEntity.isPresent()){
            return null;
        }

        UsuarioEntity entity = optEntity.get(); //Ya tiene internamente el id

        entity.setNombre(newData.getNombre());
        entity.setEmail(newData.getEmail());
        entity.setAvatar(newData.getAvatar());

        RolEntity rol = rolService.getRolById(newData.getRolId());
        entity.setRol(rol);

        this.repository.save(entity); // Guardamos los cambios

        newData.setId(entity.getId()); // Actualizamos el DTO con el ID

        return newData;

    }
}


