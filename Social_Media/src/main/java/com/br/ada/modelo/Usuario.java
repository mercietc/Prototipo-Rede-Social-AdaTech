package com.br.ada.modelo;

import java.time.LocalDate;

public class Usuario extends Pessoa{
    private int id;
    private String nomeUsuario;
    private String email;
    private String senha;
    private LocalDate dataCriacao;

    public Usuario(){

    }

    public Usuario(int id, String nomeUsuario, String email, String senha, LocalDate dataCriacao) {
        this.id = id;
        this.nomeUsuario = nomeUsuario;
        this.email = email;
        this.senha = senha;
        this.dataCriacao = dataCriacao;
    }

    public Usuario(int id, String nome, LocalDate dataNascimento, String profissao, LocalDate dataCriacao, int id1, String nomeUsuario, String email, String senha, LocalDate dataCriacao1) {
        super(id, nome, dataNascimento, profissao, dataCriacao);
        this.id = id1;
        this.nomeUsuario = nomeUsuario;
        this.email = email;
        this.senha = senha;
        this.dataCriacao = dataCriacao1;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    @Override
    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "Id: " + id +
                ", Nome de usuário: '" + nomeUsuario + '\'' +
                ", E-mail: " + email + '\'' +
                ", Senha: '" + senha + '\'' +
                ", Criado em: " + dataCriacao +
                '}';
    }
}
