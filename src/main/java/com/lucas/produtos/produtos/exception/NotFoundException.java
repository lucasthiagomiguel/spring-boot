package com.lucas.produtos.produtos.exception;

// Exceção para produto não encontrado
public class NotFoundException extends RuntimeException {
    public NotFoundException(String msg) {
        super(msg);
    }
}
