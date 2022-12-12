package com.br.ada.servico;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static com.br.ada.repository.UsuarioRepository.obterInfoCadastro;

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
                System.out.println("Fazer login");
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
}


