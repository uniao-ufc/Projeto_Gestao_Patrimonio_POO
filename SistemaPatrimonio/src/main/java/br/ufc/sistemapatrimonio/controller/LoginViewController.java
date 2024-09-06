package br.ufc.sistemapatrimonio.controller;
import br.ufc.sistemapatrimonio.enums.TipoUsuario;
import br.ufc.sistemapatrimonio.model.Model;
import br.ufc.sistemapatrimonio.model.Observer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class LoginViewController implements Observer {
    private final Model model = new Model();
    private String login;
    private String senha;
    private TipoUsuario tipo;

    public void initLoginView(){
        model.attachObserver(this);
    }
    @Override
    public void update() {

    }

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


    @FXML
    void cadastrar(ActionEvent event) {
        btnCadastro.setText(model.ret());
    }

    @FXML
    void logarUsuario(ActionEvent event) {
    }
}