package com.br.ada.repository;

import com.br.ada.modelo.Post;
import com.br.ada.modelo.Usuario;
import com.br.ada.utilidade.ArquivoUtil;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlataformaRepository {
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
}
