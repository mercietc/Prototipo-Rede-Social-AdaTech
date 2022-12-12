package com.br.ada.repository;
import com.br.ada.modelo.Usuario;
import com.br.ada.modelo.UsuarioBuilder;
import com.br.ada.utilidade.ArquivoUtil;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.stream.Stream;

import static com.br.ada.servico.UsuarioServico.iniciarAplicacao;
import static com.br.ada.utilidade.DataUtil.formatarData;
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
}
