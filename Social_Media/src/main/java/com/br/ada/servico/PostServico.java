package com.br.ada.servico;
import com.br.ada.modelo.Post;
import com.br.ada.modelo.Usuario;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static com.br.ada.repository.PostRepository.*;
import static com.br.ada.servico.UsuarioServico.exibirOpcoesDePerfil;
import static com.br.ada.servico.UsuarioServico.input;

public class PostServico {
    static Logger logger
            = Logger.getLogger(
            PostServico.class.getName());

    public static void obterPostInfo(Usuario usuario){
        Scanner in = new Scanner(System.in);
        System.out.println("\n------------------------ Novo Post ------------------------");

        System.out.println("Digite o título do post: ");
        String titulo = in.nextLine();
        System.out.println("Digite o corpo do post: ");
        String corpo = in.nextLine();
        criaPost(titulo,corpo, usuario);

    }

    public static LocalDate criaData() {
        Scanner in = new Scanner(System.in);
        logger.log(Level.INFO,"Digite a data [dd/mm/yyyy]: ");
        String stringData = in.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(stringData, formatter);
    }

    public static void fluxoMeuPost(String opcao, Usuario usuario, List<Post> meusPosts) {
        switch (opcao) {
            case "1":
                System.out.println("Digite o Id do post:");
                abrirMeuPost(input.nextLine(), meusPosts, usuario);
                break;
            case "2":
                exibirOpcoesDePerfil(usuario);
                break;
            default:
                logger.log(Level.WARNING, "Opção inválida!" + '\n');
                exibirOpcoesDePerfil(usuario);
        }

    }


    public static void opcoesPost(Usuario usuario, Post post) {
        String menu =
                " -----------------------------------\n" +
                "|           OPÇÕES DE POST          |\n" +
                "|___________________________________|\n" +
                "|                                   |\n" +
                "|     O que deseja fazer?           |\n" +
                "|                                   |\n" +
                "|    1 - Editar Post                |\n" +
                "|    2 - Excluir Post               |\n" +
                "|    3 - Voltar ao Menu Principal   |\n" +
                "|___________________________________|\n";

        System.out.println(menu);
        System.out.println("Digite a opção desejada:");
        switch (input.nextLine()) {
            case "1":
                System.out.println("Modo Edição:");
                exibirOpcoesDeEdicaoPost(post, usuario);
                break;
            case "2":
                apagarPost(post, usuario);
                break;
            case "3":
                exibirOpcoesDePerfil(usuario);
                break;
            default:
                logger.log(Level.WARNING, "Opção inválida!" + '\n');
                exibirOpcoesDePerfil(usuario);
        }
    }

    public static void exibirOpcoesDeEdicaoPost(Post post, Usuario usuario){
        String menu =
                " ----------------------------------------------------\n" +
                "|               OPÇÕES DE EDIÇÃO DE POST             |\n" +
                "|____________________________________________________|\n" +
                "|                                                    |\n" +
                "|     Insira a opção que você gostaria de editar:    |\n" +
                "|                                                    |\n" +
                "|    1 - Título                                      |\n" +
                "|    2 - Conteúdo                                    |\n" +
                "|    3 - Voltar                                      |\n" +
                "|____________________________________________________|\n";

        System.out.println(menu);
        editarPost(post, input.nextLine(), usuario);
    }

}
