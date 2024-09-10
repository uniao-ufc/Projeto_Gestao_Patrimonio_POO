package br.ufc.sistemapatrimonio.entities;

// representa um tipo de patrimônio dentro do sistema
public class TipoPatrimonio {
    private String nome; // nome do tipo de patrimônio
    private String descricao; // descrição do tipo de patrimônio
    private double depreciacaoAnual; // taxa de depreciação anual do patrimônio

    // construtor para inicializar um tipo de patrimônio com nome, descrição e depreciação anual
    public TipoPatrimonio(String nome, String descricao, double depreciacaoAnual) {
        this.nome = nome;
        this.descricao = descricao;
        this.depreciacaoAnual = depreciacaoAnual;
    }

    // getter para o nome do tipo de patrimônio
    public String getNome() {
        return nome;
    }

    // setter para o nome do tipo de patrimônio
    public void setNome(String nome) {
        this.nome = nome;
    }

    // getter para a descrição do tipo de patrimônio
    public String getDescricao() {
        return descricao;
    }

    // setter para a descrição do tipo de patrimônio
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // getter para a taxa de depreciação anual do patrimônio
    public double getDepreciacaoAnual() {
        return depreciacaoAnual;
    }

    // setter para a taxa de depreciação anual do patrimônio
    public void setDepreciacaoAnual(double depreciacaoAnual) {
        this.depreciacaoAnual = depreciacaoAnual;
    }
}
