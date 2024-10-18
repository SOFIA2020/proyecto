package com.electroArreglos.service;

import com.electroArreglos.dto.AdministradorDto;
import com.electroArreglos.dto.EstadoDto;
import com.electroArreglos.entity.AdministradorEntity;
import com.electroArreglos.entity.EstadoEntity;
import com.electroArreglos.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository repository;

    @Autowired
    private ClienteService clienteService;
    public EstadoDto create(EstadoDto dto) {

        EstadoEntity entity = new EstadoEntity();
        entity.setId(dto.getId());
        entity.setEstadoReparacion(dto.getEstadoReparacion());
        entity.setFechaIngreso(dto.getFechaIngreso());
        entity.setFechaSalida(dto.getFechaSalida());

        repository.save(entity);

        return dto;
    }
    /*Metodo para obtener todos los Estados*/
    public List<EstadoDto> findAll() {

        List<EstadoEntity> entities = this.repository.findAll();
        List<EstadoDto> dtos = new ArrayList<>();

        for (EstadoEntity entity : entities) {

            EstadoDto dto = EstadoDto.builder()
                    .id(entity.getId())
                    .estadoReparacion(entity.getEstadoReparacion())
                    .fechaIngreso(entity.getFechaIngreso())
                    .fechaSalida(entity.getFechaSalida())
                    .build();
            dtos.add(dto);
        }
        return dtos;
    }

    /*Método para obtener un Estado por id*/
    public EstadoDto getById(Long id) {
        Optional<EstadoEntity> optEntity =this.repository.findById(id);
        /*EstadoEntity entity = this.repository.findById(id).get();
        EstadoDto dto = EstadoDto.builder()*/

        if (optEntity.isPresent()) {
            EstadoEntity entity = optEntity.get();
            return EstadoDto.builder()
                    .id(entity.getId())
                    .estadoReparacion(entity.getEstadoReparacion())
                    .fechaIngreso(entity.getFechaIngreso())
                    .fechaSalida(entity.getFechaSalida())
                    .build();
        }
        return null; //Devuelve true si no se encuentra la entidad//
    }
    //Metodo para eliminar un Estado por ID

    public EstadoDto deleteById(Long id) {
        if (id <= 0) {
            return null;  // Devuelve null si el ID no es válido
        }

        Optional<EstadoEntity> optEntity = this.repository.findById(id);

        if (!optEntity.isPresent()) {
            return null;  // Devuelve null si el registro no se encuentra
        }

        EstadoEntity entity = optEntity.get();
        EstadoDto dto = EstadoDto.builder()
                .id(entity.getId())
                .estadoReparacion(entity.getEstadoReparacion())
                .fechaIngreso(entity.getFechaIngreso())
                .fechaSalida(entity.getFechaSalida())
                .build();

        this.repository.deleteById(id);  // Elimina la entidad de la base de datos

        return dto;  // Devuelve el EstadoDto eliminado
    }
    // Método para actualizar un Estado por ID
    public EstadoDto updateById(Long id, EstadoDto newData) {
        if(id <= 0){
            return null;
        }
        Optional<EstadoEntity> optEntity = this.repository.findById(id);

        if (!optEntity.isPresent()){
            return null;
        }

        EstadoEntity entity = optEntity.get();
        entity.setEstadoReparacion(newData.getEstadoReparacion());
        entity.setFechaIngreso(newData.getFechaIngreso());
        entity.setFechaSalida(newData.getFechaSalida());

        this.repository.save(entity); // Guardamos los cambios
        newData.setId(entity.getId()); // Actualizamos el DTO con el ID

        return newData;

    }
}
