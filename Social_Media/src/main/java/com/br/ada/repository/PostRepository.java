package com.br.ada.repository;

import com.br.ada.modelo.Post;
import com.br.ada.modelo.PostBuilder;
import com.br.ada.modelo.Usuario;
import com.br.ada.utilidade.ArquivoUtil;

import java.util.List;

import static com.br.ada.servico.UsuarioServico.exibirOpcoesDePerfil;

public class PostRepository {

    public static void criaPost(String titulo, String corpo, Usuario usuario) {
        Post post =  new PostBuilder()
                .titulo(titulo)
                .corpo(corpo)
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
}
