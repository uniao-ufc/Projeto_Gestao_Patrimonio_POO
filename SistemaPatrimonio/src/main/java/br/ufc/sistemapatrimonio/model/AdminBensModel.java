package br.ufc.sistemapatrimonio.model;

import br.ufc.sistemapatrimonio.entities.Bem;
import br.ufc.sistemapatrimonio.entities.TipoBem;
import br.ufc.sistemapatrimonio.exceptions.BemException;
import br.ufc.sistemapatrimonio.exceptions.BemException;

import java.util.Iterator;

public class AdminBensModel {

    public void adicionarBem(int id, String nome, String descricao, int depreciacao, String tipo) throws BemException {
        // Verificar se já existe um bem com o mesmo id
        for (Bem bem : Model.bens) {
            if (bem.getId() == id) {
                throw new BemException(BemException.EXISTENTE, "O bem com o ID " + id + " já existe.");
            }
        }
        // Criar um novo TipoBem
        TipoBem tipoBem = new TipoBem(tipo, descricao, depreciacao);

        // Criar um novo Bem
        Bem novoBem = new Bem(id, nome, tipoBem, false);

        // Adicionar o novo bem à lista
        Model.bens.add(novoBem);
    }

    public void editarBem(int id, String nome, String descricao, int depreciacao, String tipo) throws BemException {
        boolean encontrado = false;

        for (Bem bem : Model.bens) {
            if (bem.getId() == id) {
                encontrado = true;
                bem.setNome(nome);
                bem.getTipo().setNome(tipo);
                bem.getTipo().setDescricao(descricao);
                bem.getTipo().setDepreciacaoAnual(depreciacao);
                break;
            }
        }

        if (!encontrado) {
            throw new BemException(BemException.NAO_ENCONTRADO, "Bem com o ID " + id + " não encontrado.");
        }
    }

    public void removerBem(int id) throws BemException {
        boolean encontrado = false;

        // Usando um iterator para garantir que a remoção seja segura e eficiente
        Iterator<Bem> iterator = Model.bens.iterator();
        while (iterator.hasNext()) {
            Bem bem = iterator.next();
            if (bem.getId() == id) {
                iterator.remove(); // Remove o bem da lista
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            throw new BemException(BemException.NAO_ENCONTRADO, "Bem com o ID " + id + " não encontrado.");
        }
    }

    public String listarBens() {
        StringBuilder lista = new StringBuilder();
        for (Bem bem : Model.bens) {
            lista.append("ID: ").append(bem.getId()).append(", Nome: ").append(bem.getNome()).append(", Tipo: ").append(bem.getTipo().getNome()).append("\n");
        }
        return lista.toString();
    }
}
