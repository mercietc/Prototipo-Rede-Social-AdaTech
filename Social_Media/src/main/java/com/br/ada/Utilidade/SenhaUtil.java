package com.br.ada.Utilidade;
import java.util.Arrays;
import java.util.List;


public class SenhaUtil {
        public static String codificarSenha(String senha) {
                String[] senhaArray = senha.split("");
                List<String> list = Arrays.asList(senhaArray);

                for(int i = 0; i < list.size(); i++) {
                        if(list.get(i).matches("[~!@#$%^&*()_+{}\\[\\]:;,.<>/?-]"))
                                list.set(i, "//"+list.get(i)+"//");
                        if(list.get(i).equals("A")) list.set(i, "(.26#)");
                        if(list.get(i).equals("B")) list.set(i, "(.25$)");
                        if(list.get(i).equals("C")) list.set(i, "(.24#)");
                        if(list.get(i).equals("D")) list.set(i, "(.23$)");
                        if(list.get(i).equals("E")) list.set(i, "(.22#)");
                        if(list.get(i).equals("F")) list.set(i, "(.21$)");
                        if(list.get(i).equals("G")) list.set(i, "(.20#)");
                        if(list.get(i).equals("H")) list.set(i, "(.19$)");
                        if(list.get(i).equals("I")) list.set(i, "(.18#)");
                        if(list.get(i).equals("J")) list.set(i, "(.17$)");
                        if(list.get(i).equals("K")) list.set(i, "(.16#)");
                        if(list.get(i).equals("L")) list.set(i, "(.15$)");
                        if(list.get(i).equals("M")) list.set(i, "(.14#)");
                        if(list.get(i).equals("N")) list.set(i, "(.13$)");
                        if(list.get(i).equals("O")) list.set(i, "(.12#)");
                        if(list.get(i).equals("P")) list.set(i, "(.11$)");
                        if(list.get(i).equals("Q")) list.set(i, "(.10#)");
                        if(list.get(i).equals("R")) list.set(i, "(.09$)");
                        if(list.get(i).equals("S")) list.set(i, "(.08#)");
                        if(list.get(i).equals("T")) list.set(i, "(.07$)");
                        if(list.get(i).equals("U")) list.set(i, "(.06#)");
                        if(list.get(i).equals("V")) list.set(i, "(.05$)");
                        if(list.get(i).equals("W")) list.set(i, "(.04#)");
                        if(list.get(i).equals("X")) list.set(i, "(.03$)");
                        if(list.get(i).equals("Y")) list.set(i, "(.02#)");
                        if(list.get(i).equals("Z")) list.set(i, "(.01$)");

                        if(list.get(i).equals("a")) list.set(i, "(.26#)<");
                        if(list.get(i).equals("b")) list.set(i, "(.25$)<");
                        if(list.get(i).equals("c")) list.set(i, "(.24#)<");
                        if(list.get(i).equals("d")) list.set(i, "(.23$)<");
                        if(list.get(i).equals("e")) list.set(i, "(.22#)<");
                        if(list.get(i).equals("f")) list.set(i, "(.21$)<");
                        if(list.get(i).equals("g")) list.set(i, "(.20#)<");
                        if(list.get(i).equals("h")) list.set(i, "(.19$)<");
                        if(list.get(i).equals("i")) list.set(i, "(.18#)<");
                        if(list.get(i).equals("j")) list.set(i, "(.17$)<");
                        if(list.get(i).equals("k")) list.set(i, "(.16#)<");
                        if(list.get(i).equals("l")) list.set(i, "(.15$)<");
                        if(list.get(i).equals("m")) list.set(i, "(.14#)<");
                        if(list.get(i).equals("n")) list.set(i, "(.13$)<");
                        if(list.get(i).equals("o")) list.set(i, "(.12#)<");
                        if(list.get(i).equals("p")) list.set(i, "(.11$)<");
                        if(list.get(i).equals("q")) list.set(i, "(.10#)<");
                        if(list.get(i).equals("r")) list.set(i, "(.09$)<");
                        if(list.get(i).equals("s")) list.set(i, "(.08#)<");
                        if(list.get(i).equals("t")) list.set(i, "(.07$)<");
                        if(list.get(i).equals("u")) list.set(i, "(.06#)<");
                        if(list.get(i).equals("v")) list.set(i, "(.05$)<");
                        if(list.get(i).equals("w")) list.set(i, "(.04#)<");
                        if(list.get(i).equals("x")) list.set(i, "(.03$)<");
                        if(list.get(i).equals("y")) list.set(i, "(.02#)<");
                        if(list.get(i).equals("z")) list.set(i, "(.01$)<");

                        if(list.get(i).equals("0")) list.set(i, "(;ZA&)");
                        if(list.get(i).equals("1")) list.set(i, "(;YB@)");
                        if(list.get(i).equals("2")) list.set(i, "(;XC&)");
                        if(list.get(i).equals("3")) list.set(i, "(;WD@)");
                        if(list.get(i).equals("4")) list.set(i, "(;VE&)");
                        if(list.get(i).equals("5")) list.set(i, "(;UF@)");
                        if(list.get(i).equals("6")) list.set(i, "(;TG&)");
                        if(list.get(i).equals("7")) list.set(i, "(;SH@)");
                        if(list.get(i).equals("8")) list.set(i, "(;RI&)");
                        if(list.get(i).equals("9")) list.set(i, "(;QJ@)") ;

                }

                return String.join("-%<", list);

        }

        public static boolean checarSenha(String senhaInserida, String senhaSalva) {

                senhaSalva = senhaSalva.replaceAll("//", "");

                String[] senhaArray = senhaSalva.split("-%<");

                List<String> list = Arrays.asList(senhaArray);

                for(int i = 0; i < list.size(); i++) {
                        if(list.get(i).equals("(.26#)")) list.set(i,"A");
                        if(list.get(i).equals("(.25$)")) list.set(i,"B");
                        if(list.get(i).equals("(.24#)")) list.set(i, "C");
                        if(list.get(i).equals("(.23$)")) list.set(i, "D");
                        if(list.get(i).equals("(.22#)")) list.set(i, "E");
                        if(list.get(i).equals("(.21$)")) list.set(i, "F");
                        if(list.get(i).equals("(.20#)")) list.set(i, "G");
                        if(list.get(i).equals("(.19$)")) list.set(i, "H");
                        if(list.get(i).equals("(.18#)")) list.set(i, "I");
                        if(list.get(i).equals("(.17$)")) list.set(i, "J");
                        if(list.get(i).equals("(.16#)")) list.set(i, "K");
                        if(list.get(i).equals("(.15$)")) list.set(i, "L");
                        if(list.get(i).equals("(.14#)")) list.set(i, "M");
                        if(list.get(i).equals("(.13$)")) list.set(i, "N");
                        if(list.get(i).equals("(.12#)")) list.set(i, "O");
                        if(list.get(i).equals("(.11$)")) list.set(i, "P");
                        if(list.get(i).equals("(.10#)")) list.set(i, "Q");
                        if(list.get(i).equals("(.09$)")) list.set(i, "R");
                        if(list.get(i).equals("(.08#)")) list.set(i, "S");
                        if(list.get(i).equals( "(.07$)")) list.set(i,"T");
                        if(list.get(i).equals("(.06#)")) list.set(i, "U");
                        if(list.get(i).equals("(.05$)")) list.set(i, "V");
                        if(list.get(i).equals("(.04#)")) list.set(i, "W");
                        if(list.get(i).equals("(.03$)")) list.set(i, "X");
                        if(list.get(i).equals("(.02#)")) list.set(i, "Y");
                        if(list.get(i).equals("(.01$)")) list.set(i, "Z");

                        if(list.get(i).equals("(.26#)<")) list.set(i,"a");
                        if(list.get(i).equals("(.25$)<")) list.set(i,"b");
                        if(list.get(i).equals("(.24#)<")) list.set(i, "c");
                        if(list.get(i).equals("(.23$)<")) list.set(i, "d");
                        if(list.get(i).equals("(.22#)<")) list.set(i, "e");
                        if(list.get(i).equals("(.21$)<")) list.set(i, "f");
                        if(list.get(i).equals("(.20#)<")) list.set(i, "g");
                        if(list.get(i).equals("(.19$)<")) list.set(i, "h");
                        if(list.get(i).equals("(.18#)<")) list.set(i, "i");
                        if(list.get(i).equals("(.17$)<")) list.set(i, "j");
                        if(list.get(i).equals("(.16#)<")) list.set(i, "k");
                        if(list.get(i).equals("(.15$)<")) list.set(i, "l");
                        if(list.get(i).equals("(.14#)<")) list.set(i, "m");
                        if(list.get(i).equals("(.13$)<")) list.set(i, "n");
                        if(list.get(i).equals("(.12#)<")) list.set(i, "o");
                        if(list.get(i).equals("(.11$)<")) list.set(i, "p");
                        if(list.get(i).equals("(.10#)<")) list.set(i, "q");
                        if(list.get(i).equals("(.09$)<")) list.set(i, "r");
                        if(list.get(i).equals("(.08#)<")) list.set(i, "s");
                        if(list.get(i).equals( "(.07$)<")) list.set(i,"t");
                        if(list.get(i).equals("(.06#)<")) list.set(i, "u");
                        if(list.get(i).equals("(.05$)<")) list.set(i, "v");
                        if(list.get(i).equals("(.04#)<")) list.set(i, "w");
                        if(list.get(i).equals("(.03$)<")) list.set(i, "x");
                        if(list.get(i).equals("(.02#)<")) list.set(i, "y");
                        if(list.get(i).equals("(.01$)<")) list.set(i, "z");

                        if(list.get(i).equals("(;ZA&)")) list.set(i, "0");
                        if(list.get(i).equals("(;YB@)")) list.set(i, "1");
                        if(list.get(i).equals("(;XC&)")) list.set(i, "2");
                        if(list.get(i).equals("(;WD@)")) list.set(i, "3");
                        if(list.get(i).equals("(;VE&)")) list.set(i, "4");
                        if(list.get(i).equals("(;UF@)")) list.set(i, "5");
                        if(list.get(i).equals("(;TG&)")) list.set(i, "6");
                        if(list.get(i).equals("(;SH@)")) list.set(i, "7");
                        if(list.get(i).equals("(;RI&)")) list.set(i, "8");
                        if(list.get(i).equals("(;QJ@)")) list.set(i, "9") ;

                }

                String s = String.join("", list);

                return senhaInserida.equals(s);
        }
        }


