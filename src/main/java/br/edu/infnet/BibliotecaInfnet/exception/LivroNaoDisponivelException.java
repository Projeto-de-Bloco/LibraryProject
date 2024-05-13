package br.edu.infnet.BibliotecaInfnet.exception;

public class LivroNaoDisponivelException extends RuntimeException {
    public LivroNaoDisponivelException(String message) {
        super(message);
    }
}