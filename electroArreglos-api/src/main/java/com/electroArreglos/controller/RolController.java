package com.electroArreglos.controller;

import com.electroArreglos.dto.RolDto;
import com.electroArreglos.dto.ServerResponseDataDto;
import com.electroArreglos.dto.ServerResponseDto;
import com.electroArreglos.service.RolService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rol")
public class RolController {

    @Autowired
    private RolService service;

    @GetMapping
    public ServerResponseDataDto findAll () {

        List<RolDto> dtos = this.service.findAll();

        return ServerResponseDataDto.builder()
                .data(dtos)
                .status(HttpStatus.OK.value())
                .message("Registro encontrado")
                .build();
        }

    }
