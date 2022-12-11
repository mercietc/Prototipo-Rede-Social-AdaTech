package com.br.ada.principal;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    static Logger logger
            = Logger.getLogger(
            Main.class.getName());
    public static void main(String[] args) {
        logger.log(Level.INFO,"Hello world!");
    }
}