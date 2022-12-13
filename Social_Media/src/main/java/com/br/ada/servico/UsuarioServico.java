package com.br.ada.servico;

import com.br.ada.modelo.Usuario;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.br.ada.repository.UsuarioRepository.*;
import static com.br.ada.utilidade.DataUtil.saudarUsuario;

public class UsuarioServico {
    static Logger logger
            = Logger.getLogger(
            UsuarioServico.class.getName());

    static Scanner input = new Scanner(System.in);


    public static void iniciarAplicacao() {
        String menuInicial = '\n' +
                "       Olá, O que você gostaria de fazer hoje? " + '\n' +  '\n' +
                "____________________________________________________" + '\n' +
                '\n' +
                "   1- Fazer Login    2- Fazer Cadastro    3- Sair    "  + '\n' +
                "____________________________________________________"
                + '\n' + '\n' +
                "               Digite a opção desejada: " + '\n';

        logger.log(Level.INFO, menuInicial);
        String opcao = input.nextLine();
        direcionarFluxo(opcao);
    }

    private static void direcionarFluxo(String opcao) {
        switch (opcao) {
            case "1":
                obterInfoLogin();
                break;
            case "2":
                obterInfoCadastro();
                break;
            case "3":
                System.out.println("Até mais!");
                System.exit(0);
                break;
            default:
                logger.log(Level.WARNING,"Opção inválida, insira uma opção válida!"  + '\n');
                iniciarAplicacao();
                break;
        }
    }

    public static void exibirOpcoesDePerfil(Usuario usuario) {
        System.out.println(saudarUsuario() + usuario.getNome() + "!");
        System.out.println("O que gostaria de fazer hoje?");

           String menu =  "\n" + "1 - Criar um post" +
                   "\n" + "2 - Ver meus posts" +
                   "\n" + "3 - Ver feed "
                   + "\n" + "4 - Configurações de perfil" +
                   "\n" + "5 - Meus favoritos" +
                   "\n" + "6 - Lista de amigos" +
                   "\n" + "7 - Pesquisar" +
                   "\n" + "8 - Notifições" +
                   "\n" + "9 - Deslogar " + "\n";

        System.out.println(menu);
        String opcao = input.nextLine();
        direcionarFluxoDePerfil(opcao, usuario);

    }

    private static void direcionarFluxoDePerfil(String opcao , Usuario usuario) {
        switch (opcao) {
            case "1":
                System.out.println("Criar post");
                break;
            case "2":
                System.out.println("ver posts");
                break;
            case "3":
                System.out.println("ver feed");
                break;
            case "4":
                exibirOpcoesDeEdicaoPerfil(usuario);
                break;
            case "5":
                System.out.println("favoritos");
                break;
            case "6":
                System.out.println("amigos");
                break;
            case "7":
                System.out.println("pesquisar");
                break;
            case "8":
                System.out.println("notificacoes");
                break;
            case "9":
                System.out.println("Até mais, " + usuario.getNome() + "!");
                System.exit(0);
                break;
            default:
                logger.log(Level.WARNING, "Opção inválida, insira uma opção válida!" + '\n');
                exibirOpcoesDePerfil(usuario);
                break;

        }
    }

    public static void exibirOpcoesDeEdicaoPerfil(Usuario usuario){
        System.out.println("Insira a opção que você gostaria de editar:");
        String menu =  "\n" + "1 - Nome" +
                "\n" + "2 - Data de nascimento" +
                "\n" + "3 - Profissão "
                + "\n" + "4 - E-mail" +
                "\n" + "5 - Senha" +
                "\n" + "6 - Nome de usuário" +
                "\n" + "7 - Voltar";

        System.out.println(menu);
        editarUsuario(usuario, input.nextLine());
    }

}


