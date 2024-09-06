package br.ufc.sistemapatrimonio.entities;

import br.ufc.sistemapatrimonio.model.RequisicaoDeManutencao;

import java.util.ArrayList;
import java.util.HashMap;

public class Requisitante extends Usuario {
    private final HashMap<Integer, Bem> meusBens = new HashMap<>();
    private final HashMap<Integer, RequisicaoDeManutencao> minhasManutencoes = new HashMap<>();

    public Requisitante(String username, String password) {
        super(username, password);
    }

    public void solicitarManutencao(Bem bem, String descricao) {
        RequisicaoDeManutencao requisicao = new RequisicaoDeManutencao(descricao);
        minhasManutencoes.put(requisicao.getId(), requisicao);
    }

    public ArrayList<Bem> listarMeusBens() {
        return new ArrayList<>(meusBens.values());
    }

    public ArrayList<RequisicaoDeManutencao> listarMinhasManutencoes() {
        return new ArrayList<>(minhasManutencoes.values());
    }

    public void adicionarBem(Bem bem) {
        meusBens.put(bem.getId(), bem);
    }

    public void removerBem(int id) throws Exception {
        if (meusBens.containsKey(id)) {
            meusBens.remove(id);
        } else {
            throw new Exception("Bem não encontrado.");
        }
    }
}
