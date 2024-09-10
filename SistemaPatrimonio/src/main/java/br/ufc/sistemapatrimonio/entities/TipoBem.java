package br.ufc.sistemapatrimonio.entities;

// Representa um tipo de bem dentro do sistema de patrimônio
public class TipoBem {
    private String nome; // Nome do tipo de bem
    private String descricao; // Descrição do tipo de bem
    private double depreciacaoAnual; // Taxa de depreciação anual do bem

    // Construtor para inicializar um tipo de bem com nome, descrição e depreciação anual
    public TipoBem(String nome, String descricao, double depreciacaoAnual) {
        this.nome = nome;
        this.descricao = descricao;
        this.depreciacaoAnual = depreciacaoAnual;
    }

    // Construtor para inicializar um tipo de bem com nome e descrição, sem especificar a depreciação anual
    public TipoBem(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    // Getter para o nome do tipo de bem
    public String getNome() {
        return nome;
    }

    // Setter para o nome do tipo de bem
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter para a descrição do tipo de bem
    public String getDescricao() {
        return descricao;
    }

    // Setter para a descrição do tipo de bem
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // Getter para a taxa de depreciação anual do bem
    public double getDepreciacaoAnual() {
        return depreciacaoAnual;
    }

    // Setter para a taxa de depreciação anual do bem
    public void setDepreciacaoAnual(double depreciacaoAnual) {
        this.depreciacaoAnual = depreciacaoAnual;
    }
}
