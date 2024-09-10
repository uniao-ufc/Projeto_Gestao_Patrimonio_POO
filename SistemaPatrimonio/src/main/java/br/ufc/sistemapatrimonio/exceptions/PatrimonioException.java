package br.ufc.sistemapatrimonio.exceptions;

// define a exceção personalizada para patrimônios
public class PatrimonioException extends Exception {
    public static final int EXISTENTE = 1; // código de erro para patrimônio existente
    public static final int ERRO = 2; // código de erro genérico
    public static final int NAO_ENCONTRADO = 3; // código de erro para patrimônio não encontrado

    private final int erroCode; // código de erro associado à exceção

    // construtor que aceita o código de erro e a mensagem
    public PatrimonioException(int erroCode, String message) {
        super(message);
        this.erroCode = erroCode;
    }

    // método para obter o código de erro
    public int getErroCode() {
        return erroCode;
    }
}
