package com.br.ada.repository;
import com.br.ada.modelo.Usuario;
import com.br.ada.modelo.UsuarioBuilder;
import com.br.ada.utilidade.ArquivoUtil;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import static com.br.ada.servico.UsuarioServico.*;
import static com.br.ada.utilidade.DataUtil.formatarData;
import static com.br.ada.utilidade.SenhaUtil.checarSenha;
import static com.br.ada.utilidade.SenhaUtil.codificarSenha;

public class UsuarioRepository {
    static Logger logger
            = Logger.getLogger(
            UsuarioRepository.class.getName());
    static Scanner input = new Scanner(System.in);
    public static void obterInfoCadastro(){
        System.out.println("Olá, vamos começar o seu cadastro!");
        System.out.println("Insira seu nome:");
        String nome = input.nextLine();
        System.out.println("Insira sua data de nascimento: formato dd/mm/aaaa");
        String dataNascimento = input.nextLine();
        System.out.println("Insira sua profissão:");
        String profissao = input.nextLine();
        System.out.println("Insira seu e-mail");
        String email = input.nextLine();

        if(checarSeEmailJaCadastrado(email)) {
            System.out.println("E-mail já utilizado");
            iniciarAplicacao();
        } else {
            System.out.println("Insira seu nome de usuário:");
            String username = input.nextLine();
            if(checarSeUsernameJaCadastrado(username)) {
                System.out.println("Username já utilizado");
                iniciarAplicacao();
            } else {
                System.out.println("Insira a senha:");
                String senha = input.nextLine();
                fazerCadastro(nome,dataNascimento, profissao,email,username, senha);
            }
        }
    }
    private static void fazerCadastro(String nome, String dataNascimento, String profissao, String email ,
                                      String username,String senha) {
        Usuario novoUsuario = new UsuarioBuilder().nome(nome).dataNascimento(formatarData(dataNascimento))
                        .profissao(profissao).nomeUsuario(username)
                        .email(email).senha(codificarSenha(senha)).build();

        List<Usuario> usuariosData = new ArquivoUtil<String>().lerArquivo( "usuarioDatabase");
        novoUsuario.setId(usuariosData.get(usuariosData.size() - 1).getId() + 1);

        String usuario = novoUsuario.getId() + "," + novoUsuario.getNome() +  "," +
                novoUsuario.getDataNascimento() + "," + novoUsuario.getProfissao() + "," +
                novoUsuario.getNomeUsuario() + "," + novoUsuario.getEmail() + "," +
                novoUsuario.getSenha() + "," + novoUsuario.getDataCriacao();

        ArquivoUtil<String> arquivo = new ArquivoUtil<>();
        arquivo.escreverArquivo(usuario, "usuarioDatabase");
        System.out.println("Cadastro realizado com sucesso!");
        iniciarAplicacao();
    }

    private static boolean checarSeEmailJaCadastrado(String email) {
        List<Usuario> usuariosData = new ArquivoUtil<String>().lerArquivo( "usuarioDatabase");
        Stream<Usuario> usuarioStream =
                usuariosData.stream().filter(usuario -> usuario.getEmail().equals(email));

if(usuarioStream.count() > 0) {
    return true;
} else {
    return false;
}
    }
    private static boolean checarSeUsernameJaCadastrado(String username) {
        List<Usuario> usuariosData = new ArquivoUtil<String>().lerArquivo( "usuarioDatabase");
        Stream<Usuario> usuarioStream =
                usuariosData.stream().filter(usuario -> usuario.getNomeUsuario().equals(username));

        if(usuarioStream.count() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static void obterInfoLogin() {
        System.out.println("Olá! Para fazer Login insira:");
        System.out.println("E-mail ou nome de usuário:");
        String usuario = input.nextLine();
        System.out.println("Sua senha:");
        String senha = input.nextLine();

        if (!checarSeEmailJaCadastrado(usuario)
                && !checarSeUsernameJaCadastrado(usuario)) {
            System.out.println("Cheque seu Login e Senha e tente novamente!");
            iniciarAplicacao();
        } else {

            if(!checarSenha(senha, obterUsuario(usuario).getSenha())){
                System.out.println("Cheque seu Login e Senha e tente novamente!");
                iniciarAplicacao();
            } else {
                System.out.println("Login realizado com sucesso!" + '\n');
                exibirOpcoesDePerfil(obterUsuario(usuario));
            }

        }
    }

    public static Usuario obterUsuario(String usuario) {
        List<Usuario> usuariosData = new ArquivoUtil<String>().lerArquivo( "usuarioDatabase");
        Stream<Usuario> usuarioStream =
                usuariosData.stream().filter(data -> data.getEmail().equals(usuario) ||
                        data.getNomeUsuario().equals(usuario));

        return usuarioStream.findFirst().get();
    }

    public static void editarUsuario(Usuario usuario, String opcao) {

        switch (opcao) {
            case "1":
                System.out.println("Insira o novo nome:");
                usuario.setNome(input.nextLine());
                break;
            case "2":
                System.out.println("Insira a data de nascimento: formato: DD/MM/AAAA");
                usuario.setDataNascimento(formatarData(input.nextLine()));
                break;
            case "3":
                System.out.println("Insira a profissão:");
                usuario.setProfissao(input.nextLine());
                break;
            case "4":
                System.out.println("Insira o novo e-mail:");
                if(checarSeEmailJaCadastrado(input.nextLine())) {
                    System.err.println("E-mail já está em uso!");
                    exibirOpcoesDeEdicaoPerfil(usuario);
                } else {
                    usuario.setEmail(input.nextLine());

                }
                break;
            case "5":
                System.out.println("Insira a nova senha:");
                usuario.setSenha(codificarSenha(input.nextLine()));
                break;
            case "6":
                System.out.println("Insira o novo nome de usuário:");
                if(checarSeUsernameJaCadastrado(input.nextLine())) {
                    System.err.println("Nome de usuário indisponível!");
                    exibirOpcoesDeEdicaoPerfil(usuario);
                } else {
                    usuario.setNomeUsuario(input.nextLine());
                }

                break;
            case "7":
                exibirOpcoesDePerfil(usuario);
                break;
            default:
                logger.log(Level.WARNING, "Opção inválida, insira uma opção válida!" + '\n');
                exibirOpcoesDePerfil(usuario);
                break;
        }
        ArquivoUtil<String> arquivo = new ArquivoUtil<>();
        List<Usuario> lista = arquivo.lerArquivo("usuarioDatabase");


        new ArquivoUtil<String>()
                    .atualizarArquivo(lista,"usuarioDatabase", usuario);


        System.out.println("Perfil alterado com sucesso!");
        exibirOpcoesDePerfil(usuario);

    }
}
