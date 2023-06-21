package com.uss.util;

/**
 * Created by ulisses on 17/05/2023.
 */
public class Util {

    public static String formatarCPF(String cpf) {
        cpf = cpf.replaceAll("\\D", "");
        cpf = cpf.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
        return cpf;
    }
}
