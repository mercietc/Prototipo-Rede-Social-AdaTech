package com.br.ada.modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Utils {
    //implementacao dos metodos genericos
    public Post criaPost(Usuario usuario){
        Scanner in = new Scanner(System.in);
        System.out.println("\n------------------------ Novo Post ------------------------");

        System.out.println("Digite o título do post: ");
        String titulo = in.nextLine();
        System.out.println("Digite o corpo do post: ");
        String corpo = in.nextLine();

        return new PostBuilder()
                .titulo(titulo)
                .corpo(corpo)
                .idUsuario(usuario.getId())
                .build();
    }

    public static LocalDate criaData() {
        Scanner in = new Scanner(System.in);
        System.out.print("Digite a data [dd/mm/yyyy]: ");
        String stringData = in.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(stringData, formatter);
    }
}
