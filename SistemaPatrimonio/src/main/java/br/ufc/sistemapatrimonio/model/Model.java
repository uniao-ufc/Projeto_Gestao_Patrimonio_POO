package br.ufc.sistemapatrimonio.model;

import br.ufc.sistemapatrimonio.entities.*;
import br.ufc.sistemapatrimonio.enums.TipoUsuario;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Model {
    // Listas estáticas para armazenar os dados do sistema
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

    // Método para obter o modelo de tela de login e cadastro
    public TelaLoginCadastroModel getTelaLoginCadastroModel() {
        return telaLoginCadastroModel;
    }

    // Método para obter o usuário autenticado
    public static Usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }

    // Método para definir o usuário autenticado
    public static void setUsuarioAutenticado(Usuario usuarioAutenticado) {
        Model.usuarioAutenticado = usuarioAutenticado;
    }

    // Método para deslogar o usuário
    public static void deslogarUsuario() {
        usuarioAutenticado = null;
    }

    // Método para adicionar um bem à lista do usuário autenticado
    public static void adicionarBem(Bem bem) {
        // Verifica se o bem não é nulo e não está alocado
        if (bem != null && !bem.isAlocstatus()) {
            // Adiciona o bem à lista de bens do usuário autenticado
            usuarioAutenticado.getMeusBens().add(bem);
        }
    }

    // Método para adicionar um patrimônio à lista do usuário autenticado
    public static void adicionarPatrimonio(Patrimonio patrimonio) {
        // Verifica se o patrimônio não é nulo e não está alocado
        if (patrimonio != null && !patrimonio.isAlocstatus()) {
            // Adiciona o patrimônio à lista de patrimônios do usuário autenticado
            usuarioAutenticado.getMeusPatrimonios().add(patrimonio);
        }
    }

    // Método para obter a lista de patrimônios
    public static List<Patrimonio> getPatrimonios() {
        return patrimonios;
    }

    // Método para obter a lista de bens
    public static List<Bem> getBens() {
        return bens;
    }

    // Método para obter a lista de usuários
    public static List<Usuario> getUsers() {
        return users;
    }

    // Método para obter o modelo de administração
    public AdminModel getAdminModel() {
        return adminModel;
    }

    // Método para obter a lista de requisições de manutenção
    public List<RequisicaoDeManutencao> getRequisicaoDeManutencao() {
        return requisicaoDeManutencao;
    }

    // Método para obter o modelo de cadastro
    public TelaLoginCadastroModel getCadastroModel() {
        return cadastroModel;
    }

    // Método para obter a lista de requisições de reserva
    public List<RequisicaoDeReserva> getrequisicaoDeReservas() {
        return requisicaoDeReservas;
    }

    // Método para obter o modelo de usuário
    public UsuarioModel getUsuarioModel() {
        return usuarioModel;
    }

    // Método para exibir um alerta na tela
    public void mostrarPopup(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo); // Cria um alerta com o tipo especificado
        alerta.setTitle(titulo); // Define o título do alerta
        alerta.setHeaderText(null); // Define o texto do cabeçalho do alerta como nulo
        alerta.setContentText(mensagem); // Define o conteúdo do alerta
        alerta.showAndWait(); // Exibe o alerta e aguarda o fechamento
    }

    // Método para obter o modelo de requisitante
    public RequisitanteModel getRequisitanteModel() {
        return requisitanteModel;
    }

    // Método para remover requisições de manutenção ou reserva de um usuário
    public void removerDosUsuarios(String metodo, int id, String criador) {
        List<Usuario> usuarios = Model.getUsers();
        if (Objects.equals(metodo, "Manutencao")) {
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
                    // Obtém o iterator para a lista de requisições de reserva
                    Iterator<RequisicaoDeReserva> iterator = usuario.getMinhasRequisicaoDeReservas().iterator();

                    // Itera sobre a lista de requisições e remove a que tiver o ID correspondente
                    while (iterator.hasNext()) {
                        RequisicaoDeReserva reserva = iterator.next();
                        if (reserva.getId() == id) {
                            iterator.remove(); // Remove a requisição de reserva da lista
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
                    // Obtém o iterator para a lista de requisições de reserva
                    Iterator<RequisicaoDeReserva> iterator = usuario.getMinhasRequisicaoDeReservas().iterator();

                    // Itera sobre a lista de requisições e remove a que tiver o ID correspondente
                    while (iterator.hasNext()) {
                        RequisicaoDeReserva reserva = iterator.next();
                        if (reserva.getId() == id) {
                            iterator.remove(); // Remove a requisição de reserva da lista
                            break; // Sai do loop após a remoção
                        }
                    }

                    break; // Sai do loop após encontrar o usuário
                }
            }
        } else {
            throw new RuntimeException(); // Lança uma exceção caso o método não seja reconhecido
        }
    }
}
