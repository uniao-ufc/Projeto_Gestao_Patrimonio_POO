package br.ufc.sistemapatrimonio.entities;

public class Local {
    private String endereco;

    public Local(String endereco) {
        this.endereco = endereco;
    }

    // Getter para endereco
    public String getEndereco() {
        return endereco;
    }

    // Setter para endereco
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
