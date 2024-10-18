package com.electroArreglos.service;

import com.electroArreglos.dto.ClienteDto;
import com.electroArreglos.entity.ClienteEntity;
import com.electroArreglos.entity.RolEntity;
import com.electroArreglos.entity.UsuarioEntity;
import com.electroArreglos.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    public ClienteDto create(ClienteDto dto){

        //Crea el nuevo cliente con entity
        ClienteEntity entity = new ClienteEntity();
        entity.setId(dto.getId());
        entity.setCelular(dto.getCelular());
        entity.setCedula(dto.getCedula());
        entity.setNombre(dto.getNombre());
        entity.setIngreso(dto.getIngreso());
        entity.setDireccion(dto.getDireccion());

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setEmail(dto.getUsuarioEmail());
        usuario.setNombre(dto.getNombre());
        //usuario.setRol(dto.getRol());
        entity.setUsuarioId(usuario);

        entity = repository.save(entity);

        dto.setId(entity.getId());

        return dto;
    }
    /*Metodo para obtener todos los Clientes*/
    public List<ClienteDto> findAll() {

        List<ClienteEntity> entities = this.repository.findAll();
        List<ClienteDto> dtos = new ArrayList<>();

        for (ClienteEntity entity : entities) {

            ClienteDto dto = ClienteDto.builder()
                    .id(entity.getId())
                    .nombre(entity.getNombre())
                    .cedula(entity.getCedula())
                    .celular(entity.getCelular())
                    .direccion(entity.getDireccion())
                    .ingreso(entity.getIngreso())
                    .usuarioEmail(entity.getUsusarioEmail().getEmail())
                    .build();
            dtos.add(dto);
        }
        return dtos;
    }

    /*Método para obtener un cliente por id*/
    public ClienteDto getById (Long id) {
        Optional<ClienteEntity> optEntity =this.repository.findById(id);

        if (optEntity.isPresent()) {
            ClienteEntity entity = optEntity.get();
            return ClienteDto.builder()
                    .id(entity.getId())
                    .nombre(entity.getNombre())
                    .cedula(entity.getCedula())
                    .celular(entity.getCelular())
                    .direccion(entity.getDireccion())
                    .ingreso(entity.getIngreso())
                    .build();
        }
        return null; //Devuelve true si no se encuentra la entidad//
    }
    //Metodo para eliminar un cliente por ID

    public ClienteDto deleteById(Long id) {
        if (id <= 0) {
            return null;  // Devuelve null si el ID no es válido
        }

        Optional<ClienteEntity> optEntity = this.repository.findById(id);

        if (!optEntity.isPresent()) {
            return null;  // Devuelve null si el registro no se encuentra
        }

        ClienteEntity entity = optEntity.get();
        ClienteDto dto = ClienteDto.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .cedula(entity.getCedula())
                .celular(entity.getCelular())
                .build();

        this.repository.deleteById(id);  // Elimina la entidad de la base de datos

        return dto;  // Devuelve el AdministradorDto eliminado
    }


    // Método para actualizar un administrador por ID
    public ClienteDto updateById(Long id, ClienteDto newData) {
        if(id <= 0){
            return null;
        }
        Optional<ClienteEntity> optEntity = this.repository.findById(id);

        if (!optEntity.isPresent()){
            return null;
        }

        ClienteEntity entity = optEntity.get();
        entity.setNombre(newData.getNombre());
        entity.setCedula(newData.getCedula());
        entity.setCelular(newData.getCelular());
        entity.setDireccion(newData.getDireccion());

        this.repository.save(entity); // Guardamos los cambios
        newData.setId(entity.getId()); // Actualizamos el DTO con el ID

        return newData;

    }
}


