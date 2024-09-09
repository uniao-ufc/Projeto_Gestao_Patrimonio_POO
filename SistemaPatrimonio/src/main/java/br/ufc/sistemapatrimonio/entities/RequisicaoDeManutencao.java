package br.ufc.sistemapatrimonio.entities;

import br.ufc.sistemapatrimonio.enums.TipoReserva;

public class RequisicaoDeManutencao {
    TipoReserva tipo;
    private int id;
    private String nome;
    private String descricao;
    private Boolean status = false;
    private String usuarioCriador;

    public RequisicaoDeManutencao(int id, String nome, String descricao, TipoReserva tipo, boolean status, String usuarioCriador) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.status = status;
        this.tipo = tipo;
        this.usuarioCriador = usuarioCriador;
    }

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

    public TipoReserva getTipo() {
        return tipo;
    }

    public void setTipo(TipoReserva tipo) {
        this.tipo = tipo;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getUsuarioCriador() {
        return usuarioCriador;
    }

    public void setUsuarioCriador(String usuarioCriador) {
        this.usuarioCriador = usuarioCriador;
    }
}
