package com.br.ada.modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Utils {
    //implementacao dos metodos genericos
    public Post criaPost(){
        Scanner in = new Scanner(System.in);
        System.out.println("\n------------------------ Novo Post ------------------------");
        System.out.println("Digite o id do post: ");
        int id = in.nextInt();
        System.out.println("Digite o título do post: ");
        String titulo = in.nextLine();
        System.out.println("Digite o corpo do post: ");
        String corpo = in.nextLine();
        System.out.println("Digite o Id do Usuário: ");
        int idUsuario = in.nextInt();
        System.out.print("--- Inserindo data de criação do Post ---");
        LocalDate dataCriacao = criaData();
        System.out.print("--- Inserindo data de atualização do Post ---");
        LocalDate dataAtualizacao = criaData();

        Post post = new Post();
        post.setId(id);
        post.setTitulo(titulo);
        post.setCorpo(corpo);
        post.setIdUsuario(idUsuario);
        post.setDataCriacao(dataCriacao);
        post.setDataAtualizacao(dataAtualizacao);
        return post;
    }

    public static LocalDate criaData() {
        Scanner in = new Scanner(System.in);
        System.out.print("Digite a data [dd/mm/yyyy]: ");
        String stringData = in.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(stringData, formatter);
    }
}
