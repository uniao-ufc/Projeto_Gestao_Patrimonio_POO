package br.ufc.sistemapatrimonio.model;

public class TipoPatrimonio {
    private int id;
    private String nome;
    private String descricao;
    private int depreciacaoMax;
    private int depreciacaoMin;

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

        // Getter para depreciacaoMax
        public int getDepreciacaoMax() {
            return depreciacaoMax;
        }
    
        // Setter para depreciacaoMax
        public void setDepreciacaoMax(int depreciacaoMax) {
            this.depreciacaoMax = depreciacaoMax;
        }

        // Getter para depreciacaoMin
        public int getDepreciacaoMin() {
            return depreciacaoMin;
        }
    
        // Setter para depreciacaoMin
        public void setDepreciacaoMin(int depreciacaoMin) {
            this.depreciacaoMin = depreciacaoMin;
        }
}
