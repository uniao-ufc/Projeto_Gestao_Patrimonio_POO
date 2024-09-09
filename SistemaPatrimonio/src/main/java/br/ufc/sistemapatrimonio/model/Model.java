package br.ufc.sistemapatrimonio.model;

import br.ufc.sistemapatrimonio.entities.*;
import br.ufc.sistemapatrimonio.enums.TipoUsuario;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Model {
    private static final List<Usuario> users = new ArrayList<>();
    private static final List<Patrimonio> patrimonios = new ArrayList<>();
    private static final List<Bem> bens = new ArrayList<>();
    private static final List<RequisicaoDeReserva> requisicaoDeReservas = new ArrayList<>();
    private static final List<RequisicaoDeManutencao> requisicaoDeManutencao = new ArrayList<>();
    private static final RequisitanteModel requisitanteModel = new RequisitanteModel();
    private static final TelaLoginCadastroModel cadastroModel = new TelaLoginCadastroModel();
    private static final UsuarioModel usuarioModel = new UsuarioModel();
    private static final AdminModel adminModel = new AdminModel();
    private static final TelaLoginCadastroModel telaLoginCadastroModel = new TelaLoginCadastroModel();
    private static Usuario usuarioAutenticado = new Usuario("", "", TipoUsuario.START);

    public TelaLoginCadastroModel getTelaLoginCadastroModel() {
        return telaLoginCadastroModel;
    }

    public static Usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }

    public static void setUsuarioAutenticado(Usuario usuarioAutenticado) {
        Model.usuarioAutenticado = usuarioAutenticado;
    }

    public static void deslogarUsuario() {
        usuarioAutenticado = null;
    }

    public static void adicionarBem(Bem bem) {
        // Verifica se o bem existe e se está disponível
        if (bem != null && !bem.isAlocstatus()) {
            // Adiciona à lista do usuário
            usuarioAutenticado.getMeusBens().add(bem);
        }
    }

    public static void adicionarPatrimonio(Patrimonio patrimonio) {
        // Verifica se o patrimônio existe e se está disponível
        if (patrimonio != null && !patrimonio.isAlocstatus()) {
            // Adiciona à lista do usuário
            usuarioAutenticado.getMeusPatrimonios().add(patrimonio);
        }
    }

    public static List<Patrimonio> getPatrimonios() {
        return patrimonios;
    }

    public static List<Bem> getBens() {
        return bens;
    }

    public static List<Usuario> getUsers() {
        return users;
    }

    public AdminModel getAdminModel() {
        return adminModel;
    }

    public List<RequisicaoDeManutencao> getRequisicaoDeManutencao() {
        return requisicaoDeManutencao;
    }

    public TelaLoginCadastroModel getCadastroModel() {
        return cadastroModel;
    }

    public List<RequisicaoDeReserva> getrequisicaoDeReservas() {
        return requisicaoDeReservas;
    }

    public UsuarioModel getUsuarioModel() {
        return usuarioModel;
    }

    public void mostrarPopup(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

    // Métodos para RequisitanteModel.java
    public RequisitanteModel getRequisitanteModel() {
        return requisitanteModel;
    }

    public void removerDosUsuarios(String metodo, int id, String criador){
        List<Usuario> usuarios = Model.getUsers();
        if(Objects.equals(metodo, "Manutencao")){
            for (Usuario usuario : usuarios) {
                if (usuario.getUsername().equals(criador)) {
                    // Obtém o iterator para a lista de manutenções
                    Iterator<RequisicaoDeManutencao> iterator = usuario.getMinhasManutencoes().iterator();

                    // Itera sobre a lista de manutenções e remove a que tiver o ID correspondente
                    while (iterator.hasNext()) {
                        RequisicaoDeManutencao manutencao = iterator.next();
                        if (manutencao.getId() == id) {
                            iterator.remove(); // Remove a requisição de manutenção da lista
                            break; // Sai do loop após a remoção
                        }
                    }

                    break; // Sai do loop após encontrar o usuário
                }
            }
        } else if (Objects.equals(metodo, "Requisição")) {
            for (Usuario usuario : usuarios) {
                if (usuario.getUsername().equals(criador)) {
                    // Obtém o iterator para a lista de manutenções
                    Iterator<RequisicaoDeReserva> iterator = usuario.getMinhasRequisicaoDeReservas().iterator();

                    // Itera sobre a lista de manutenções e remove a que tiver o ID correspondente
                    while (iterator.hasNext()) {
                        RequisicaoDeReserva reserva = iterator.next();
                        if (reserva.getId() == id) {
                            iterator.remove(); // Remove a requisição de manutenção da lista
                            break; // Sai do loop após a remoção
                        }
                    }

                    break; // Sai do loop após encontrar o usuário
                }
            }

        } else if (Objects.equals(metodo, "ambos")) {
            for (Usuario usuario : usuarios) {
                if (usuario.getUsername().equals(criador)) {
                    // Obtém o iterator para a lista de manutenções
                    Iterator<RequisicaoDeManutencao> iterator = usuario.getMinhasManutencoes().iterator();

                    // Itera sobre a lista de manutenções e remove a que tiver o ID correspondente
                    while (iterator.hasNext()) {
                        RequisicaoDeManutencao manutencao = iterator.next();
                        if (manutencao.getId() == id) {
                            iterator.remove(); // Remove a requisição de manutenção da lista
                            break; // Sai do loop após a remoção
                        }
                    }

                    break; // Sai do loop após encontrar o usuário
                }
            }
            for (Usuario usuario : usuarios) {
                if (usuario.getUsername().equals(criador)) {
                    // Obtém o iterator para a lista de manutenções
                    Iterator<RequisicaoDeReserva> iterator = usuario.getMinhasRequisicaoDeReservas().iterator();

                    // Itera sobre a lista de manutenções e remove a que tiver o ID correspondente
                    while (iterator.hasNext()) {
                        RequisicaoDeReserva reserva = iterator.next();
                        if (reserva.getId() == id) {
                            iterator.remove(); // Remove a requisição de manutenção da lista
                            break; // Sai do loop após a remoção
                        }
                    }

                    break; // Sai do loop após encontrar o usuário
                }
            }
        } else{
            throw new RuntimeException();
        }
    }
}
