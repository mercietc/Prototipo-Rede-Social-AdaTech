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
        String menu =
                " ----------------------------------------------------\n" +
                "|                   TIPO DE PESQUISA                 |\n" +
                "|____________________________________________________|\n" +
                "|                                                    |\n" +
                "|     Que tipo de pesquisa você gostaria de fazer?   |\n" +
                "|                                                    |\n" +
                "|    1 - Pesquisar Posts                             |\n" +
                "|    2 - Pesquisar Usuários                          |\n" +
                "|    3 - Voltar                                      |\n" +
                "|____________________________________________________|\n";

        System.out.println(menu);
        direcionarPesquisa(input.nextLine(), usuario);
    }


    private static void direcionarPesquisa(String opcao, Usuario usuario) {
        switch (opcao) {
            case "1":
                System.out.println("Digite sua pesquisa:");
                pesquisarPost(input.nextLine());
                exibirOpcoesDePerfil(usuario);
                break;
            case "2":
                System.out.println("Digite sua pesquisa:");
                pesquisarUsuario(input.nextLine(), usuario);
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
        String menu =
                " ----------------------------------------------------\n" +
                "|                      INTERAÇÃO                     |\n" +
                "|____________________________________________________|\n" +
                "|                                                    |\n" +
                "|         Gostaria de interagir com um post?         |\n" +
                "|                                                    |\n" +
                "|             1 - SIM           2 - NÃO              |\n" +
                "|____________________________________________________|\n";

        System.out.println(menu);
        System.out.println("Digite a opção desejada:");
        obterAcaoPost(input.nextLine(), usuario);

    }
    public static void acoesPost(String opcao, Usuario usuario, String id){
        String menu =
                        " --------------------------------------------------\n" +
                        "|                         POST                     |\n" +
                        "|__________________________________________________|\n" +
                        "|                                                  |\n" +
                        "|                 Você gostaria de:                |\n" +
                        "|                                                  |\n" +
                        "|         1- Curtir                                |\n" +
                        "|         2- Adicionar post aos Favoritos          |\n" +
                        "|__________________________________________________|\n";

        System.out.println(menu);
        System.out.println();
        System.out.println("Insira a opção desejada:");
        direcionarAcaoPost(input.nextLine(), usuario, id);

    }

    public static void obterRemoverFavoritos( Usuario usuario){
        String menu =
                        " ----------------------------------------------------\n" +
                        "|                      FAV0RITOS                     |\n" +
                        "|____________________________________________________|\n" +
                        "|                                                    |\n" +
                        "|  Você gostaria de remover um post dos favoritos?   |\n" +
                        "|                                                    |\n" +
                        "|             1 - SIM           2 - NÃO              |\n" +
                        "|____________________________________________________|\n";

        System.out.println(menu);
        System.out.println();
        System.out.println("Insira a opção desejada:");
        switch(input.nextLine()){
            case "1":
                System.out.println("Insira o ID do post que deseja remover:");
                removerPostDosFavoritos(input.nextLine(), usuario);
                break;
            case "2":
                exibirOpcoesDePerfil(usuario);
                break;
            default:
                logger.log(Level.WARNING, "Opção inválida, insira uma opção válida!" + '\n');
                exibirOpcoesDePerfil(usuario);
                break;
        }


    }

    public static void obterAcaoPost(String opcao, Usuario usuario) {
        switch (opcao) {
            case "1":
                System.out.println("Digite o ID do Post");
                acoesPost(opcao, usuario, input.nextLine());
            case "2":
                exibirOpcoesDePerfil(usuario);
            default:
                logger.log(Level.WARNING, "Opção inválida, insira uma opção válida!" + '\n');
                verFeed(usuario);
                break;
        }
    }
    public static void direcionarAcaoPost(String opcao, Usuario usuario, String id){
        switch (opcao) {
            case "1":
                curtirPost(id, usuario);
                verFeed(usuario);
                break;
            case "2":
                adicionarPostAosFavoritos(id, usuario);
                verFeed(usuario);
                break;
            default:
                logger.log(Level.WARNING, "Opção inválida, insira uma opção válida!" + '\n');
                verFeed(usuario);
                break;
        }
    }

    public static void opcoesAmigos(Usuario usuario){
        String menu =
                        " ----------------------------------------------------\n" +
                        "|                  OPÇÕES DE AMIZADE                 |\n" +
                        "|____________________________________________________|\n" +
                        "|                                                    |\n" +
                        "|          O que você gostaria de fazer hoje?        |\n" +
                        "|                                                    |\n" +
                        "|                 1- Adicionar amigo                 |\n" +
                        "|                 2- Remover amigo                   |\n" +
                        "|                 3- Voltar                          |\n" +
                        "|____________________________________________________|\n";

        System.out.println(menu);
        System.out.println();
        System.out.println("Insira a opção desejada:");
        switch(input.nextLine()){
            case "1":
                System.out.println("Confira os usuários disponíveis para fazer novas amizades: \n");
                verUsuarios(usuario);
                System.out.println("Insira o ID do usuário que deseja adicionar como amigo: ");
                adicionarAmigo(input.nextLine(), usuario);
                opcoesAmigos(usuario);
                break;
            case "2":
                System.out.println("Insira o ID do usuário que deseja desfazer amizade: ");
                removerAmigo(input.nextLine(), usuario);
                opcoesAmigos(usuario);
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
}
