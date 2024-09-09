package br.ufc.sistemapatrimonio.entities;

public class TipoBem {
    private String nome;
    private String descricao;
    private double depreciacaoAnual;

    public TipoBem(String nome, String descricao, double depreciacaoAnual) {
        this.nome = nome;
        this.descricao = descricao;
        this.depreciacaoAnual = depreciacaoAnual;
    }

    public TipoBem(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
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

    public double getDepreciacaoAnual() {
        return depreciacaoAnual;
    }

    public void setDepreciacaoAnual(double depreciacaoAnual) {
        this.depreciacaoAnual = depreciacaoAnual;
    }
}
