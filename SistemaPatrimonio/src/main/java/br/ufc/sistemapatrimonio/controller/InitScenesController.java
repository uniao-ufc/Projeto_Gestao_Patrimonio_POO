package br.ufc.sistemapatrimonio.controller;

import br.ufc.sistemapatrimonio.HelloApplication;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class InitScenesController {
    public static void initScenes() throws IOException {
        FXMLLoader telaLoginLoader = new FXMLLoader(HelloApplication.class.getResource("telaLoginCadastro.fxml"));  // carrega o arquivo FXML para a tela de login
        ScreenController.addScreen("telaLogin", telaLoginLoader);  // adiciona a tela de login ao controlador de telas

        // tela do requisitante
        FXMLLoader telaRequisitanteRequisicoesLoader = new FXMLLoader(HelloApplication.class.getResource("telaRequisitanteRequisicoes.fxml"));  // carrega o arquivo FXML para a tela de requisições do requisitante
        ScreenController.addScreen("telaRequisitanteRequisicoes", telaRequisitanteRequisicoesLoader);  // adiciona a tela de requisições do requisitante ao controlador de telas
        
        FXMLLoader telaRequisitanteManutencoesLoader = new FXMLLoader(HelloApplication.class.getResource("telaRequisitanteManutencoes.fxml"));  // carrega o arquivo FXML para a tela de manutenções do requisitante
        ScreenController.addScreen("telaRequisitanteManutencoes", telaRequisitanteManutencoesLoader);  // adiciona a tela de manutenções do requisitante ao controlador de telas
        
        FXMLLoader telaAdminRequisicoesLoader = new FXMLLoader(HelloApplication.class.getResource("telaAdminRequisicoes.fxml"));  // carrega o arquivo FXML para a tela de requisições do administrador
        ScreenController.addScreen("telaAdminRequisicoes", telaAdminRequisicoesLoader);  // adiciona a tela de requisições do administrador ao controlador de telas
        
        FXMLLoader telaAdminManutencoesLoader = new FXMLLoader(HelloApplication.class.getResource("telaAdminManutencoes.fxml"));  // carrega o arquivo FXML para a tela de manutenções do administrador
        ScreenController.addScreen("telaAdminManutencoes", telaAdminManutencoesLoader);  // adiciona a tela de manutenções do administrador ao controlador de telas
        
        FXMLLoader telaAdminBensLoader = new FXMLLoader(HelloApplication.class.getResource("telaAdminBens.fxml"));  // carrega o arquivo FXML para a tela de bens do administrador
        ScreenController.addScreen("telaAdminBens", telaAdminBensLoader);  // adiciona a tela de bens do administrador ao controlador de telas
        
        FXMLLoader telaAdminPatrimoniosLoader = new FXMLLoader(HelloApplication.class.getResource("telaAdminPatrimonios.fxml"));  // carrega o arquivo FXML para a tela de patrimônios do administrador
        ScreenController.addScreen("telaAdminPatrimonios", telaAdminPatrimoniosLoader);  // adiciona a tela de patrimônios do administrador ao controlador de telas
        
        FXMLLoader telaAdminHistoricoLoader = new FXMLLoader(HelloApplication.class.getResource("telaAdminHistorico.fxml"));  // carrega o arquivo FXML para a tela de histórico do administrador
        ScreenController.addScreen("telaAdminHistorico", telaAdminHistoricoLoader);  // adiciona a tela de histórico do administrador ao controlador de telas
    }
}
