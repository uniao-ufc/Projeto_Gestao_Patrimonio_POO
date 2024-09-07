package br.ufc.sistemapatrimonio.entities;

public class TipoBem {
    private int id;
    private String nome;
    private String descricao;
    private int depreciacaoAnualTipo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getDepreciacaoAnualTipo() {
        return depreciacaoAnualTipo;
    }

    public void setDepreciacaoAnualTipo(int depreciacaoAnualTipo) {
        this.depreciacaoAnualTipo = depreciacaoAnualTipo;
    }
}
