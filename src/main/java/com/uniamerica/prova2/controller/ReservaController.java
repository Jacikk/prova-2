package com.uniamerica.prova2.controller;

import com.uniamerica.prova2.model.Carro;
import com.uniamerica.prova2.model.Reserva;
import com.uniamerica.prova2.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/reserva")
public class ReservaController {

    private final ReservaService reservaService;

    @Autowired
    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping
    public ResponseEntity<?> insereReserva(@RequestBody Reserva reserva) throws Exception {
        try{
            boolean disponivel = reservaService.VerificarData(reserva);

            if(disponivel) {
                reserva.setStatus(Reserva.Status.RESERVADO);
                Reserva add = reservaService.insereReserva(reserva);
                return new ResponseEntity<>(add, null, HttpStatus.CREATED);
            } else return new ResponseEntity<>("Já reservado nesse horário", null, HttpStatus.OK);
        }
        catch( Exception ex){
            throw new Exception(ex);
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll () throws Exception{
        try{
            List<Reserva> found = reservaService.findAll();

            if(!found.isEmpty()) return new ResponseEntity<>(found, null, HttpStatus.OK);
            else return new ResponseEntity<>(found, null, HttpStatus.NO_CONTENT);
        }
        catch (Exception ex){
            throw new Exception(ex);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) throws Exception{
        try{
            Reserva found = reservaService.findById(id);
            if(found != null) return new ResponseEntity<>(found, null, HttpStatus.OK);
            else return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
        catch (Exception ex){
            throw new Exception(ex);
        }
    }

    @PutMapping("/retirar/{id}")
    public ResponseEntity<?> retirar (@PathVariable Long id) throws Exception {
        try{
            Reserva reserva = reservaService.findById(id);
            if(reserva != null) {
                if(reserva.getStatus() == Reserva.Status.RESERVADO) {
                    reserva.setStatus(Reserva.Status.EM_ANDAMENTO);
                    reservaService.insereReserva(reserva);
                    return new ResponseEntity<>(reserva, null, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Já em andamento", null, HttpStatus.BAD_REQUEST);
                }
            }else {
                return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception ex){
            throw new Exception(ex);
        }
    }

    @PutMapping("/finalizar/{id}")
    public ResponseEntity<?> finalizar (@PathVariable Long id) throws Exception {
        try{
            Reserva reserva = reservaService.findById(id);
            if(reserva != null) {
                if(reserva.getStatus() == Reserva.Status.EM_ANDAMENTO) {
                    reserva.setStatus(Reserva.Status.FINALIZADO);
                    reservaService.insereReserva(reserva);
                    return new ResponseEntity<>(reserva, null, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Já Finalizado", null, HttpStatus.BAD_REQUEST);
                }
            }else {
                return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception ex){
            throw new Exception(ex);
        }
    }

    @GetMapping("/{dataInicio}/{dataFinal}")
    public ResponseEntity<?> veiculosDisponiveisNoPeriodo(@PathVariable @DateTimeFormat(pattern="yyyy-MM-dd")Date dataInicio,
                                                          @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd")Date dataFinal) throws Exception{
        try{
            Calendar inicio = Calendar.getInstance();
            inicio.setTime(dataInicio);

            Calendar fim= Calendar.getInstance();
            fim.setTime(dataFinal);

            List<Carro> listaDeCarrosDisponiveis = reservaService.veiculosDisponiveisNoPeriodo(inicio, fim);
            if(!listaDeCarrosDisponiveis.isEmpty() ) return new ResponseEntity<>(listaDeCarrosDisponiveis, null , HttpStatus.OK);
            else return new ResponseEntity<>(listaDeCarrosDisponiveis, null , HttpStatus.NO_CONTENT);
        }
        catch (Exception ex){
            throw new Exception(ex);
        }
    }
}
