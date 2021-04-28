package com.uniamerica.prova2.service;

import com.uniamerica.prova2.model.Marca;
import com.uniamerica.prova2.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarcaService {

    private final MarcaRepository marcaRepository;

    @Autowired
    public MarcaService(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }


    public Marca insereMarca(Marca marca){
        return marcaRepository.save(marca);
    }

    public Marca getById(long id) {
        return marcaRepository.getOne(id);
    }
}
