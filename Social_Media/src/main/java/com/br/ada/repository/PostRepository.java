package com.br.ada.repository;

import com.br.ada.modelo.Post;
import com.br.ada.modelo.PostBuilder;
import com.br.ada.modelo.Usuario;

import com.br.ada.utilidade.ArquivoUtil;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import static com.br.ada.servico.PostServico.opcoesPost;
import static com.br.ada.servico.UsuarioServico.exibirOpcoesDePerfil;

import static com.br.ada.utilidade.DataUtil.formatarDataToString;
import static com.br.ada.utilidade.GeneralUtil.isInteger;

public class PostRepository {
    static Scanner input = new Scanner(System.in);

    static Logger logger
            = Logger.getLogger(
            PostRepository.class.getName());
    public static void criaPost(String titulo, String corpo, Usuario usuario) {
        Post post =  new PostBuilder()
                .titulo(titulo.replace(",", ";"))
                .corpo(corpo.replace(",", ";"))
                .idUsuario(usuario.getId())
                .build();

        List<Post> postData = new ArquivoUtil<String>().lerPost( "postDatabase");
        post.setId(postData.get(postData.size() - 1).getId() + 1);

        String postCSV =
                post.getId() + "," + post.getIdUsuario() + "," + post.getTitulo() +
                        "," + post.getCorpo() + "," + post.getDataCriacao() + "," +post.getDataAtualizacao();
        ArquivoUtil<String> arquivo = new ArquivoUtil<>();

        arquivo.escreverArquivo(postCSV,
                "postDatabase");

        System.out.println("Post criado com sucesso!");
        exibirOpcoesDePerfil(usuario);

    }

    public static void abrirMeuPost(String id, List<Post> meusPosts, Usuario usuario) {
        Post postEscolhido = null;
        if(isInteger(id)) {
            List<Usuario> usuariosData = new ArquivoUtil<String>().lerArquivo( "usuarioDatabase");

            String feedPost = "";
            for(Post post: meusPosts) {
                if(post.getId() == Integer.parseInt(id)) {
                    postEscolhido = post;
                    Stream<Usuario> usuarioStream =
                            usuariosData.stream().filter(data -> data.getId() == post.getIdUsuario());
                    feedPost = "Id: " + post.getId() + '\n' +
                            "Título: " + post.getTitulo() + '\n' +
                            "Conteúdo: " +  post.getCorpo() + '\n' +
                            "Autor: " + usuarioStream.findFirst().get().getNome()
                            + '\n' +
                            "Data de Criação: " + formatarDataToString(post.getDataCriacao()) + '\n' +
                            "Data de Atualização: " + formatarDataToString(post.getDataAtualizacao()) + '\n' +
                            "Likes: " + post.getLikes() + '\n' +
                            '\n';

                    System.out.println(feedPost);
                }
            }
            if (feedPost.equals("")) {
                System.out.println("Não foi possível encontrar um post com esse Id.");
                System.out.println();
                exibirOpcoesDePerfil(usuario);

            } else {
                opcoesPost(usuario, postEscolhido);
            }
        } else {
            System.out.println("Id inválido!");
            exibirOpcoesDePerfil(usuario);
        }

    }

    public static void editarPost(Post post, String opcao, Usuario usuario) {
        switch (opcao) {
            case "1":
                System.out.println("Insira o novo título:");
                post.setTitulo(input.nextLine().replace(",", ";"));
                break;
            case "2":
                System.out.println("Insira o novo conteúdo");
                post.setCorpo(input.nextLine().replace(",", ";"));
                break;
            case "3":
                exibirOpcoesDePerfil(usuario);
                break;
            default:
                logger.log(Level.WARNING, "Opção inválida, insira uma opção válida!" + '\n');
                exibirOpcoesDePerfil(usuario);
                break;
        }
        ArquivoUtil<String> arquivo = new ArquivoUtil<>();
        List<Post> lista = arquivo.lerPost("postDatabase");


        new ArquivoUtil<String>().atualizarPost(lista,"postDatabase", post);


        System.out.println("Post alterado com sucesso!");
        exibirOpcoesDePerfil(usuario);

    }

    public static void apagarPost(Post post, Usuario usuario) {

        ArquivoUtil<String> arquivo = new ArquivoUtil<>();
        List<Post> lista = arquivo.lerPost("postDatabase");

        for(int i=0; i<lista.size(); i++) {
            if (lista.get(i).getId() == post.getId()) {
                lista.remove(lista.get(i));
            }
        }
        new ArquivoUtil<String>().deletarPost(lista,"postDatabase", post);

        System.out.println("Post deletado com sucesso!");
        exibirOpcoesDePerfil(usuario);

    }

}
