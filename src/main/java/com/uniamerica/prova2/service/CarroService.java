package com.uniamerica.prova2.service;

import com.uniamerica.prova2.model.Carro;
import com.uniamerica.prova2.model.Marca;
import com.uniamerica.prova2.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarroService {

    private final CarroRepository carroRepository;

    @Autowired
    public CarroService(CarroRepository carroRepository) {
        this.carroRepository = carroRepository;
    }

    public Carro insereCarro(Carro carro){
        return carroRepository.save(carro);
    }


    public List<Carro> getCarrosByMarca(Marca marca) {

        List<Carro> carros = carroRepository.findAll();

        return carros.stream()
                .filter(carro -> carro.getModelo().getMarca().equals(marca))
                .collect(Collectors.toList());
    }

}
