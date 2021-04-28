package com.uniamerica.prova2.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dataDeRetirada;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dataDeDevolucao;

    public enum Status {
        RESERVADO, EM_ANDAMENTO, FINALIZADO;
    }
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataDeRetirada() {
        return dataDeRetirada;
    }

    public void setDataDeRetirada(Date dataDeRetirada) {
        this.dataDeRetirada = dataDeRetirada;
    }

    public Date getDataDeDevolucao() {
        return dataDeDevolucao;
    }

    public void setDataDeDevolucao(Date dataDeDevolucao) {
        this.dataDeDevolucao = dataDeDevolucao;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    @ManyToOne
    private Carro carro;


}
