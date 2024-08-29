import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DataPersistence {
    public static void saveData(Model model, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(model.listarBens());
            oos.writeObject(model.listarPatrimonios());
            oos.writeObject(model.listarUsuarios());
            oos.writeObject(model.listarManutencoes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadData(Model model, String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            ArrayList<Bem> bens = (ArrayList<Bem>) ois.readObject();
            ArrayList<Patrimonio> patrimonios = (ArrayList<Patrimonio>) ois.readObject();
            ArrayList<Usuario> usuarios = (ArrayList<Usuario>) ois.readObject();
            ArrayList<RequisicaoDeManutencao> manutencoes = (ArrayList<RequisicaoDeManutencao>) ois.readObject();

            // Limpa os dados atuais
            model.getBensMap().clear();
            model.getPatrimoniosMap().clear();
            model.getUsuariosMap().clear();
            model.getManutencoesMap().clear();

            // Adiciona os dados carregados
            for (Bem bem : bens) {
                model.adicionarBem(bem);
            }
            for (Patrimonio patrimonio : patrimonios) {
                model.adicionarPatrimonio(patrimonio);
            }
            for (Usuario usuario : usuarios) {
                model.adicionarUsuario(usuario);
            }
            for (RequisicaoDeManutencao requisicao : manutencoes) {
                model.adicionarRequisicaoDeManutencao(requisicao);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
