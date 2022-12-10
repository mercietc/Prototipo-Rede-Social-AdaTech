package com.br.ada.modelo;
import java.time.LocalDate;

public abstract class Pessoa {
    private int id;
    private String nome;
    private LocalDate dataNascimento;
    private String profissao;
    private LocalDate dataCriacao;

    public Pessoa(){

    }

    public Pessoa(int id, String nome, LocalDate dataNascimento, String profissao, LocalDate dataCriacao) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.profissao = profissao;
        this.dataCriacao = dataCriacao;
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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "Id: " + id +
                ", Nome: '" + nome + '\'' +
                ", Data de nascimento: " + dataNascimento +
                ", Profissão: '" + profissao + '\'' +
                ", Criado em: " + dataCriacao +
                '}';
    }
}
