package com.br.ada.modelo;

import java.time.LocalDate;

public class Usuario extends Pessoa{
    private String login;
    private String senha;

    public Usuario(){

    }

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario(int id, String nome, LocalDate dataNasc, String cpf, String regiaoOrigem, String regiaoMorada, String login, String senha) {
        super(id, nome, dataNasc, cpf, regiaoOrigem, regiaoMorada);
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "Login: '" + login + '\'' +
                ", Senha: '" + senha + '\'' + //dado sensível que deve ser escondido
                '}';
    }
}
