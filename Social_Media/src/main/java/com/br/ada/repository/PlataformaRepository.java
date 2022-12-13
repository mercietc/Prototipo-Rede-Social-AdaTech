package com.br.ada.repository;

import com.br.ada.modelo.Post;
import com.br.ada.modelo.Usuario;
import com.br.ada.utilidade.ArquivoUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.br.ada.servico.PostServico.fluxoMeuPost;

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

    public static void  verFeed() {
        List<Usuario> usuariosData = new ArquivoUtil<String>().lerArquivo( "usuarioDatabase");

        List<Post> postData = new ArquivoUtil<String>().lerPost( "postDatabase");

        for(Post post : postData) {
            Stream<Usuario> usuarioStream =
                    usuariosData.stream().filter(data -> data.getId() == post.getIdUsuario());

            String feedPost = "Id: " + post.getId() + '\n' +
                    "Título: " + post.getTitulo() + '\n' +
                    "Conteúdo: " + post.getCorpo() + '\n' +
                    "Autor: " + usuarioStream.findFirst().get().getNome()
                    + '\n';

            System.out.println(feedPost);
        }

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
                        + '\n';
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


}
