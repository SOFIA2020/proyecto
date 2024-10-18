package com.electroArreglos.controller;

import com.electroArreglos.dto.ServerResponseDataDto;
import com.electroArreglos.dto.AdministradorDto;
import com.electroArreglos.service.AdministradorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/administrador")
public class AdministradorController {

    @Autowired
    private AdministradorService service;

    @PostMapping()
    public ServerResponseDataDto create(@RequestBody @Valid AdministradorDto dto) {

       AdministradorDto response = this.service.create(dto);

        return ServerResponseDataDto.builder()
                .data(response)
                .status(HttpStatus.OK.value())
                .message("Registro creado con exito")
                .data(response)
                .build();

    }
    @GetMapping
    public ServerResponseDataDto findAll() {
        List<AdministradorDto> dtos = this.service.findAll();

        return ServerResponseDataDto.builder()
                .data(dtos)
                .status(HttpStatus.OK.value())
                .message("Registro encontrado")
                .build();
    }

    @GetMapping("/{id}")
    public ServerResponseDataDto findById(@PathVariable("id") Long id) {

        AdministradorDto dto = this.service.getById(id);

        return ServerResponseDataDto.builder()
                .data(dto)
                .status(HttpStatus.OK.value())
                .message("Registro encontrado")
                .build();
    }

    @DeleteMapping("/{id}")
    public ServerResponseDataDto deleteById(@PathVariable("id") Long id){

        return ServerResponseDataDto.builder()
                .data(this.service.deleteById(id))
                .status(HttpStatus.OK.value())
                .message("Registro eliminado")
                .build();
    }

    @PutMapping("/{id}")
    public ServerResponseDataDto updateById(@PathVariable("id") Long id,
            @RequestBody @Valid AdministradorDto newData) {

        AdministradorDto dto = this.service.updateById(id, newData);

        return ServerResponseDataDto.builder()
                .data(dto)
                .status(HttpStatus.OK.value())
                .message("Registro actualizado")
                .build();
    }
}