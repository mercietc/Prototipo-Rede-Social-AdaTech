package com.br.ada.utilidade;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static com.br.ada.servico.UsuarioServico.iniciarAplicacao;

public class DataUtil {
    public static LocalDate formatarData(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(date, formatter);
        } catch(DateTimeParseException e) {
            System.err.println("Data inserida possui formato inválido. Formato aceito: DD/MM/AAAA");
            iniciarAplicacao();

        }
        return  null;
    }

    public static LocalDate formatarData2(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(date, formatter);
        } catch(DateTimeParseException e) {
            System.err.println("Data inserida possui formato inválido. Formato aceito: DD/MM/AAAA");
            iniciarAplicacao();

        }
        return  null;
    }
}
