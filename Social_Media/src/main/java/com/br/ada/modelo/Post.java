package com.br.ada.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Post implements Comentario{
    Logger logger
            = Logger.getLogger(
            Post.class.getName());

    private int id;
    private String titulo;
    private String corpo;
    private int idUsuario;

    private LocalDate dataCriacao;
    private LocalDate dataAtualizacao;

    private int likes = 0;
    private List<String> comentarios = new ArrayList<>();


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

    public int getLikes() {
        return likes;
    }
    public void setLikes() {
        this.likes += 1;
    }

    public void setLikes(int like) {
        this.likes = like;
    }
        public void setId(int id) {
        this.dataAtualizacao = LocalDate.now();
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.dataAtualizacao = LocalDate.now();
        this.titulo = titulo;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.dataAtualizacao = LocalDate.now();
        this.corpo = corpo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.dataAtualizacao = LocalDate.now();
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
    public List<String> getComentarios() {
        return comentarios;
    }
    public void removeComentarios(String comentario) {this.comentarios.remove(comentario);}

    @Override
    public String toString() {
        return "Post [ " +
                "Id: " + id +
                ", Título: '" + titulo + '\'' +
                ", Corpo: '" + corpo + '\'' +
                ", Id do usuário: " + idUsuario +
                ", Criado em: " + dataCriacao +
                ", Atualizado em: " + dataAtualizacao +
                ", Comentários do post: " + comentarios +  '\'' +
                " ]";
    }

    @Override
    public void criarComentario() {
        Scanner in = new Scanner(System.in);
        System.out.println("Insira o comentário: ");
        String conteudoComentario = in.nextLine();
        this.comentarios.add(conteudoComentario);

        String comentario = "criar";
        logger.log(Level.INFO, comentario);
    }

    @Override
    public void atualizarComentario() {
        Scanner in = new Scanner(System.in);
        System.out.println("Edite o comentário: ");
        String conteudoComentario = in.nextLine();
        this.comentarios.add(conteudoComentario);

        String comentario = "atualizar";
        logger.log(Level.INFO, comentario);
    }

    @Override
    public void deletarComentario() {
        String comentario = "";
        removeComentarios(comentario);

        comentario = "deletar";
        logger.log(Level.INFO, comentario);
    }
}
