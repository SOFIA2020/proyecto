package com.electroArreglos.controller;

import com.electroArreglos.Confi.Constantes;
import com.electroArreglos.dto.ServerResponseDataDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping("/upload")
    public ServerResponseDataDto handleFileUpload(@RequestParam("file") MultipartFile file ) {

        String fileName = Math.random()*10000 + "-" + file.getOriginalFilename();
        try {
            String basePathToUpload = Constantes.UPLOAD_PATH;
            String newPath =  basePathToUpload + fileName;
            System.out.println("Aqui" + newPath);
            file.transferTo( new File(newPath));
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponseDataDto.builder()
                    .message("Error al subir")
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();
        }
        return ServerResponseDataDto.builder()
                .message("Exitoso")
                .data(fileName)
                .status(HttpStatus.OK.value()).build();
    }

}
