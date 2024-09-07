package br.ufc.sistemapatrimonio.entities;

public class TipoPatrimonio {
    private String nome;
    private String descricao;
    private int depreciacaoAnual;

    public TipoPatrimonio(String nome, String descricao, int depreciacaoAnual) {
        this.nome = nome;
        this.descricao = descricao;
        this.depreciacaoAnual = depreciacaoAnual;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getDepreciacaoAnual() {
        return depreciacaoAnual;
    }

    public void setDepreciacaoAnual(int depreciacaoAnual) {
        this.depreciacaoAnual = depreciacaoAnual;
    }
}
