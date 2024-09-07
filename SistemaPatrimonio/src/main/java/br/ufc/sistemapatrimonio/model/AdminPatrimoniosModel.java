package br.ufc.sistemapatrimonio.model;

import br.ufc.sistemapatrimonio.entities.Patrimonio;
import br.ufc.sistemapatrimonio.entities.TipoPatrimonio;
import java.util.Optional;

public class AdminPatrimoniosModel {

    public void adicionarPatrimonio(int id, String nome, String descricao, int depreciacao, String tipo, int numeroTombamento) {
        TipoPatrimonio tipoPatrimonio = new TipoPatrimonio(tipo, descricao, depreciacao);

        Patrimonio novoPatrimonio = new Patrimonio(id, nome, tipoPatrimonio, numeroTombamento, false);

        Model.patrimonios.add(novoPatrimonio);
    }

    public void editarPatrimonio(int id, String nome, String descricao, int depreciacao, String tipo, int numeroTombamento) {
        Optional<Patrimonio> patrimonioExistente = Model.patrimonios.stream().filter(patrimonio -> patrimonio.getId() == id).findFirst();
        if (patrimonioExistente.isPresent()) {
            Patrimonio patrimonio = patrimonioExistente.get();
            patrimonio.setNome(nome);
            patrimonio.getTipo().setNome(tipo);
            patrimonio.getTipo().setDescricao(descricao);
            patrimonio.getTipo().setDepreciacaoAnual(depreciacao);
            patrimonio.setNumeroTombamento(numeroTombamento);
        }
    }

    public void removerPatrimonio(int id) {
        Model.patrimonios.removeIf(patrimonio -> patrimonio.getId() == id);
    }

    public String listarPatrimonios() {
        StringBuilder lista = new StringBuilder();
        for (Patrimonio patrimonio : Model.patrimonios) {
            lista.append("ID: ").append(patrimonio.getId()).append(", Nome: ").append(patrimonio.getNome()).append(", Tipo: ").append(patrimonio.getTipo().getNome()).append("\n");
        }
        return lista.toString();
    }
}
