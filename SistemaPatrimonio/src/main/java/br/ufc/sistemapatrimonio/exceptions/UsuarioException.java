package br.ufc.sistemapatrimonio.exceptions;

public class UsuarioException extends Exception {
    public static final int EXISTENTE = 1; // código de erro para usuário existente
    public static final int ERRO = 2; // código de erro genérico
    public static final int DESCONHECIDO = 3; // código de erro para erro desconhecido

    private final int erroCode; // código de erro associado à exceção

    // construtor que aceita o código de erro e a mensagem
    public UsuarioException(int erroCode, String message) {
        super(message);
        this.erroCode = erroCode;
    }

    // método para obter o código de erro
    public int getErroCode() {
        return erroCode;
    }
}
