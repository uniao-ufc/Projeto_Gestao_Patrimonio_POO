package br.ufc.sistemapatrimonio;

import br.ufc.sistemapatrimonio.controller.ScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    public void initScenes(){
        // Carregar telas
        // Login
        FXMLLoader telaLoginLoader = new FXMLLoader(HelloApplication.class.getResource("telaLogin.fxml"));
        ScreenController.addScreen("telaLogin", telaLoginLoader);
        // Tela do requisitante
        FXMLLoader telaRequisitanteLoader = new FXMLLoader(HelloApplication.class.getResource("telaRequisitante.fxml"));
        ScreenController.addScreen("telaRequisitante", telaRequisitanteLoader);
        //
    }
    @Override
    public void start(Stage stage){
        try {
            initScenes();
            // Ativando tela principal
            ScreenController.setStage(stage);
            // Ativar a tela inicial (login)
            ScreenController.activate("telaLogin");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}