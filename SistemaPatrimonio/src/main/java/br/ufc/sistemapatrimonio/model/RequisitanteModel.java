package br.ufc.sistemapatrimonio.model;

import br.ufc.sistemapatrimonio.entities.RequisicaoDeManutencao;
import java.util.ArrayList;
import java.util.List;

public class RequisitanteModel {

    // Lista para armazenar as requisições de manutenção
    private List<RequisicaoDeManutencao> requisicoes;

    // Construtor
    public RequisitanteModel() {
        this.requisicoes = new ArrayList<>();
    }

    // Adiciona uma nova requisição
    public boolean adicionarRequisicao(RequisicaoDeManutencao requisicao) {
        return requisicoes.add(requisicao);
    }

    // Lista todas as requisições
    public String listarRequisicoes() {
        StringBuilder lista = new StringBuilder();
        for (RequisicaoDeManutencao requisicao : requisicoes) {
            lista.append("ID: ").append(requisicao.getId())
                .append(", Descrição: ").append(requisicao)
                .append(", Status: ").append(requisicao)
                .append("\n");
        }
        return lista.toString();
    }

    // Remove uma requisição pelo ID
    public boolean removerRequisicao(int id) {
        return requisicoes.removeIf(requisicao -> requisicao.getId() == id);
    }
}
