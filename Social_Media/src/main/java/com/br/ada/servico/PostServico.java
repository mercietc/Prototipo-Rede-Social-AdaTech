package com.br.ada.servico;

import com.br.ada.modelo.Post;
import com.br.ada.modelo.PostBuilder;
import com.br.ada.modelo.Usuario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostServico {
    static Logger logger
            = Logger.getLogger(
            PostServico.class.getName());
    //implementacao dos metodos genericos
    public Post criaPost(Usuario usuario){
        Scanner in = new Scanner(System.in);
        logger.log(Level.INFO,"\n------------------------ Novo Post ------------------------");

        logger.log(Level.INFO,"Digite o título do post: ");
        String titulo = in.nextLine();
        logger.log(Level.INFO,"Digite o corpo do post: ");
        String corpo = in.nextLine();

        return new PostBuilder()
                .titulo(titulo)
                .corpo(corpo)
                .idUsuario(usuario.getId())
                .build();
    }

    public static LocalDate criaData() {
        Scanner in = new Scanner(System.in);
        logger.log(Level.INFO,"Digite a data [dd/mm/yyyy]: ");
        String stringData = in.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(stringData, formatter);
    }
}
