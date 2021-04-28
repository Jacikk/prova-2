package com.uniamerica.prova2.service;

import com.uniamerica.prova2.model.Modelo;
import com.uniamerica.prova2.repository.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModeloService {

    private final ModeloRepository modeloRepository;

    @Autowired
    public ModeloService(ModeloRepository modeloRepository) {
        this.modeloRepository = modeloRepository;
    }

    public Modelo insereModelo(Modelo modelo){
        return modeloRepository.save(modelo);
    }
}
