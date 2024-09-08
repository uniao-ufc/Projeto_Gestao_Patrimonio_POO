package br.ufc.sistemapatrimonio.exceptions;

public class UsuarioException extends Exception {
    public static final int EXISTENTE = 1;
    public static final int ERRO = 2;

    public static final int DESCONHECIDO = 3;

    private final int erroCode;

    // Construtor que aceita o código de erro e a mensagem
    public UsuarioException(int erroCode, String message) {
        super(message);
        this.erroCode = erroCode;
    }

    // Método para obter o código de erro
    public int getErroCode() {
        return erroCode;
    }
}
