import com.br.ada.modelo.Post;
import com.br.ada.modelo.PostBuilder;
import com.br.ada.modelo.Usuario;
import com.br.ada.modelo.UsuarioBuilder;
import com.br.ada.utilidade.ArquivoUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static com.br.ada.repository.PlataformaRepository.checarSeJaFavoritos;
import static com.br.ada.repository.PlataformaRepository.removerPostDosFavoritos;
import static com.br.ada.utilidade.SenhaUtil.checarSenha;
import static com.br.ada.utilidade.SenhaUtil.codificarSenha;


public class UsuarioTeste {
    public static void main(String[] args) {
        Usuario novoUsuario =
                new UsuarioBuilder().nome("Bruna")
                        .dataNascimento(LocalDate.now())
                        .profissao("Desenvolvedora")
                        .nomeUsuario("brunak")
                        .email("bruna@gmail.com")
                        .senha(codificarSenha("fortes123"))
                        .build();

        Usuario novoUsuario2 =
                new UsuarioBuilder().nome("Bruna")
                        .dataNascimento(LocalDate.now())
                        .profissao("Desenvolvedora")
                        .nomeUsuario("brunak")
                        .email("bruna@gmail.com")
                        .senha(codificarSenha("KIDABELHA"))
                        .build();



        Post novoPost =
                new PostBuilder()
                        .titulo("Primeiro Post")
                        .corpo("Como construir um build pattern")
                        .idUsuario(novoUsuario2.getId())
                        .build();

        Post novoPost2 =
                new PostBuilder()
                        .titulo("SEGUNDO Post")
                        .corpo("Como construir um build pattern")
                        .idUsuario(novoUsuario2.getId())
                        .build();

        String resposta  = checarSenha("KIDABELHA", novoUsuario2.getSenha()) ?
                "Logado!" : "Senha incorreta!";
//        System.out.println(resposta);
//        List<Usuario> usuariosData = new ArquivoUtil<String>().lerArquivo( "usuarioDatabase");
//        novoUsuario2.addFavoritos(novoPost);
//        novoUsuario2.addFavoritos(novoPost2);

//        usuariosData.get(0).removeFavoritos(novoPost);
//        System.out.println(usuariosData.get(0));

        ArquivoUtil<String> favoritosDatabase = new ArquivoUtil<>();
        String favoritos = "";
        for(Post post: novoUsuario2.getFavoritos()) {
            favoritos =
                    novoUsuario2.getNome() + "," +  novoUsuario2.getId() +
                             "," + post.getId() + "," + post.getIdUsuario() + "," + post.getTitulo() +
                    "," + post.getCorpo() + "," + post.getDataCriacao() + "," +post.getDataAtualizacao()
                    + "," + post.getLikes();
            favoritosDatabase.escreverArquivo(favoritos,"favoritosDatabase");



        }







        String  saudacao = "Boa noite, ";
        int hora = 0;
        if(hora > 17) {
            saudacao = "Boa noite, ";
        } else if (hora > 11) {
            saudacao = "Boa tarde, ";

        } else if( hora > 5)
            saudacao = "Bom dia, ";


//        System.out.println(saudacao);
        List<Post> postData = new ArquivoUtil<String>().lerPost( "postDatabase");

//        System.out.println(postData.get(postData.size() - 1).getId() + 1);
        checarSeJaFavoritos(novoUsuario2, "1");

        removerPostDosFavoritos("1", novoUsuario2);
    }
}
