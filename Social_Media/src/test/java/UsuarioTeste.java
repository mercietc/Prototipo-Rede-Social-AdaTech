import com.br.ada.modelo.Post;
import com.br.ada.modelo.PostBuilder;
import com.br.ada.modelo.Usuario;
import com.br.ada.modelo.UsuarioBuilder;

import java.time.LocalDate;

import static com.br.ada.Utilidade.SenhaUtil.checarSenha;
import static com.br.ada.Utilidade.SenhaUtil.codificarSenha;


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
                        .senha(codificarSenha("fortes123"))
                        .build();



        Post novoPost =
                new PostBuilder()
                        .titulo("Primeiro Post")
                        .corpo("Como construir um build pattern")
                        .idUsuario(novoUsuario2.getId())
                        .build();

        ;
        String resposta  = checarSenha("Fortes123", novoUsuario2.getSenha()) ?
                "Logado!" : "Senha incorreta!";
        System.out.println(resposta);


    }
}
