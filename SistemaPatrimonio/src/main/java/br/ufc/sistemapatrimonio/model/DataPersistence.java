package br.ufc.sistemapatrimonio.model;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DataPersistence {

    private HashMap<Integer, Bem> bensMap = new HashMap<>();
    private HashMap<Integer, Patrimonio> patrimoniosMap = new HashMap<>();
    private HashMap<Integer, Usuario> usuariosMap = new HashMap<>();
    private HashMap<Integer, RequisicaoDeManutencao> manutencoesMap = new HashMap<>();

    // Salvando dados em arquivos
    public void saveData(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(new ArrayList<>(bensMap.values()));
            oos.writeObject(new ArrayList<>(patrimoniosMap.values()));
            oos.writeObject(new ArrayList<>(usuariosMap.values()));
            oos.writeObject(new ArrayList<>(manutencoesMap.values()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Carregando dados dos arquivos
    public void loadData(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            ArrayList<Bem> bens = (ArrayList<Bem>) ois.readObject();
            ArrayList<Patrimonio> patrimonios = (ArrayList<Patrimonio>) ois.readObject();
            ArrayList<Usuario> usuarios = (ArrayList<Usuario>) ois.readObject();
            ArrayList<RequisicaoDeManutencao> manutencoes = (ArrayList<RequisicaoDeManutencao>) ois.readObject();

            // Limpar dados atuais
            bensMap.clear();
            patrimoniosMap.clear();
            usuariosMap.clear();
            manutencoesMap.clear();

            // Adicionar os dados carregados
            for (Bem bem : bens) {
                adicionarBem(bem);
            }
            for (Patrimonio patrimonio : patrimonios) {
                adicionarPatrimonio(patrimonio);
            }
            for (Usuario usuario : usuarios) {
                adicionarUsuario(usuario);
            }
            for (RequisicaoDeManutencao requisicao : manutencoes) {
                adicionarRequisicaoDeManutencao(requisicao);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Métodos de adição
    public void adicionarBem(Bem bem) {
        bensMap.put(bem.getId(), bem);
    }

    public void adicionarPatrimonio(Patrimonio patrimonio) {
        patrimoniosMap.put(patrimonio.getId(), patrimonio);
    }

    public void adicionarUsuario(Usuario usuario) {
        usuariosMap.put(usuario.getId(), usuario);
    }

    public void adicionarRequisicaoDeManutencao(RequisicaoDeManutencao requisicao) {
        manutencoesMap.put(requisicao.getId(), requisicao);
    }

    // Métodos de obtenção de listas
    public ArrayList<Bem> listarBens() {
        return new ArrayList<>(bensMap.values());
    }

    public ArrayList<Patrimonio> listarPatrimonios() {
        return new ArrayList<>(patrimoniosMap.values());
    }

    public ArrayList<Usuario> listarUsuarios() {
        return new ArrayList<>(usuariosMap.values());
    }

    public ArrayList<RequisicaoDeManutencao> listarManutencoes() {
        return new ArrayList<>(manutencoesMap.values());
    }
}
