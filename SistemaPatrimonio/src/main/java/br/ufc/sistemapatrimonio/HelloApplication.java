package br.ufc.sistemapatrimonio;

import br.ufc.sistemapatrimonio.controller.InitScenesController;
import br.ufc.sistemapatrimonio.controller.ScreenController;
import javafx.application.Application;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) {
        try {
            InitScenesController.initScenes();
            // Ativando tela principal
            ScreenController.setStage(stage);
            // Ativar a tela inicial (login)
            ScreenController.activate("telaLogin");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}