package com.uniamerica.prova2.service;

import com.uniamerica.prova2.controller.CarroController;
import com.uniamerica.prova2.model.Carro;
import com.uniamerica.prova2.model.Reserva;
import com.uniamerica.prova2.repository.CarroRepository;
import com.uniamerica.prova2.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    private final CarroRepository carroRepository;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository, CarroRepository carroRepository) {
        this.reservaRepository = reservaRepository;
        this.carroRepository = carroRepository;
    }

    public Reserva insereReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public List<Reserva> findByCarro(Carro carro) {
        return reservaRepository.findByCarro(carro);
    }

    public List<Reserva> findAll() {
        return reservaRepository.findAll();
    }

    public Reserva findById(Long id) {
        Optional<Reserva> found = reservaRepository.findById(id);

        if(found.isPresent()) return found.get();
        else return null;
    }

    public boolean VerificarData(Reserva reserva){

        List<Reserva> temp = reservaRepository.findByCarro(reserva.getCarro());

        Calendar verificarDiaRetirada = Calendar.getInstance();
        verificarDiaRetirada.setTime(reserva.getDataDeRetirada());

        Calendar verificarDiaDevoluacao = Calendar.getInstance();
        verificarDiaDevoluacao.setTime(reserva.getDataDeDevolucao());

        boolean disponivel = false;
        if(temp.isEmpty()) disponivel = true;
        else for (Reserva bd:temp) {

            Calendar diaRetirada = Calendar.getInstance();
            diaRetirada.setTime(bd.getDataDeRetirada());

            Calendar diaDevolucao = Calendar.getInstance();
            diaDevolucao.setTime(bd.getDataDeDevolucao());

            if(verificarDiaDevoluacao.after(diaRetirada) && verificarDiaDevoluacao.before(diaDevolucao)){
                disponivel = false;
            } else if(verificarDiaRetirada.after(diaRetirada) && verificarDiaRetirada.before(diaDevolucao)){
                disponivel = false;
            } else if(diaRetirada.after(verificarDiaRetirada) && diaRetirada.before(diaDevolucao)){
                disponivel = false;
            } else if(diaDevolucao.after(verificarDiaRetirada) && diaDevolucao.before(verificarDiaDevoluacao)){
                disponivel = false;
            } if(diaDevolucao.equals(verificarDiaDevoluacao) || diaRetirada.equals(verificarDiaRetirada) ){
                disponivel = false;
            } else {
                disponivel = true;
            }
        }

        if(disponivel) return true;
        else return false;
    }

    public List<Carro> veiculosDisponiveisNoPeriodo(Calendar inicio, Calendar fim) {

        List<Reserva> reservas = reservaRepository.findAll();
        List<Reserva> reservaForaDoPeriodo = new ArrayList<>();
        
        List<Carro> carrosDisponiveis = new ArrayList<>();

        for (Reserva bd:reservas) {

            Calendar diaRetirada = Calendar.getInstance();
            diaRetirada.setTime(bd.getDataDeRetirada());

            Calendar diaDevolucao = Calendar.getInstance();
            diaDevolucao.setTime(bd.getDataDeDevolucao());

            if(fim.after(diaRetirada) && fim.before(diaDevolucao)){

            } else if(inicio.after(diaRetirada) && inicio.before(diaDevolucao)){

            } else if(diaRetirada.after(inicio) && diaRetirada.before(diaDevolucao)){

            } else if(diaDevolucao.after(inicio) && diaDevolucao.before(fim)){

            } else {

            }
        }
        return carrosDisponiveis;
    }
}
