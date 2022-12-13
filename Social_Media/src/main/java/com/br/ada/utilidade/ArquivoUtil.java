package com.br.ada.utilidade;

import com.br.ada.modelo.Post;
import com.br.ada.modelo.PostBuilder;
import com.br.ada.modelo.Usuario;
import com.br.ada.modelo.UsuarioBuilder;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static com.br.ada.utilidade.DataUtil.formatarData2;

public class ArquivoUtil<T> {

        public void escreverArquivo(T t, String fileName) {


            String path = "Social_Media/src/main/resources/" + fileName + ".csv";
            Path path1 = Path.of(path);
            try (BufferedWriter csvWriter = new BufferedWriter(new FileWriter(path1.toFile(), true))) {


                csvWriter.write(t.toString());
                csvWriter.newLine();


            } catch (
                    IOException e) {
                System.out.println("error:" + e.getMessage());
            }

        }
    public ArquivoUtil atualizarArquivo(List<Usuario> lista, String fileName, Usuario usuario) {

        String path = "Social_Media/src/main/resources/" + fileName + ".csv";
        Path path1 = Path.of(path);
        limparArquivo(fileName);

        try (BufferedWriter csvWriter = new BufferedWriter(new FileWriter(path1.toFile(), true))) {

            csvWriter.write("ID,NOME,DATA DE NASCIMENTO,PROFISSAO,NOME DE USUARIO,E-MAIL,SENHA,DATA DE CRIACAO\n");
            for(int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getNomeUsuario().equals(usuario.getNomeUsuario())) {
                    lista.set(i, usuario);

                }
                String stringUser = lista.get(i).getId() + "," + lista.get(i).getNome() + "," +
                        lista.get(i).getDataNascimento() + "," + lista.get(i).getProfissao() + "," +
                        lista.get(i).getNomeUsuario() + "," + lista.get(i).getEmail() + "," +
                        lista.get(i).getSenha() + "," + lista.get(i).getDataCriacao();

                csvWriter.write(stringUser);
                csvWriter.newLine();
                csvWriter.flush();
            }




        } catch (
                IOException e) {
            System.out.println("error:" + e.getMessage());
        }

        return null;
    }

    public ArquivoUtil limparArquivo(String fileName) {


        String path = "Social_Media/src/main/resources/" + fileName + ".csv";
        Path path1 = Path.of(path);


        try (BufferedWriter csvWriter = new BufferedWriter(new FileWriter(path1.toFile(), false))) {

            csvWriter.write("");
            csvWriter.flush();
        }catch (
                IOException e) {
            System.out.println("error:" + e.getMessage());
        }

        return null;
    }

            public List<Usuario> lerArquivo (String fileName) {
            String path = "Social_Media/src/main/resources/" + fileName + ".csv";
            Path path1 = Path.of(path);
            try (BufferedReader file = new BufferedReader(new FileReader(path1.toFile()))) {
                String row = file.readLine();

                List<Usuario> arquivo = new ArrayList<>();
                List<String> linhas = new ArrayList<>();
                while (row != null) {

                    row = file.readLine();
                    linhas.add(row);

                    if(row != null) {
                        row = file.readLine();
                        linhas.add(row);
                    }
                }
                linhas.remove(linhas.get(linhas.size() - 1));

                for(int i = 0; i < linhas.size(); i++) {

                        String[] usuario = linhas.get(i).split(",");
                        Usuario usuarioData =
                                new UsuarioBuilder().nome(usuario[1])
                                        .dataNascimento(formatarData2(usuario[2]))
                                        .profissao(usuario[3])
                                        .nomeUsuario(usuario[4])
                                        .email(usuario[5])
                                        .senha(usuario[6])
                                        .build();
                    usuarioData.setId(Integer.parseInt(usuario[0]));
                    usuarioData.setDataCriacao(formatarData2(usuario[7]));
                        arquivo.add(usuarioData);

                }

                return arquivo;

            } catch (
                    IOException e) {
                System.out.println("error:" + e.getMessage());
            }

            return null;
        }

    public List<Post> lerPost (String fileName) {
        String path = "Social_Media/src/main/resources/" + fileName + ".csv";
        Path path1 = Path.of(path);
        try (BufferedReader file = new BufferedReader(new FileReader(path1.toFile()))) {
            String row = file.readLine();
            List<Post> arquivo = new ArrayList<>();
            List<String> linhas = new ArrayList<>();
            while (row != null) {
                row = file.readLine();
                linhas.add(row);
                if(row != null) {
                    row = file.readLine();
                    linhas.add(row);
                }
            }
            linhas.remove(linhas.get(linhas.size() - 1));
            for(int i = 0; i < linhas.size(); i++) {

                String[] info = linhas.get(i).split(",");
                Post post =  new PostBuilder()
                        .titulo(info[2])
                        .corpo(info[3])
                        .idUsuario(Integer.parseInt(info[1]))
                        .build();

                post.setId(Integer.parseInt(info[0]));
                post.setDataCriacao(formatarData2(info[4]));
                post.setDataAtualizacao(formatarData2(info[5]));
                arquivo.add(post);

            }
            return arquivo;

        } catch (
                IOException e) {
            System.out.println("error:" + e.getMessage());
        }

        return null;
    }
}
