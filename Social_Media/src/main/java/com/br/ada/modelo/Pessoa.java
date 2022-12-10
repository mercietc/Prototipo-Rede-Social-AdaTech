package com.br.ada.modelo;
import java.time.LocalDate;

public abstract class Pessoa {
    private String nome;
    private LocalDate dataNascimento;
    private String profissao;

    public Pessoa(){

    }

    public Pessoa(String nome, LocalDate dataNascimento, String profissao) {

        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.profissao = profissao;

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



    @Override
    public String toString() {
        return
                "Nome: '" + nome + '\'' +
                ", Data de nascimento: " + dataNascimento +
                ", Profissão: '" + profissao + "'";
    }
}
