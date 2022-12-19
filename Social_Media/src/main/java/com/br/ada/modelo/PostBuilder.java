package com.br.ada.modelo;

import java.time.LocalDate;

public class PostBuilder {
    private static int sequence = 0;
    private int id;
    private String titulo;
    private String corpo;
    private int idUsuario;
    private LocalDate dataCriacao;

    private LocalDate dataAtualizacao;

    private int likes = 0;


    private PostBuilder id() {
        this.id = sequence + 1;
        setSequence(this.id);
        return this;
    }

    private static void setSequence(int sequence) {
        PostBuilder.sequence = sequence;
    }

    public PostBuilder titulo(String titulo) {
        this.titulo = titulo;
        return this;
    }



    public PostBuilder corpo(String corpo) {
        this.corpo = corpo;
        return this;
    }

    public PostBuilder idUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
        return this;
    }

    private void dataAtualizacao() {
        this.dataAtualizacao = LocalDate.now();

    }

    private void dataCriacao() {
        this.dataCriacao = LocalDate.now();
    }

        public Post build(){

        id();
        dataCriacao();
        dataAtualizacao();
            return new Post(id, titulo, corpo, idUsuario, dataCriacao,
                dataAtualizacao);
    }
}
