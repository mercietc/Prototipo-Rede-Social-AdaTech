import com.br.ada.modelo.Post;
import com.br.ada.modelo.PostBuilder;
import com.br.ada.modelo.Usuario;
import com.br.ada.modelo.UsuarioBuilder;
import com.br.ada.utilidade.ArquivoUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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

        ;
        String resposta  = checarSenha("KIDABELHA", novoUsuario2.getSenha()) ?
                "Logado!" : "Senha incorreta!";
//        System.out.println(resposta);
        List<Usuario> usuariosData = new ArquivoUtil<String>().lerArquivo( "usuarioDatabase");
        usuariosData.get(0).addFavoritos(novoPost);
        System.out.println(usuariosData.get(0));
        usuariosData.get(0).removeFavoritos(novoPost);
        System.out.println(usuariosData.get(0));

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
    }
}
