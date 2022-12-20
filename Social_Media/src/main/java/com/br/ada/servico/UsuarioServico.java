package com.br.ada.servico;

import com.br.ada.modelo.Usuario;
import com.br.ada.utilidade.ArquivoUtil;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.br.ada.repository.PlataformaRepository.*;
import static com.br.ada.repository.UsuarioRepository.*;
import static com.br.ada.servico.PlataformaServico.obterTipoPesquisa;
import static com.br.ada.servico.PostServico.obterPostInfo;
import static com.br.ada.utilidade.DataUtil.saudarUsuario;

public class UsuarioServico {
    static Logger logger
            = Logger.getLogger(
            UsuarioServico.class.getName());

    static Scanner input = new Scanner(System.in);


    public static void iniciarAplicacao() {
        String menuInicial = '\n' +
        "-------------------------------- Boas vindas à AdaTech! ------------------------------\n" +
        " -------------------------------------------------------------------------------------\n" +
        "|                                         MENU                                        |\n" +
        "|_____________________________________________________________________________________|\n" +
        "|                                                                                     |\n" +
        "|                        Olá! O que você gostaria de fazer hoje?                      |\n" +
        "|                                                                                     |\n" +
        "|                 1- Fazer Login      2- Fazer Cadastro      3- Sair                  |\n" +
        "|                                                                                     |\n" +
        "|_____________________________________________________________________________________|\n" +
        "| Digite a opção desejada:                                                            |\n" +
        "|_____________________________________________________________________________________|\n";

        logger.log(Level.INFO, menuInicial);
        String opcao = input.nextLine();
        direcionarFluxo(opcao);
    }

    public static void direcionarFluxo(String opcao) {
        switch (opcao) {
            case "1":
                obterInfoLogin();
                break;
            case "2":
                obterInfoCadastro();
                break;
            case "3":
                System.out.println("______________________________________ Até mais! ______________________________________");
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
        String menu =
                " --------------------------------------------------------------------------------\n" +
                "|                                 OPÇÕES DE PERFIL                               |\n" +
                "|________________________________________________________________________________|\n" +
                "|                                                                                |\n" +
                "|                       O que você gostaria de fazer hoje?                       |\n" +
                "|                                                                                |\n" +
                "|         1- Criar um post      2- Ver meus posts     3- Ver feed                |\n" +
                "|         4- Configurar perfil  5- Meus favoritos     6- Meus amigos             |\n" +
                "|         7- Pesquisar          8- Deslogar                                      |\n" +
                "|                                                                                |\n" +
                "|________________________________________________________________________________|\n";

        System.out.println(menu);
        String opcao = input.nextLine();
        direcionarFluxoDePerfil(opcao, usuario);

    }

    private static void direcionarFluxoDePerfil(String opcao , Usuario usuario) {
        switch (opcao) {
            case "1":
                obterPostInfo(usuario);
                break;
            case "2":
                verMeusPosts(usuario);
               break;
            case "3":
                verFeed(usuario);
                exibirOpcoesDePerfil(usuario);
                break;
            case "4":
                exibirOpcoesDeEdicaoPerfil(usuario);
                break;
            case "5":
                exibirFavoritos(usuario);
                break;
            case "6":
                exibirAmigos(usuario);
                break;
            case "7":
                obterTipoPesquisa(usuario);
                break;
            case "8":
                System.out.println("Até mais, " + usuario.getNome() + "!");
                ArquivoUtil<String> arquivo = new ArquivoUtil<>();
                arquivo.escreverArquivo(usuario.getNome() + ": Horário Logout: " + LocalDateTime.now(),
                        "historicoSessao");
                System.exit(0);
                break;
            default:
                logger.log(Level.WARNING, "Opção inválida, insira uma opção válida!" + '\n');
                exibirOpcoesDePerfil(usuario);
                break;

        }
    }

    public static void exibirOpcoesDeEdicaoPerfil(Usuario usuario){
        System.out.println("");
        String menu =
                " -----------------------------------------------------------\n" +
                "|                 OPÇÕES DE EDIÇÃO DE PERFIL                |\n" +
                "|___________________________________________________________|\n" +
                "|                                                           |\n" +
                "|     Insira a opção que você gostaria de editar:           |\n" +
                "|                                                           |\n" +
                "|               1- Nome                                     |\n" +
                "|               2- Data de nascimento                       |\n" +
                "|               3- Profissão                                |\n" +
                "|               4- E-mail                                   |\n" +
                "|               5- Senha                                    |\n" +
                "|               6- Nome de usuário                          |\n" +
                "|               7- Voltar                                   |\n" +
                "|___________________________________________________________|\n";

        System.out.println(menu);
        editarUsuario(usuario, input.nextLine());
    }

}


