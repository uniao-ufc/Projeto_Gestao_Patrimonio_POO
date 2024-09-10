package br.ufc.sistemapatrimonio.controller;

import br.ufc.sistemapatrimonio.model.Model;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class ScreenController {
    private static final HashMap<String, Scene> sceneMap = new HashMap<>();  // armazena as cenas
    private static final HashMap<String, FXMLLoader> loaderMap = new HashMap<>();  // armazena os FXMLLoaders
    private static Stage stage;  // armazena o stage principal

    // define o stage principal
    public static void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    // adiciona uma tela ao mapa de cenas e armazena o FXMLLoader
    public static void addScreen(String name, FXMLLoader fxmlLoader) throws IOException {
        if (!sceneMap.containsKey(name)) {
            Parent root = fxmlLoader.load();  // carrega o FXML
            Scene scene = new Scene(root);  // cria uma nova cena
            sceneMap.put(name, scene);  // armazena a cena no mapa
            loaderMap.put(name, fxmlLoader);  // armazena o FXMLLoader no mapa
        }
    }

    // ativa uma tela pelo nome
    public static void activate(String name) throws IOException {
        Scene scene = sceneMap.get(name);  // obtém a cena pelo nome
        if (scene == null) {
            throw new IOException("Tela não encontrada: " + name);  // lança exceção se a tela não for encontrada
        }

        stage.setScene(scene);  // define a cena no stage
        String nome = Model.getUsuarioAutenticado().getUsername();  // obtém o nome do usuário autenticado
        String tipo = Model.getUsuarioAutenticado().getTipoUsuario().toString();  // obtém o tipo do usuário autenticado
        stage.setTitle("Sistema de Patrimonio " + tipo + " " + nome);  // define o título do stage
        stage.show();  // mostra o stage

        switch (name) {
            case "telaLogin" -> {
                stage.setTitle("Sistema de Patrimonio");  // define o título específico para a tela de login
            }
            case "telaRequisitanteRequisicoes" -> {
                RequisitanteRequisicoesViewController controller = ScreenController.getController(name);
                if (controller != null) {
                    controller.atualizarListaRequisicoes();  // atualiza a lista de requisições
                }
            }
            case "telaRequisitanteManutencoes" -> {
                RequisitanteManutencoesViewController controller = ScreenController.getController(name);
                if (controller != null) {
                    controller.atualizarListaManutencoes();  // atualiza a lista de manutenções
                }
            }
            case "telaAdminRequisicoes" -> {
                AdminRequisicoesViewController controller = ScreenController.getController(name);
                if (controller != null) {
                    controller.atualizarListaRequisicoes();  // atualiza a lista de requisições
                }
            }
            case "telaAdminManutencoes" -> {
                AdminManutencoesViewController controller = ScreenController.getController(name);
                if (controller != null) {
                    controller.atualizarListaManutencoes();  // atualiza a lista de manutenções
                }
            }
            case "telaAdminBens" -> {
                AdminBensViewController controller = ScreenController.getController(name);
                if (controller != null) {
                    controller.atualizarListaBens();  // atualiza a lista de bens
                }
            }
            case "telaAdminPatrimonios" -> {
                AdminPatrimoniosViewController controller = ScreenController.getController(name);
                if (controller != null) {
                    controller.atualizarListaPatrimonios();  // atualiza a lista de patrimônios
                }
            }
            case "telaAdminHistorico" -> {
                AdminHistoricoViewController controller = ScreenController.getController(name);
                if (controller != null) {
                    controller.atualizarListaReservas();  // atualiza a lista de reservas
                    controller.atualizarListaManutencoes();  // atualiza a lista de manutenções
                }
            }
        }

        // centraliza a tela
        screenCenter(stage);
    }

    // centraliza a tela
    public static void screenCenter(Stage stage) {
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();  // obtém os limites da tela principal
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);  // calcula a posição X para centralizar
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);  // calcula a posição Y para centralizar
    }

    // método para obter o controlador da tela ativa ou de qualquer tela carregada
    public static <T> T getController(String name) {
        FXMLLoader loader = loaderMap.get(name);  // obtém o FXMLLoader
        if (loader != null) {
            return loader.getController();  // retorna o controlador da tela
        }
        return null;  // retorna null se o controlador não for encontrado
    }
}
