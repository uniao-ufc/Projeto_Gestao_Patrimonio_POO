package br.ufc.sistemapatrimonio.entities;

public class Patrimonio {
    private int id;
    private String tipo;
    private String nome;
    private String descricao;
    private int depreciacaoMax;
    private int depreciacaoMin;
    private int numeroTombamento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public int getDepreciacaoMax() {
        return depreciacaoMax;
    }

    public void setDepreciacaoMax(int depreciacaoMax) {
        this.depreciacaoMax = depreciacaoMax;
    }

    public int getDepreciacaoMin() {
        return depreciacaoMin;
    }

    public void setDepreciacaoMin(int depreciacaoMin) {
        this.depreciacaoMin = depreciacaoMin;
    }

    public int getNumeroTombamento() {
        return numeroTombamento;
    }

    public void setNumeroTombamento(int numeroTombamento) {
        this.numeroTombamento = numeroTombamento;
    }
}
