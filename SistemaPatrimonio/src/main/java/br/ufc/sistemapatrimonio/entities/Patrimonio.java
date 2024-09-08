package br.ufc.sistemapatrimonio.entities;

public class Patrimonio {
    private int id;
    private String nome;
    private TipoPatrimonio tipo;
    private Local local;
    private int numeroTombamento;
    private boolean Alocstatus;

    public Patrimonio(int id, String nome, TipoPatrimonio tipo, Local local, int numeroTombamento, boolean alocstatus) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.numeroTombamento = numeroTombamento;
        this.local = local;
        Alocstatus = alocstatus;
    }

    public Patrimonio(int id, String nome, TipoPatrimonio tipo, int numeroTombamento, boolean alocstatus) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.numeroTombamento = numeroTombamento;
        Alocstatus = alocstatus;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoPatrimonio getTipo() {
        return tipo;
    }

    public void setTipo(TipoPatrimonio tipo) {
        this.tipo = tipo;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public int getNumeroTombamento() {
        return numeroTombamento;
    }

    public void setNumeroTombamento(int numeroTombamento) {
        this.numeroTombamento = numeroTombamento;
    }

    public boolean isAlocstatus() {
        return Alocstatus;
    }

    public void setAlocstatus(boolean alocstatus) {
        Alocstatus = alocstatus;
    }
}
