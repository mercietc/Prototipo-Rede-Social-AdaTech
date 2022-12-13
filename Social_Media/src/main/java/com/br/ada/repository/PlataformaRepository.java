package com.br.ada.repository;

import com.br.ada.modelo.Post;
import com.br.ada.modelo.Usuario;
import com.br.ada.utilidade.ArquivoUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.br.ada.servico.PlataformaServico.obterLike;
import static com.br.ada.servico.PostServico.fluxoMeuPost;
import static com.br.ada.servico.UsuarioServico.exibirOpcoesDePerfil;
import static com.br.ada.utilidade.DataUtil.formatarDataToString;
import static com.br.ada.utilidade.GeneralUtil.isInteger;

public class PlataformaRepository {
    static Scanner input = new Scanner(System.in);
    public static List<Usuario> pesquisarUsuario(String usuario) {
        List<Usuario> usuariosData = new ArquivoUtil<String>().lerArquivo( "usuarioDatabase");
        Stream<Usuario> usuarioStream =
                usuariosData.stream().filter(data -> data.getNomeUsuario().toUpperCase().contains(usuario.toUpperCase()) ||
                        data.getNome().toUpperCase().contains(usuario.toUpperCase()));

        return  usuarioStream.collect(Collectors.toList());
    }

    public static List<Post> pesquisarPost(String post) {
        List<Post> postData = new ArquivoUtil<String>().lerPost( "postDatabase");
        Stream<Post> postStream =
                postData.stream().filter(data -> data.getTitulo().toUpperCase().contains(post.toUpperCase()) ||
                        data.getCorpo().toUpperCase().contains(post.toUpperCase()));

        return postStream.collect(Collectors.toList());
    }

    public static void  verFeed(Usuario usuario) {
        List<Usuario> usuariosData = new ArquivoUtil<String>().lerArquivo( "usuarioDatabase");

        List<Post> postData = new ArquivoUtil<String>().lerPost( "postDatabase");
        Stream<Post> postStream = postData.stream();
        Stream<Post> sorted = postStream.sorted(Comparator.comparing(Post::getDataCriacao));
        List<Post> postListSort = sorted.collect(Collectors.toList());

        for(Post post : postListSort) {
            Stream<Usuario> usuarioStream =
                    usuariosData.stream().filter(data -> data.getId() == post.getIdUsuario());

            String feedPost = "Id: " + post.getId() + '\n' +
                    "Título: " + post.getTitulo() + '\n' +
                    "Conteúdo: " + post.getCorpo() + '\n' +
                    "Autor: " + usuarioStream.findFirst().get().getNome() + '\n' +
                    "Data de Criação: " + formatarDataToString(post.getDataCriacao()) + '\n' +
                    "Data de Atualização: " + formatarDataToString(post.getDataAtualizacao()) + '\n' +
                    "Likes: " + post.getLikes() + '\n' +

                    '\n';

            System.out.println(feedPost);

        }
        obterLike(usuario);
    }

    public static void verMeusPosts(Usuario usuario) {
        List<Usuario> usuariosData = new ArquivoUtil<String>().lerArquivo("usuarioDatabase");

        List<Post> postData = new ArquivoUtil<String>().lerPost("postDatabase");
        String feedPost = "";
        List<Post> meusPosts = new ArrayList<>();
        for (Post post : postData) {
            if (post.getIdUsuario() == usuario.getId()) {
                meusPosts.add(post);
                Stream<Usuario> usuarioLogado =
                        usuariosData.stream().filter(data -> data.getId() == post.getIdUsuario());
                feedPost = "Id: " + post.getId() + '\n' +
                        "Título: " + post.getTitulo() + '\n' +
                        "Conteúdo: " + post.getCorpo() + '\n' +
                        "Autor: " + usuarioLogado.findFirst().get().getNome()
                        + '\n'
                + "Data de Criação: " + formatarDataToString(post.getDataCriacao()) + '\n' +
                        "Data de Atualização: " + formatarDataToString(post.getDataAtualizacao()) + '\n' +
                        "Likes: " + post.getLikes() + '\n' +
                        '\n';
                System.out.println(feedPost);
            }


        }

        if (feedPost.equals("")) {
            System.out.println("Você não possui posts.");
            System.out.println();

        } else {
            System.out.println();
            System.out.println("Deseja abrir um post? ");
            String opcao = "1 - SIM" +'\n' +
                    "2 - NÃO" + '\n' +  '\n' +
                    "Insira a opção desejada:";
            System.out.println(opcao);
            fluxoMeuPost(input.nextLine(), usuario, meusPosts);
        }
    }

    public static void curtirPost(String id, Usuario usuario) {
        String likedPost = "";
        if(isInteger(id)) {
            List<Post> postData = new ArquivoUtil<String>().lerPost("postDatabase");
            for(Post post: postData) {
                if(post.getId() == Integer.parseInt(id)) {
                    post.setLikes();

                    likedPost = "Post curtido com sucesso!\n";
                    System.out.println(likedPost);


                    ArquivoUtil<String> arquivo = new ArquivoUtil<>();
                    List<Post> lista = arquivo.lerPost("postDatabase");
                    new ArquivoUtil<String>().atualizarPost(lista,"postDatabase", post);
                    exibirOpcoesDePerfil(usuario);

                }
            }

            } else {
            System.out.println("ID inválido!");
            exibirOpcoesDePerfil(usuario);
        }
        if (likedPost.equals("")) {
            System.out.println("Post não encontrado.");
            System.out.println();
            exibirOpcoesDePerfil(usuario);
        }

    }


}
