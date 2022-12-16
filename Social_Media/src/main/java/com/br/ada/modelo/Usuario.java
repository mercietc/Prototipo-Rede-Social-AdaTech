package com.br.ada.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Usuario extends Pessoa{
    private int id;
    private String nomeUsuario;
    private String email;
    private String senha;
    private LocalDate dataCriacao;

    private List<Post> favoritos = new ArrayList<>();

    private List<Usuario> amigos = new ArrayList<>();

    public Usuario(){

    }



    public Usuario(String nome, LocalDate dataNascimento, String profissao, String nomeUsuario, String email, String senha, LocalDate dataCriacao) {
        super( nome, dataNascimento, profissao);
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

    public List<Post> getFavoritos() {
        return favoritos;
    }
    public List<Usuario> getAmigos() {
        return amigos;
    }
    public void addFavoritos(Post post) {
        this.favoritos.add(post);
    }

    public void removeFavoritos(Post post) {
        this.favoritos.remove(post);
    }

    public void addAmigos(Usuario amigo) {
        this.amigos.add(amigo);
    }

    public void removeAmigos(Usuario amigo) {
        this.amigos.remove(amigo);
    }




    @Override
    public String toString() {
        return "Usuário [ " +
                "Id: " + id +
                ", " + super.toString() +
                ", Nome de usuário: '" + nomeUsuario + '\'' +
                ", E-mail: " + email + '\'' +
                ", Senha: '" + senha + '\'' +
                ", Lista de favoritos: '" + favoritos + '\'' +
                ", Criado em: " + dataCriacao +
                " ]";
    }
}
