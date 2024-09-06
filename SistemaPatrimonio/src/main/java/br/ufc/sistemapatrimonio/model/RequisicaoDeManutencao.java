package br.ufc.sistemapatrimonio.model;
public class RequisicaoDeManutencao{
    private String request;
    private int id;
    private Boolean status;

    public RequisicaoDeManutencao(String descricao) {
    }

    // Getter para request
    public String getRequest() {
        return request;
    }

    // Setter para request
    public void setRequest(String request) {
        this.request = request;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
