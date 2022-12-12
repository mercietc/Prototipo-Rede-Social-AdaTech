package com.br.ada.utilidade;

import com.br.ada.modelo.Usuario;
import com.br.ada.modelo.UsuarioBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.br.ada.utilidade.DataUtil.formatarData2;

public class ArquivoUtil<T> {

        public void escreverArquivo(T t, String fileName) {


            String path = "Social_Media/src/main/resources/" + fileName + ".csv";

            try (BufferedWriter csvWriter = new BufferedWriter(new FileWriter(path, true))) {


                csvWriter.write(t.toString());
                csvWriter.newLine();


            } catch (
                    IOException e) {
                System.out.println("error:" + e.getMessage());
            }

        }

        public List<Usuario> lerArquivo (String fileName) {
            String path = "Social_Media/src/main/resources/" + fileName + ".csv";
            try (BufferedReader file = new BufferedReader(new FileReader(path))) {
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
}
