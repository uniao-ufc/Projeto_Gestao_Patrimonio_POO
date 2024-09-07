package br.ufc.sistemapatrimonio.model;

public class RequisicaoDeManutencao {
    private int id;
    private String Nome;
    private String descricao;
    private Boolean status;

    public RequisicaoDeManutencao(int id, String nome, String descricao, Boolean status) {
        this.id = id;
        Nome = nome;
        this.descricao = descricao;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
