package com.electroArreglos.controller;

import com.electroArreglos.dto.ServerResponseDataDto;
import com.electroArreglos.dto.ElectrodomesticoDto;
import com.electroArreglos.dto.TecnicoDto;
import com.electroArreglos.service.ElectrodomesticoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/electrodomestico")
public class ElectrodomesticoController {
    @Autowired
    private ElectrodomesticoService service;

    @PostMapping()
    public ServerResponseDataDto create(@RequestBody @Valid ElectrodomesticoDto dto) {

        ElectrodomesticoDto response = this.service.create(dto);

        return ServerResponseDataDto.builder()
                .data(response)
                .status(HttpStatus.OK.value())
                .message("Registro creado con exito")
                .build();

    }

    @GetMapping
    public ServerResponseDataDto findAll() {

        List<ElectrodomesticoDto> dtos = this.service.findAll();

        return ServerResponseDataDto.builder()
                .data(dtos)
                .status(HttpStatus.OK.value())
                .message("Registro encontrado")
                .build();
    }

    @GetMapping("/{id}")
    public ServerResponseDataDto findById(@PathVariable("id") Long id) {

        ElectrodomesticoDto dto = this.service.getById(id);

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
    public ServerResponseDataDto updateById(
            @PathVariable("id") Long id,
            @RequestBody @Valid ElectrodomesticoDto newData
    ) {

        ElectrodomesticoDto dto = this.service.updateById(id, newData);

        return ServerResponseDataDto.builder()
                .data(dto)
                .status(HttpStatus.OK.value())
                .message("Registro actualizado")
                .build();
    }
}
