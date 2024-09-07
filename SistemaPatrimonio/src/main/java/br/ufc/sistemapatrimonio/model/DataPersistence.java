package br.ufc.sistemapatrimonio.model;

import br.ufc.sistemapatrimonio.entities.Administrador;
import br.ufc.sistemapatrimonio.entities.Bem;
import br.ufc.sistemapatrimonio.entities.Patrimonio;
import br.ufc.sistemapatrimonio.entities.Requisitante;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DataPersistence {
/*
    private final HashMap<Integer, Bem> bensMap = new HashMap<>();
    private final HashMap<Integer, Patrimonio> patrimoniosMap = new HashMap<>();
    private final HashMap<String, Requisitante> requisitantesMap = new HashMap<>();
    private final HashMap<String, Administrador> administradoresMap = new HashMap<>();
    private final HashMap<Integer, RequisicaoDeManutencao> manutencoesMap = new HashMap<>();

    // Salvando dados em arquivos
    public void saveData(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(new ArrayList<>(bensMap.values()));
            oos.writeObject(new ArrayList<>(patrimoniosMap.values()));
            oos.writeObject(new ArrayList<>(requisitantesMap.values()));
            oos.writeObject(new ArrayList<>(administradoresMap.values()));
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
            ArrayList<Requisitante> requisitantes = (ArrayList<Requisitante>) ois.readObject();
            ArrayList<Administrador> administradores = (ArrayList<Administrador>) ois.readObject();
            ArrayList<RequisicaoDeManutencao> manutencoes = (ArrayList<RequisicaoDeManutencao>) ois.readObject();

            // Limpar dados atuais
            bensMap.clear();
            patrimoniosMap.clear();
            requisitantesMap.clear();
            administradoresMap.clear();
            manutencoesMap.clear();

            // Adicionar os dados carregados
            for (Bem bem : bens) {
                adicionarBem(bem);
            }
            for (Patrimonio patrimonio : patrimonios) {
                adicionarPatrimonio(patrimonio);
            }
            for (Requisitante requisitante : requisitantes) {
                adicionarRequisitante(requisitante);
            }
            for (Administrador administrador : administradores) {
                adicionarAdministrador(administrador);
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

    public void adicionarRequisitante(Requisitante requisitante) {
        requisitantesMap.put(requisitante.getUsername(), requisitante);
    }

    public void adicionarAdministrador(Administrador administrador) {
        administradoresMap.put(administrador.getUsername(), administrador);
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

    public ArrayList<Requisitante> listarRequisitantes() {
        return new ArrayList<>(requisitantesMap.values());
    }

    public ArrayList<Administrador> listarAdministradores() {
        return new ArrayList<>(administradoresMap.values());
    }

    public ArrayList<RequisicaoDeManutencao> listarManutencoes() {
        return new ArrayList<>(manutencoesMap.values());
    }

    // Novos métodos para salvar requisitantes e administradores
    public void salvarRequisitantes(HashMap<String, Requisitante> requisitantesMap) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("requisitantes.dat"))) {
            oos.writeObject(new ArrayList<>(requisitantesMap.values()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void salvarAdministradores(HashMap<String, Administrador> administradoresMap) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("administradores.dat"))) {
            oos.writeObject(new ArrayList<>(administradoresMap.values()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}