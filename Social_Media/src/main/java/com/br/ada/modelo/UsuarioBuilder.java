package com.br.ada.modelo;

import java.time.LocalDate;

public class UsuarioBuilder {
    private static int sequence = 0;
    private String nome;
    private LocalDate dataNascimento;
    private String profissao;
    private int id;
    private String nomeUsuario;
    private String email;
    private String senha;
    private LocalDate dataCriacao;


    public UsuarioBuilder nome(String nome) {
        this.nome = nome;
        return this;
    }

    public UsuarioBuilder dataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
        return this;
    }

    public UsuarioBuilder profissao(String profissao) {
        this.profissao = profissao;
        return this;
    }

    private UsuarioBuilder id() {
        this.id = sequence + 1;
        setSequence(this.id);
        return this;
    }

    private static void setSequence(int sequence) {
        UsuarioBuilder.sequence = sequence;
    }

    public UsuarioBuilder nomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
        return this;
    }

    public UsuarioBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UsuarioBuilder senha(String senha) {
        this.senha = senha;
        return this;
    }

    private void dataCriacao() {
        this.dataCriacao = LocalDate.now();
    }

    public Usuario build(){
        id();
        dataCriacao();
        return new Usuario(nome, dataNascimento, profissao, id,
                nomeUsuario, email, senha, dataCriacao);
    }
}
