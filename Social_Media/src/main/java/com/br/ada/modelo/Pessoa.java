package com.br.ada.modelo;

import java.time.LocalDate;

public abstract class Pessoa {
    private int id;
    private String nome;
    private LocalDate dataNasc;
    private String cpf;
    private String regiaoOrigem;
    private String regiaoMorada;

    public Pessoa(){

    }

    public Pessoa(int id, String nome, LocalDate dataNasc, String cpf, String regiaoOrigem, String regiaoMorada) {
        this.id = id;
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.cpf = cpf;
        this.regiaoOrigem = regiaoOrigem;
        this.regiaoMorada = regiaoMorada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRegiaoOrigem() {
        return regiaoOrigem;
    }

    public void setRegiaoOrigem(String regiaoOrigem) {
        this.regiaoOrigem = regiaoOrigem;
    }

    public String getRegiaoMorada() {
        return regiaoMorada;
    }

    public void setRegiaoMorada(String regiaoMorada) {
        this.regiaoMorada = regiaoMorada;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "Id: " + id +
                ", Nome: '" + nome + '\'' +
                ", Data de Nascimento: " + dataNasc +
                ", CPF: '" + cpf + '\'' +
                ", Região de origem: '" + regiaoOrigem + '\'' +
                ", Região onde mora: '" + regiaoMorada + '\'' +
                '}';
    }
}
