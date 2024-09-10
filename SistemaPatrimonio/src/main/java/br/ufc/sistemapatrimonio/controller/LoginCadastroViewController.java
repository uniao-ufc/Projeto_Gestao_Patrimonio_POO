package br.ufc.sistemapatrimonio.controller;

import br.ufc.sistemapatrimonio.entities.Usuario;
import br.ufc.sistemapatrimonio.enums.TipoUsuario;
import br.ufc.sistemapatrimonio.exceptions.UsuarioException;
import br.ufc.sistemapatrimonio.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class LoginCadastroViewController {
    private final Model model = new Model();

    @FXML
    private TextField logLogin;  // campo para login do usuário

    @FXML
    private PasswordField logSenha;  // campo para senha do usuário

    @FXML
    private TextField cadLogin;  // campo para login de cadastro

    @FXML
    private PasswordField cadSenha;  // campo para senha de cadastro

    @FXML
    private Button btnSair;  // botão para sair

    @FXML
    private Button btnCadastro;  // botão para cadastro

    @FXML
    private Button btnLogin;  // botão para login

    @FXML
    private RadioButton radioAdmin;  // botão de opção para administrador

    @FXML
    private RadioButton radioRequi;  // botão de opção para requisitante

    private String login;  // variável para armazenar o login

    private String senha;  // variável para armazenar a senha

    private String cadastroLogin;  // variável para armazenar o login de cadastro

    private String cadastroSenha;  // variável para armazenar a senha de cadastro

    private TipoUsuario tipo;  // variável para armazenar o tipo de usuário

    @FXML
    // cadastra o usuário
    void cadastrar(ActionEvent event) {
        try {
            if (cadLogin.getText().trim().isEmpty() || cadSenha.getText().trim().isEmpty() || !radioAdmin.isSelected() && !radioRequi.isSelected()) {
                model.mostrarPopup("Erro", "Informações não inseridas", AlertType.WARNING);  // mostra um popup se informações não forem inseridas
            } else {
                cadastroLogin = cadLogin.getText().trim();  // obtém o login de cadastro
                cadastroSenha = cadSenha.getText().trim();  // obtém a senha de cadastro

                if (radioAdmin.isSelected()) {
                    tipo = TipoUsuario.ADMINISTRADOR;  // define o tipo de usuário como administrador

                    model.getCadastroModel().cadastrarUsuario(cadastroLogin, cadastroSenha, tipo);  // cadastra o usuário

                    model.mostrarPopup("Sucesso", "Usuário cadastrado com sucesso!", Alert.AlertType.INFORMATION);  // mostra um popup de sucesso

                } else if (radioRequi.isSelected()) {
                    tipo = TipoUsuario.REQUISITANTE;  // define o tipo de usuário como requisitante

                    model.getCadastroModel().cadastrarUsuario(cadastroLogin, cadastroSenha, tipo);  // cadastra o usuário

                    model.mostrarPopup("Sucesso", "Usuário cadastrado com sucesso!", Alert.AlertType.INFORMATION);  // mostra um popup de sucesso
                } else {
                    model.mostrarPopup("Erro", "Informações não inseridas!", Alert.AlertType.WARNING);  // mostra um popup se informações não forem inseridas
                }
            }

        } catch (UsuarioException e) {
            model.mostrarPopup("Erro", "Usuario já existe!", Alert.AlertType.INFORMATION);  // mostra um popup se o usuário já existir
            throw new RuntimeException(e);  // lança uma exceção em tempo de execução
        } catch (IOException e) {
            model.mostrarPopup("Erro", "Algum erro ocorreu", AlertType.ERROR);  // mostra um popup se ocorrer algum erro de entrada/saída
            throw new RuntimeException(e);  // lança uma exceção em tempo de execução
        }
    }

    @FXML
    void logarUsuario(ActionEvent event) throws IOException, UsuarioException {
        try {
            if (logLogin.getText().trim().isEmpty() || logSenha.getText().trim().isEmpty()) {
                model.mostrarPopup("Erro", "Informações não inseridas", AlertType.WARNING);  // mostra um popup se informações não forem inseridas
            } else {
                login = logLogin.getText().trim();  // obtém o login
                senha = logSenha.getText().trim();  // obtém a senha

                Usuario usuarioEncontrado = model.getTelaLoginCadastroModel().usuarioEncontrado(login, senha);  // encontra o usuário

                if (usuarioEncontrado != null) {
                    HashMap<Integer, Usuario> resultado = model.getCadastroModel().autenticarUsuario(login, senha, usuarioEncontrado.getTipoUsuario());  // autentica o usuário

                    if (resultado.containsKey(1)) {
                        //model.mostrarPopup("Sucesso", "Logado", AlertType.INFORMATION);  // mostra um popup de sucesso

                        Model.setUsuarioAutenticado(resultado.get(1));  // define o usuário autenticado

                        // ativa a tela correta com base no tipo de usuário autenticado
                        if (Model.getUsuarioAutenticado().getTipoUsuario() == TipoUsuario.REQUISITANTE) {
                            ScreenController.activate("telaRequisitanteRequisicoes");  // ativa a tela de requisições do requisitante
                        } else if (Model.getUsuarioAutenticado().getTipoUsuario() == TipoUsuario.ADMINISTRADOR) {
                            ScreenController.activate("telaAdminRequisicoes");  // ativa a tela de requisições do administrador
                        }
                    }
                } else {
                    model.mostrarPopup("Erro", "Usuário ou senha incorretos", AlertType.ERROR);  // mostra um popup se usuário ou senha estiverem incorretos
                }
            }
        } catch (UsuarioException e) {
            model.mostrarPopup("Erro", "Usuário ou senha incorretos", AlertType.ERROR);  // mostra um popup se usuário ou senha estiverem incorretos
            throw new RuntimeException(e);  // lança uma exceção em tempo de execução
        } catch (IOException e) {
            model.mostrarPopup("Erro", "Algum erro ocorreu", AlertType.ERROR);  // mostra um popup se ocorrer algum erro de entrada/saída
            throw new RuntimeException(e);  // lança uma exceção em tempo de execução
        }
        //System.out.println(Model.getUsers());  // imprime os usuários para depuração
    }
}
