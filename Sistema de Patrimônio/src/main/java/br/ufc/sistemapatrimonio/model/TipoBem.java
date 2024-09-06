package br.ufc.sistemapatrimonio.model;

public class TipoBem {
    private int id;
    private String nome;
    private String descricao;
    private int depreciacaoAnualTipo;
    
    // Getter para id
    public int getId() {
        return id;
    }
    
    // Setter para id
    public void setId(int id) {
        this.id = id;
    }

    // Getter para nome
    public String getNome() {
        return nome;
    }
    
    // Setter para nome
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter para descricao
    public String getDescricao() {
        return descricao;
    }
    
    // Setter para descricao
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // Getter para depreciacaoAnualTipo
    public int getDepreciacaoAnualTipo() {
        return depreciacaoAnualTipo;
    }
    
    // Setter para depreciacaoAnualTipo
    public void setDepreciacaoAnualTipo(int depreciacaoAnualTipo) {
        this.depreciacaoAnualTipo = depreciacaoAnualTipo;
    }
     
}
