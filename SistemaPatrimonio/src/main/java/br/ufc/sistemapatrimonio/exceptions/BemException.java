package br.ufc.sistemapatrimonio.exceptions;

// define uma exceção personalizada para a classe Bem
public class BemException extends Exception {
    public static final int EXISTENTE = 1; // código de erro para bem existente
    public static final int ERRO = 2; // código de erro genérico
    public static final int NAO_ENCONTRADO = 3; // código de erro para bem não encontrado

    private final int erroCode; // código de erro associado à exceção

    // construtor que inicializa a exceção com um código de erro e uma mensagem
    public BemException(int erroCode, String message) {
        super(message);
        this.erroCode = erroCode;
    }

    // getter para o código de erro
    public int getErroCode() {
        return erroCode;
    }
}
