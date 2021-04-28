package com.uniamerica.prova2.controller;

import com.uniamerica.prova2.model.Marca;
import com.uniamerica.prova2.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/marcas")
public class MarcaController {

    private final MarcaService marcaService;

    @Autowired
    public MarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    @PostMapping
    public ResponseEntity<?> insertMarca(@RequestBody Marca marca) throws Exception {
        Marca add;
        try {
            add = marcaService.insereMarca(marca);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return new ResponseEntity<>(add, null, HttpStatus.CREATED);
    }

}
