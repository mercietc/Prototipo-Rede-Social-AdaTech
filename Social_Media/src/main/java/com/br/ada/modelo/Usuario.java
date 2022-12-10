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

    public Usuario(String nome, LocalDate dataNascimento, String profissao,  int id, String nomeUsuario, String email, String senha, LocalDate dataCriacao) {
        super( nome, dataNascimento, profissao);
        this.id = id;
        this.nomeUsuario = nomeUsuario;
        this.email = email;
        this.senha = senha;
        this.dataCriacao = dataCriacao;
    }


    public int getId() {
        return id;
    }

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

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Override
    public String toString() {
        return "Usuário [ " +
                "Id: " + id +
                ", " + super.toString() +
                ", Nome de usuário: '" + nomeUsuario + '\'' +
                ", E-mail: " + email + '\'' +
                ", Senha: '" + senha + '\'' +
                ", Criado em: " + dataCriacao +
                " ]";
    }
}
