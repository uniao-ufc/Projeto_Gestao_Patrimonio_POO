package br.ufc.sistemapatrimonio.entities;

public class Patrimonio {
    private int id;
    private TipoPatrimonio tipo;
    private int numeroTombamento;

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

    public int getNumeroTombamento() {
        return numeroTombamento;
    }

    public void setNumeroTombamento(int numeroTombamento) {
        this.numeroTombamento = numeroTombamento;
    }
}
