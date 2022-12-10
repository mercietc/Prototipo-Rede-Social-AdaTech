package com.br.ada.modelo;

import java.time.LocalDate;

public class Post implements Comentario{
    private int id;
    private String titulo;
    private String corpo;
    private int idUsuario;
    private LocalDate dataCriacao;
    private LocalDate dataAtualizacao;

    public Post(){

    }
    public Post(int id, String titulo, String corpo, int idUsuario, LocalDate dataCriacao, LocalDate dataAtualizacao) {
        this.id = id;
        this.titulo = titulo;
        this.corpo = corpo;
        this.idUsuario = idUsuario;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDate dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    @Override
    public String toString() {
        return "Post{" +
                "Id: " + id +
                ", Título: '" + titulo + '\'' +
                ", Corpo: " + corpo + '\'' +
                ", Id do usuário: " + idUsuario +
                ", Criado em: " + dataCriacao +
                ", Atualizado em: " + dataAtualizacao +
                '}';
    }
}
