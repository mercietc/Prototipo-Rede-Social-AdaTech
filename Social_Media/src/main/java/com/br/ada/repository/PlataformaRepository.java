package com.br.ada.repository;

import com.br.ada.modelo.Post;
import com.br.ada.modelo.Usuario;
import com.br.ada.utilidade.ArquivoUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.br.ada.servico.PlataformaServico.*;
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

    public static void  verUsuarios(Usuario usuario) {
        List<Usuario> usuariosData = new ArquivoUtil<String>().lerArquivo( "usuarioDatabase");
        Stream<Usuario> usuarioStream = usuariosData.stream();
        Stream<Usuario> sorted = usuarioStream.sorted(Comparator.comparing(Usuario::getId));
        List<Usuario> usuarioListSort = sorted.collect(Collectors.toList());

        for(Usuario user : usuarioListSort) {

            String verUsuarioFeed =
                    "Id: " + user.getId() + '\n' +
                    "Nome: " + user.getNomeUsuario() + '\n' +
                    "Email: " + user.getEmail() + '\n' +
                    "Conta criada em: " + formatarDataToString(user.getDataCriacao()) + '\n' +
                    "Lista de favoritos: " + user.getFavoritos() + '\n' +
                    "Lista de amigos: " + user.getAmigos() + '\n' +
                    '\n';

            System.out.println(verUsuarioFeed);

        }
//        adicionarAmigo(id, usuario);
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
                        "Autor: " + usuarioLogado.findFirst().get().getNome() + '\n' +
                        "Data de Criação: " + formatarDataToString(post.getDataCriacao()) + '\n' +
                        "Data de Atualização: " + formatarDataToString(post.getDataAtualizacao()) + '\n' +
                        "Likes: " + post.getLikes() + '\n' +
                        '\n';
                System.out.println(feedPost);
            }


        }

        if (feedPost.equals("")) {
            System.out.println("Você não possui posts.");
            System.out.println();
            exibirOpcoesDePerfil(usuario);

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
                    verFeed(usuario);

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

    public static void adicionarPostAosFavoritos(String id, Usuario usuario) {
        if(checarSeJaFavoritos(usuario, id)) {
            System.err.println("Esse post já foi favoritado!");
            System.out.println();
            verFeed(usuario);
        }
        else {
            String postFavorito = "";
                List<Post> postData = new ArquivoUtil<String>().lerPost("postDatabase");
                for (Post postList : postData) {
                    if (postList.getId() == Integer.parseInt(id)) {

                        usuario.addFavoritos(postList);
                        postFavorito = "Post favoritado com sucesso!\n";
                        System.out.println(postFavorito);
                        System.out.println();
                        ArquivoUtil<String> favoritosDatabase = new ArquivoUtil<>();
                        String favoritos = "";
                        for (Post post : usuario.getFavoritos()) {
                            favoritos =
                                    post.getId() + "," + post.getIdUsuario() + "," + post.getTitulo() +
                                            "," + post.getCorpo() + "," + post.getDataCriacao() + "," + post.getDataAtualizacao()
                                            + "," + post.getLikes();
                            favoritosDatabase.escreverArquivo(favoritos, usuario.getNomeUsuario()+"favoritos");
                        }

                    }
                }
            }
    }

    public static void removerPostDosFavoritos(String id, Usuario usuario) {
        if (!checarSeJaFavoritos(usuario, id)) {
            System.err.println("Não foi encontrado um post com esse Id em seus favoritos!");
            System.out.println();
            exibirOpcoesDePerfil(usuario);
        } else {

            ArquivoUtil<String> arquivo = new ArquivoUtil<>();
            List<Post> lista = arquivo.lerPost(usuario.getNomeUsuario()+"favoritos");
            Stream<Post> postfilter = lista.stream().filter(data -> data.getId() == Integer.parseInt(id));
            Post post = postfilter.findFirst().get();

            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getId() == post.getId()) {
                    lista.remove(lista.get(i));
                }
            }
            new ArquivoUtil<String>().deletarPost(lista, usuario.getNomeUsuario()+"favoritos", post);

            System.out.println("Post deletado com sucesso!");
            exibirOpcoesDePerfil(usuario);
        }

    }

    public static boolean checarSeJaFavoritos(Usuario usuario, String id) {
        if (isInteger(id)) {
            List<Post> postData = new ArquivoUtil<String>()
                    .lerPost(usuario.getNomeUsuario()+"favoritos");

            Stream<Post> postList = postData.stream().filter(post -> post.getId() == Integer.parseInt(id));

            return postList.findFirst().isPresent();

        } else {
            System.out.println("ID inválido!");
            verFeed(usuario);
            return false;

        }

    }

    public static void exibirFavoritos(Usuario usuario){
        File file = new File("Social_Media/src/main/resources/" + usuario.getNomeUsuario()+"favoritos" + ".csv");
        if(!file.exists()) {
            ArquivoUtil<String> favoritosDatabase = new ArquivoUtil<>();
            favoritosDatabase.escreverArquivo("ID,ID DO USUARIO,TITULO,CORPO,DATA DE CRIACAO,DATA DE ATUALIZACAO,LIKES",
                    usuario.getNomeUsuario()+"favoritos");
            System.out.println("Você ainda não favoritou nenhum post :(");
            System.out.println();
            exibirOpcoesDePerfil(usuario);
        } else {
            List<Usuario> usuariosData = new ArquivoUtil<String>().lerArquivo("usuarioDatabase");
            List<Post> postData = new ArquivoUtil<String>()
                    .lerPost(usuario.getNomeUsuario()+"favoritos");
            String feedPost = "";
            if(postData.size() > 0 ) {

                System.out.println("Aqui estão seus posts Favoritos:");
                for(Post post: postData) {
                    Stream<Usuario> usuarioLogado =
                            usuariosData.stream().filter(data -> data.getId() == post.getIdUsuario());
                    feedPost = "Id: " + post.getId() + '\n' +
                            "Título: " + post.getTitulo() + '\n' +
                            "Conteúdo: " + post.getCorpo() + '\n' +
                            "Autor: " + usuarioLogado.findFirst().get().getNome() + '\n' +
                            "Data de Criação: " + formatarDataToString(post.getDataCriacao()) + '\n' +
                            "Data de Atualização: " + formatarDataToString(post.getDataAtualizacao()) + '\n' +
                            "Likes: " + post.getLikes() + '\n' +
                            '\n';
                    System.out.println(feedPost);
                    obterRemoverFavoritos(usuario);
                }
            } else {
                System.out.println("Você ainda não favoritou nenhum post :(");
                exibirOpcoesDePerfil(usuario);

            }

        }

    }

    public static void adicionarAmigo(String id, Usuario usuario) {
        if(checarSeAmigoJaAdicionado(usuario, id)) {
            System.err.println("Vocês já são amigos!");
            System.out.println();
            verFeed(usuario);
        }
        else {
            List<Usuario> usuariosData = new ArquivoUtil<String>().lerArquivo("usuarioDatabase");
            List<Usuario> usuarioData = new ArquivoUtil<String>().lerArquivo(usuario.getNomeUsuario()+"amigos");

            String usuarioAmigo = "";

            for (Usuario listaAmigos : usuariosData) {
                if (listaAmigos.getId() == Integer.parseInt(id)) {
                    usuario.addAmigos(listaAmigos);
                    usuarioAmigo = "Amigo adicionado com sucesso!\n";
                    System.out.println(usuarioAmigo);
                    System.out.println();
                    ArquivoUtil<String> amigosDatabase = new ArquivoUtil<>();
                    String amigos = "";
                    for (Usuario user : usuario.getAmigos()) {
                        amigos =
                                user.getId() + "," + user.getNome() +  "," +
                                        user.getDataNascimento() + "," + user.getProfissao() + "," +
                                        user.getNomeUsuario() + "," + user.getEmail() + "," +
                                        user.getSenha() + "," + user.getDataCriacao();
                        amigosDatabase.escreverArquivo(amigos, usuario.getNomeUsuario()+"amigos");
                    }
                }
            }
        }

    }

    public static void removerAmigo(String id, Usuario usuario) {
        if (!checarSeAmigoJaAdicionado(usuario, id)) {
            System.err.println("Não foi encontrado um usuário com esse Id na sua lista de amigos!");
            System.out.println();
            exibirOpcoesDePerfil(usuario);
        } else {

            ArquivoUtil<String> arquivo = new ArquivoUtil<>();
            List<Usuario> lista = arquivo.lerArquivo(usuario.getNomeUsuario()+"amigos");
            Stream<Usuario> userfilter = lista.stream().filter(data -> data.getId() == Integer.parseInt(id));
            Usuario user = userfilter.findFirst().get();

            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getId() == user.getId()) {
                    lista.remove(lista.get(i));
                }
            }
            //new ArquivoUtil<String>().deletarAmigo(lista, usuario.getNomeUsuario()+"amigos", user);

            System.out.println("Amizade desfeita.");
            exibirOpcoesDePerfil(usuario);
        }

    }


    public static boolean checarSeAmigoJaAdicionado(Usuario usuario, String id) {
        if (isInteger(id)) {
            List<Usuario> usuarioData = new ArquivoUtil<String>()
                    .lerArquivo(usuario.getNomeUsuario()+"amigos");

            Stream<Usuario> usuarioList = usuarioData.stream().filter(user -> usuario.getId() == Integer.parseInt(id));

            return usuarioList.findFirst().isPresent();

        } else {
            System.out.println("ID inválido!");
            verFeed(usuario);
            return false;

        }

    }


    public static void exibirAmigos(Usuario usuario){
        File file = new File("Social_Media/src/main/resources/" + usuario.getNomeUsuario()+"amigos" + ".csv");
        if(!file.exists()) {
            ArquivoUtil<String> amigosDatabase = new ArquivoUtil<>();
            amigosDatabase.escreverArquivo("ID,NOME,DATA DE NASCIMENTO,PROFISSAO,NOME DE USUARIO,E-MAIL,SENHA,DATA DE CRIACAO"
                    ,
                    usuario.getNomeUsuario()+"amigos");
            System.out.println("Você ainda não tem amigos :(");
            obterAmigo(usuario);
            System.out.println();
            exibirOpcoesDePerfil(usuario);
        } else {
            List<Usuario> usuariosData = new ArquivoUtil<String>().lerArquivo("usuarioDatabase");
            List<Usuario> usuarioData = new ArquivoUtil<String>()
                    .lerArquivo(usuario.getNomeUsuario()+"amigos");
            String amigo = "";
            if(usuarioData.size() > 0 ) {

                System.out.println("Aqui estão seus amigos:");
                for(Usuario user: usuarioData) {
                    Stream<Usuario> usuarioLogado =
                            usuariosData.stream().filter(data -> data.getId() == user.getId());
                    amigo = "Id: " + user.getId() + '\n' +
                            "Nome: " + user.getNome() + '\n' +
                            "Email: " + user.getEmail() + '\n' +
                         "Conta criada em: " + formatarDataToString(user.getDataCriacao()) + '\n' +
                            "Lista de favoritos: " + user.getFavoritos() + '\n' +
                            "Lista de amigos: " + user.getAmigos() + '\n' +
                            '\n';
                    System.out.println(amigo);
                    obterRemoverAmigos(usuario);
                }
            } else {
                System.out.println("Você ainda não tem amigos :(");
                obterAmigo(usuario);

            }

        }
        obterAmigo(usuario);
    }
}
