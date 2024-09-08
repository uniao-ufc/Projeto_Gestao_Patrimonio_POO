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
    private TextField logLogin;

    @FXML
    private PasswordField logSenha;

    @FXML
    private TextField cadLogin;

    @FXML
    private PasswordField cadSenha;

    @FXML
    private Button btnSair;

    @FXML
    private Button btnCadastro;

    @FXML
    private Button btnLogin;

    @FXML
    private RadioButton radioAdmin;

    @FXML
    private RadioButton radioRequi;

    private String login;

    private String senha;

    private String cadastroLogin;

    private String cadastroSenha;

    private TipoUsuario tipo;

    @FXML
        // Cadastra o usuario
    void cadastrar(ActionEvent event) {
        try {
            if (cadLogin.getText().trim().isEmpty() || cadSenha.getText().trim().isEmpty() || !radioAdmin.isSelected() && !radioRequi.isSelected()) {
                model.mostrarPopup("Erro", "Informações não inseridas", AlertType.WARNING);
            } else {
                cadastroLogin = cadLogin.getText().trim();
                cadastroSenha = cadSenha.getText().trim();

                if (radioAdmin.isSelected()) {
                    tipo = TipoUsuario.ADMINISTRADOR;

                    model.getCadastroModel().cadastrarUsuario(cadastroLogin, cadastroSenha, tipo);

                    model.mostrarPopup("Sucesso", "Usuário cadastrado com sucesso!", Alert.AlertType.INFORMATION);

                } else if (radioRequi.isSelected()) {
                    tipo = TipoUsuario.REQUISITANTE;

                    model.getCadastroModel().cadastrarUsuario(cadastroLogin, cadastroSenha, tipo);

                    model.mostrarPopup("Sucesso", "Usuário cadastrado com sucesso!", Alert.AlertType.INFORMATION);
                } else {
                    model.mostrarPopup("Erro", "Informações não inseridas!", Alert.AlertType.WARNING);
                }
            }

        } catch (UsuarioException e) {
            model.mostrarPopup("Erro", "Usuario já existe!", Alert.AlertType.INFORMATION);
            throw new RuntimeException(e);
        } catch (IOException e) {
            model.mostrarPopup("Erro", "Algum erro ocorreu", AlertType.ERROR);
            throw new RuntimeException(e);
        }
    }

    @FXML
    void logarUsuario(ActionEvent event) throws IOException, UsuarioException {
        try {
            if (logLogin.getText().trim().isEmpty() || logSenha.getText().trim().isEmpty()) {
                model.mostrarPopup("Erro", "Informações não inseridas", AlertType.WARNING);
            } else {
                login = logLogin.getText().trim();
                senha = logSenha.getText().trim();

                Usuario usuarioEncontrado = model.getTelaLoginCadastroModel().usuarioEncontrado(login, senha);

                if (usuarioEncontrado != null) {
                    HashMap<Integer, Usuario> resultado = model.getCadastroModel().autenticarUsuario(login, senha, usuarioEncontrado.getTipoUsuario());

                    if (resultado.containsKey(1)) {
                        //model.mostrarPopup("Sucesso", "Logado", AlertType.INFORMATION);

                        Model.setUsuarioAutenticado(resultado.get(1));

                        // Ativa a tela correta com base no tipo de usuário autenticado
                        if (Model.getUsuarioAutenticado().getTipoUsuario() == TipoUsuario.REQUISITANTE) {
                            ScreenController.activate("telaRequisitanteRequisicoes");
                        } else if (Model.getUsuarioAutenticado().getTipoUsuario() == TipoUsuario.ADMINISTRADOR) {
                            ScreenController.activate("telaAdminRequisicoes");
                        }
                    }
                } else {
                    model.mostrarPopup("Erro", "Usuário ou senha incorretos", AlertType.ERROR);
                }
            }
        } catch (UsuarioException e) {
            model.mostrarPopup("Erro", "Usuário ou senha incorretos", AlertType.ERROR);
            throw new RuntimeException(e);
        } catch (IOException e) {
            model.mostrarPopup("Erro", "Algum erro ocorreu", AlertType.ERROR);
            throw new RuntimeException(e);
        }
        System.out.println(Model.getUsers());
    }
}