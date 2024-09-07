package br.ufc.sistemapatrimonio.exceptions;

// Define a exceção personalizada
public class BemException extends Exception {
    public static final int EXISTENTE = 1;
    public static final int ERRO = 2;
    public static final int NAO_ENCONTRADO = 3;

    private final int erroCode;

    public BemException(int erroCode, String message) {
        super(message);
        this.erroCode = erroCode;
    }

    public int getErroCode() {
        return erroCode;
    }
}