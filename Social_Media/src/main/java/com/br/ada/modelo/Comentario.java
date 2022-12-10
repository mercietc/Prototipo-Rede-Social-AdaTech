package com.br.ada.modelo;

import java.time.LocalDate;

public interface Comentario {
    int id = 0;
    int idUsuario = 0;
    int idPost = 0;
    String corpo = null;
    LocalDate dataCriacao = null;
    LocalDate dataAtualizacao = null;

}
