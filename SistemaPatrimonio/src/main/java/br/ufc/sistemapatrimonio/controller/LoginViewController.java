package br.ufc.sistemapatrimonio.controller;
import br.ufc.sistemapatrimonio.entities.Administrador;
import br.ufc.sistemapatrimonio.entities.Requisitante;
import br.ufc.sistemapatrimonio.entities.Usuario;
import br.ufc.sistemapatrimonio.enums.TipoUsuario;
import br.ufc.sistemapatrimonio.model.Model;
import br.ufc.sistemapatrimonio.model.Observer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class LoginViewController{
    private final Model model = new Model();


    @FXML
    private Button btnCadastro;

    @FXML
    private Button btnLogin;

    @FXML
    private TextField cadastroLogin;

    @FXML
    private PasswordField cadastroSenha;

    @FXML
    private TextField loginLogin;

    @FXML
    private PasswordField loginSenha;

    @FXML
    private RadioButton radioAdmin;

    @FXML
    private RadioButton radioRequi;

    public Button getBtn(){
        return btnCadastro;
    }
    public String getf(){
        return cadastroLogin.getText();
    }

    private String login;
    private String senha;
    private String cadlogin;
    private String cadsenha;
    private final TipoUsuario tipo = TipoUsuario.ADMINISTRADOR;

    private int userCount = 1;

    @FXML
    void cadastrar(ActionEvent event) {

    }

    @FXML
    void logarUsuario(ActionEvent event) {
        login = loginLogin.getText().trim();
        senha = loginSenha.getText().trim();
        System.out.println(model.cadastroModel.autenticarUsuario(login, senha, tipo));
    }
}