package br.ufc.sistemapatrimonio.entities;

public class Bem {
    private int id;
    private String nome;
    private TipoBem tipo;
    private boolean Alocstatus;

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

    public boolean isAlocstatus() {
        return Alocstatus;
    }

    public void setAlocstatus(boolean alocstatus) {
        Alocstatus = alocstatus;
    }
}
