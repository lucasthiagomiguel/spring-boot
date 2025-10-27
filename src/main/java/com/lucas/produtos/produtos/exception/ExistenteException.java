package com.lucas.produtos.produtos.exception;


public class ExistenteException extends RuntimeException {
    public ExistenteException(String msg) {
        super(msg);
    }
}