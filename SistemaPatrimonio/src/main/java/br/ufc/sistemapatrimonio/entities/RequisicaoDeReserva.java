package br.ufc.sistemapatrimonio.entities;

import br.ufc.sistemapatrimonio.enums.TipoReserva;

public class RequisicaoDeReserva {
    private static int id = 0;
    private final int idReserva;
    private final int idReqReserva;
    TipoReserva tipoReserva;
    String nome;
    Local local;
    String descricao;

    public RequisicaoDeReserva(int idReserva, String nome, Local local, String descricao, TipoReserva tipoReserva) {
        this.idReserva = idReserva;
        this.nome = nome;
        this.local = local;
        this.descricao = descricao;
        this.tipoReserva = tipoReserva;
        this.idReqReserva = id;
        id++;
    }

    public static int getId() {
        return id;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdReqReserva() {
        return idReqReserva;
    }

    public TipoReserva getTipoReserva() {
        return tipoReserva;
    }

    public void setTipoReserva(TipoReserva tipoReserva) {
        this.tipoReserva = tipoReserva;
    }
}
