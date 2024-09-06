package br.ufc.sistemapatrimonio.model;

import java.io.*;
import java.util.ArrayList;

public class DataPersistence {
    public static void saveData(Model model, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(listarBens());
            oos.writeObject(listarPatrimonios());
            oos.writeObject(listarUsuarios());
            oos.writeObject(listarManutencoes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadData(Model.Model model, String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            ArrayList<Bem> bens = (ArrayList<Bem>) ois.readObject();
            ArrayList<Patrimonio> patrimonios = (ArrayList<Patrimonio>) ois.readObject();
            ArrayList<Usuario> usuarios = (ArrayList<Usuario>) ois.readObject();
            ArrayList<RequisicaoDeManutencao> manutencoes = (ArrayList<RequisicaoDeManutencao>) ois.readObject();

            // Limpa os dados atuais
            getBensMap().clear();
            getPatrimoniosMap().clear();
            getUsuariosMap().clear();
            getManutencoesMap().clear();

            // Adiciona os dados carregados
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
}
