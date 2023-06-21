package com.uss.exception;

/**
 * Created by ulisses on 09/05/2023.
 */
public class ExceptionValidacao extends RuntimeException {

    public ExceptionValidacao(String mensagemValidacao) {
        super(mensagemValidacao);
    }
}
