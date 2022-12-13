package com.br.ada.servico;

import com.br.ada.modelo.Usuario;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.br.ada.repository.PlataformaRepository.*;
import static com.br.ada.servico.UsuarioServico.exibirOpcoesDePerfil;

public class PlataformaServico {
    static Logger logger
            = Logger.getLogger(
            PlataformaServico.class.getName());
    static Scanner input = new Scanner(System.in);

    public static void obterTipoPesquisa(Usuario usuario) {
        String menu =  "\n" + "1 - Pesquisar Posts" +
                "\n" + "2 - Pesquisar Usuários" +
                "\n" + "3 - Voltar "
                + "\n";

        System.out.println(menu);

        direcionarPesquisa(input.nextLine(), usuario);
    }


    private static void direcionarPesquisa(String opcao, Usuario usuario) {
        switch (opcao) {
            case "1":
                System.out.println("Digite sua pesquisa:");
                System.out.println(pesquisarPost(input.nextLine()));
                exibirOpcoesDePerfil(usuario);
                break;
            case "2":
                System.out.println("Digite sua pesquisa:");
                System.out.println(pesquisarUsuario(input.nextLine()));
                exibirOpcoesDePerfil(usuario);
                break;
            case "3":
                exibirOpcoesDePerfil(usuario);
                break;
            default:
                logger.log(Level.WARNING, "Opção inválida, insira uma opção válida!" + '\n');
                exibirOpcoesDePerfil(usuario);
                break;
        }
    }

    public static void obterLike(Usuario usuario) {
        System.out.println("Gostaria de curtir um post?");
        String menu =  "\n" + "1 - SIM" +
                "\n" + "2 - NÃO" + "\n";

        System.out.println(menu);
        System.out.println("Digite a opção desejada:");
        direcionarLike(input.nextLine(), usuario);

    }

    public static void direcionarLike(String opcao, Usuario usuario) {
        switch (opcao) {
            case "1":
                System.out.println("Digite o ID do Post");
                curtirPost(input.nextLine(), usuario);
            case "2":
                exibirOpcoesDePerfil(usuario);
            default:
                logger.log(Level.WARNING, "Opção inválida, insira uma opção válida!" + '\n');
                verFeed(usuario);
                break;
        }
    }
}
