package com.uniamerica.prova2.controller;

import com.uniamerica.prova2.model.Modelo;
import com.uniamerica.prova2.service.ModeloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/modelos")
public class ModeloController {

    private final ModeloService modeloService;

    @Autowired
    public ModeloController(ModeloService modeloService) {
        this.modeloService = modeloService;
    }

    @PostMapping
    public ResponseEntity<?> insertModelo(@RequestBody Modelo modelo) throws Exception {
        Modelo add;
        try {
            add = modeloService.insereModelo(modelo);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return new ResponseEntity<>(add, null, HttpStatus.CREATED);
    }
}
