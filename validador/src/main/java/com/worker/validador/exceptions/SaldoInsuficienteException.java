package com.worker.validador.exceptions;

public class SaldoInsuficienteException extends RuntimeException {
    public SaldoInsuficienteException(String meessage) {
        super(meessage);
    }
}
