package com.worker.validador.exceptions;

public class LimiteIndisponivelException extends RuntimeException{
    public LimiteIndisponivelException(String message) {
        super(message);
    }
}
