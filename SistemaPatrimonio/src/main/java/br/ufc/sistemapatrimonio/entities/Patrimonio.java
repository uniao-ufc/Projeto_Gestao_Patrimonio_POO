package br.ufc.sistemapatrimonio.entities;

public class Patrimonio {
    private int id;
    private TipoPatrimonio tipo;
    private int numeroTombamento;

    // Getter para id
    public int getId() {
        return id;
    }

    // Setter para id
    public void setId(int id) {
        this.id = id;
    }

    // Getter para tipo
    public TipoPatrimonio getTipo() {
        return tipo;
    }

    // Setter para tipo
    public void setTipo(TipoPatrimonio tipo) {
        this.tipo = tipo;
    }

    // Getter para numeroTombamento
    public int getTombamento() {
        return numeroTombamento;
    }

    // Setter para numeroTombamento
    public void setTombamento(int numeroTombamento) {
        this.numeroTombamento = numeroTombamento;
    }
}
