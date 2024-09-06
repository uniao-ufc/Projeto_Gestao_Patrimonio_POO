package br.ufc.sistemapatrimonio.model;
import java.util.ArrayList;
import java.util.HashMap;

public class Requisitante extends Usuario{
    private final HashMap<Integer, Bem> meusBens = new HashMap<>();
    private final HashMap<Integer, RequisicaoDeManutencao> minhasManutencoes = new HashMap<>();

    public Requisitante(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    // Métodos para acessar os dados pessoais
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Métodos para solicitar manutenção de bens
    public void solicitarManutencao(Bem bem, String descricao) {
        RequisicaoDeManutencao requisicao = new RequisicaoDeManutencao(descricao);
        minhasManutencoes.put(requisicao.getId(), requisicao);
    }

    // Métodos para visualizar seus bens
    public ArrayList<Bem> listarMeusBens() {
        return new ArrayList<>(meusBens.values());
    }

    // Método para visualizar as requisições de manutenção feitas
    public ArrayList<RequisicaoDeManutencao> listarMinhasManutencoes() {
        return new ArrayList<>(minhasManutencoes.values());
    }

    // Método para adicionar um bem ao requisitante
    public void adicionarBem(Bem bem) {
        meusBens.put(bem.getId(), bem);
    }

    // Método para remover um bem do requisitante
    public void removerBem(int id) throws Exception {
        if (meusBens.containsKey(id)) {
            meusBens.remove(id);
        } else {
            throw new Exception("Bem não encontrado.");
        }
    }
}

