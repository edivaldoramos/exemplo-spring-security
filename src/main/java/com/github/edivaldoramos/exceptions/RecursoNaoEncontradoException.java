package com.github.edivaldoramos.exceptions;

public class RecursoNaoEncontradoException extends Exception{
    public RecursoNaoEncontradoException(String message) {
        super(message);
    }

    public RecursoNaoEncontradoException(String message, Exception cause) {
        super(message, cause);
    }
}
