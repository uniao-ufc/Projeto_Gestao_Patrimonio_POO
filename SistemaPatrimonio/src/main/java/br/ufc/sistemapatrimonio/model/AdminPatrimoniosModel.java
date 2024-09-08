package br.ufc.sistemapatrimonio.model;

import br.ufc.sistemapatrimonio.entities.Patrimonio;
import br.ufc.sistemapatrimonio.entities.TipoPatrimonio;
import br.ufc.sistemapatrimonio.exceptions.PatrimonioException; // Certifique-se de ter uma exceção personalizada para Patrimonios
import java.util.Iterator;
import java.util.Optional;

public class AdminPatrimoniosModel {

    public void adicionarPatrimonio(int id, String nome, String descricao, int depreciacao, String tipo, int numeroTombamento) throws PatrimonioException {
        // Verificar se já existe um patrimônio com o mesmo id
        for (Patrimonio patrimonio : Model.getPatrimonios()) {
            if (patrimonio.getId() == id) {
                throw new PatrimonioException(PatrimonioException.EXISTENTE, "O patrimônio com o ID " + id + " já existe.");
            }
        }
        // Criar um novo TipoPatrimonio
        TipoPatrimonio tipoPatrimonio = new TipoPatrimonio(tipo, descricao, depreciacao);

        // Criar um novo Patrimonio
        Patrimonio novoPatrimonio = new Patrimonio(id, nome, tipoPatrimonio, numeroTombamento, false);

        // Adicionar o novo patrimônio à lista
        Model.getPatrimonios().add(novoPatrimonio);
    }

    public void editarPatrimonio(int id, String nome, String descricao, int depreciacao, String tipo, int numeroTombamento) throws PatrimonioException {
        Optional<Patrimonio> patrimonioExistente = Model.getPatrimonios().stream().filter(patrimonio -> patrimonio.getId() == id).findFirst();
        if (patrimonioExistente.isPresent()) {
            Patrimonio patrimonio = patrimonioExistente.get();
            patrimonio.setNome(nome);
            patrimonio.getTipo().setNome(tipo);
            patrimonio.getTipo().setDescricao(descricao);
            patrimonio.getTipo().setDepreciacaoAnual(depreciacao);
            patrimonio.setNumeroTombamento(numeroTombamento);
        } else {
            throw new PatrimonioException(PatrimonioException.NAO_ENCONTRADO, "Patrimônio com o ID " + id + " não encontrado.");
        }
    }

    public void removerPatrimonio(int id) throws PatrimonioException {
        boolean encontrado = false;

        // Usando um iterator para garantir que a remoção seja segura e eficiente
        Iterator<Patrimonio> iterator = Model.getPatrimonios().iterator();
        while (iterator.hasNext()) {
            Patrimonio patrimonio = iterator.next();
            if (patrimonio.getId() == id) {
                iterator.remove(); // Remove o patrimônio da lista
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            throw new PatrimonioException(PatrimonioException.NAO_ENCONTRADO, "Patrimônio com o ID " + id + " não encontrado.");
        }
    }

    public String listarPatrimonios() {
        StringBuilder lista = new StringBuilder();
        for (Patrimonio patrimonio : Model.getPatrimonios()) {
            // Adiciona todas as informações relevantes do patrimônio
            lista.append("ID: ").append(patrimonio.getId())
                    .append(", Nome: ").append(patrimonio.getNome())
                    .append(", Tipo: ").append(patrimonio.getTipo().getNome())
                    .append(", Descrição Tipo: ").append(patrimonio.getTipo().getDescricao())
                    .append(", Depreciação Anual Tipo: ").append(patrimonio.getTipo().getDepreciacaoAnual())
                    .append(", Número de Tombamento: ").append(patrimonio.getNumeroTombamento())
                    .append(", Status: ").append(patrimonio.isAlocstatus() ? "Ativo" : "Inativo")
                    .append("\n");
        }
        return lista.toString();
    }
}
