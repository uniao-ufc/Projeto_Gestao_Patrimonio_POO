package br.ufc.sistemapatrimonio.entities;

public class Bem {
    private int id;
    private String nome;
    private TipoBem tipo;
    private Local local;
    private boolean Alocstatus;

    public Bem(int id, String nome, TipoBem tipo, Local local, boolean alocstatus) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.local = local;
        Alocstatus = alocstatus;
    }

    public Bem(int id, String nome, TipoBem tipo, boolean alocstatus) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        Alocstatus = alocstatus;
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

    public TipoBem getTipo() {
        return tipo;
    }

    public void setTipo(TipoBem tipo) {
        this.tipo = tipo;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public boolean isAlocstatus() {
        return Alocstatus;
    }

    public void setAlocstatus(boolean alocstatus) {
        Alocstatus = alocstatus;
    }
}
