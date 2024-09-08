package br.ufc.sistemapatrimonio.controller;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class ScreenController {
    private static final HashMap<String, Scene> sceneMap = new HashMap<>();
    private static final HashMap<String, FXMLLoader> loaderMap = new HashMap<>(); // Armazena os FXMLLoaders
    private static Stage stage;

    // Define o Stage principal
    public static void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    // Adiciona uma tela ao mapa de cenas e armazena o FXMLLoader
    public static void addScreen(String name, FXMLLoader fxmlLoader) throws IOException {
        if (!sceneMap.containsKey(name)) {
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            sceneMap.put(name, scene);
            loaderMap.put(name, fxmlLoader); // Armazena o FXMLLoader
        }
    }

    // Ativa uma tela pelo nome
    public static void activate(String name) throws IOException {
        Scene scene = sceneMap.get(name);
        if (scene == null) {
            throw new IOException("Tela não encontrada: " + name);
        }

        // Atualiza a cena com a nova Scene
        stage.setScene(scene);
        stage.setTitle("Sistema de Patrimonio");
        stage.show();

        switch (name) {
            case "telaRequisitanteRequisicoes" -> {
                RequisitanteRequisicoesViewController controller = ScreenController.getController(name);
                if (controller != null) {
                    controller.atualizarListaRequisicoes();
                }
            }
            case "telaRequisitanteManutencoes" -> {
                RequisitanteManutencoesViewController controller = ScreenController.getController(name);
                if (controller != null) {
                    controller.atualizarListaManutencoes();
                }
            }
            case "telaAdminRequisicoes" -> {
                AdminRequisicoesViewController controller = ScreenController.getController(name);
                if (controller != null) {
                    controller.atualizarListaRequisicoes();
                }
            }
            case "telaAdminManutencoes" -> {
                AdminManutencoesViewController controller = ScreenController.getController(name);
                if (controller != null) {
                    controller.atualizarListaManutencoes();
                }
            }
            case "telaAdminBens" -> {
                AdminBensViewController controller = ScreenController.getController(name);
                if (controller != null) {
                    controller.atualizarListaBens();
                }
            }
            case "telaAdminPatrimonios" -> {
                AdminPatrimoniosViewController controller = ScreenController.getController(name);
                if (controller != null) {
                    controller.atualizarListaPatrimonios();
                }
            }
            case "telaAdminHistorico" -> {
                AdminHistoricoViewController controller = ScreenController.getController(name);
                if (controller != null) {

                }
            }
        }

        // Centraliza a tela
        screenCenter(stage);
    }

    // Centralizar a tela
    public static void screenCenter(Stage stage) {
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }

    // Método para obter o controlador da tela ativa ou de qualquer tela carregada
    public static <T> T getController(String name) {
        FXMLLoader loader = loaderMap.get(name);
        if (loader != null) {
            return loader.getController(); // Retorna o controlador da tela
        }
        return null;
    }
}