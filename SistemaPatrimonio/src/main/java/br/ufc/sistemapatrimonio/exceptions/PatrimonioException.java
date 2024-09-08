package br.ufc.sistemapatrimonio.exceptions;

// Define a exceção personalizada para Patrimônios
public class PatrimonioException extends Exception {
    public static final int EXISTENTE = 1;
    public static final int ERRO = 2;
    public static final int NAO_ENCONTRADO = 3;

    private final int erroCode;

    // Construtor que aceita o código de erro e a mensagem
    public PatrimonioException(int erroCode, String message) {
        super(message);
        this.erroCode = erroCode;
    }

    // Método para obter o código de erro
    public int getErroCode() {
        return erroCode;
    }
}