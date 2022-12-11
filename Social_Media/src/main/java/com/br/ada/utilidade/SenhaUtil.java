package com.br.ada.utilidade;
import java.util.Arrays;
import java.util.List;
public class SenhaUtil {
        static  String[] maiusculo = {"A", "B", "C", "D", "E", "F", "G", "H",
                "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
                "S", "T", "U", "V", "W", "X", "Y", "Z"};
        static String[] minusculo = {"a", "b", "c", "d", "e", "f", "g", "h", "i",
                "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z"};
        static String[] numero = {"0", "1", "2", "3", "4", "5", "6", "7", "8",
                "9"};
        static List<String> listaMaiuscula = Arrays.asList(maiusculo);
        static List<String> listaMinuscula = Arrays.asList(minusculo);
        static List<String> listaNumero = Arrays.asList(numero);

        private SenhaUtil() {}

        public static String codificarSenha(String senha) {
                List<String> senhaLista = Arrays.asList(senha.split(""));
                for(int i = 0; i < listaMaiuscula.size(); i++) {
                        for(int j = 0; j < senhaLista.size(); j++) {
                                for (int k = 0; k < listaNumero.size(); k++) {
                                        if (senhaLista.get(j).equals(listaMaiuscula.get(i))) {
                                                senhaLista.set(j, gerarCode(listaMaiuscula.size()
                                                        - String.join("", listaMaiuscula)
                                                        .indexOf(listaMaiuscula.get(i), 0), "#)>", "$)>"));
                                        } else if (senhaLista.get(j).equals(listaMinuscula.get(i))) {
                                                senhaLista.set(j,gerarCode(listaMinuscula.size() - String.join("", listaMinuscula)
                                                        .indexOf(listaMinuscula.get(i), 0), "#)<", "$)<"));
                                        } else if(senhaLista.get(j).equals(listaNumero.get(k))) {
                                                int index = String.join("", listaNumero).indexOf(listaNumero.get(k), 0);
                                                senhaLista.set(j, gerarCodeN(listaNumero.size() - index, listaMaiuscula.get(listaMaiuscula.size() - 1 - index),
                                                        listaMaiuscula.get(index), "&)", "@)"));}}}}
                return String.join("-%<", senhaLista);}

        public static boolean checarSenha(String senhaInserida, String senhaSalva) {
                List<String> senhaLista = Arrays.asList(senhaSalva.split("-%<"));
                for(int i = 0; i < senhaLista.size(); i++) {
                        int listaLength= listaMaiuscula.size();
                        if (senhaLista.get(i).contains("&)") || senhaLista.get(i).contains("@)")) {
                                substituirCharsNumeros(senhaLista, i);} substituirChars(senhaLista, i);
                        if(senhaLista.get(i).contains(">")) {
                                senhaLista.set(i, listaMaiuscula.get(listaLength - toInt(senhaLista.get(i),">")));}
                        if(senhaLista.get(i).contains("<")) {
                                senhaLista.set(i, listaMinuscula.get(listaLength - toInt(senhaLista.get(i),"<")));}
                        if(senhaLista.get(i).length() == 2) {
                                senhaLista.set(i, String.valueOf(String.join("", listaMaiuscula)
                                        .indexOf(senhaLista.get(i).charAt(1), 0)));}}
                return senhaInserida.equals(String.join("", senhaLista));
        }

        private static void substituirChars(List<String> lista, int index) {
                lista.set(index, lista.get(index).replace("(", "")
                        .replace("$)", "")
                        .replace("#)", "")
                        .replace(".", "")
                        .replace(";", "")
                        .replace(")", ""));
        }
        private static void substituirCharsNumeros(List<String> lista, int index) {
                lista.set(index, lista.get(index).replace("(", "")
                        .replace("&", "")
                        .replace("@", "")
                        .replace(";", ""));
        }

        private static int toInt(String item, String caracter) {
                return Integer.parseInt(item.replace(caracter, ""));
        }

        private static String gerarCode(int code, String codeString, String codeString1){
                if(code % 2 == 0) {
                        return "(." + code + codeString;
                } else {
                        return "(." + code + codeString1;
                }
        }
        private static String gerarCodeN(int code, String codeString, String codeString1, String codeString2,
                                         String codeString3){
                if(code % 2 == 0) {
                        return "(;" + codeString + codeString1 + codeString2;
                } else {
                        return "(;" + codeString + codeString1 + codeString3;
                }
        }
}


