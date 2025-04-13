package com.agencia.project;

import com.agencia.project.controller.client.CrudMenuApp;
import com.agencia.project.util.initdb.DataInicialization;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(20);
        root.setStyle("-fx-padding: 20; -fx-background-color: #f7f9fc;");

        // Botão para criar tabelas
        Button criarTabelasButton = new Button("Criar Tabelas");
        criarTabelasButton.setStyle("-fx-font-size: 16; -fx-padding: 10;");
        criarTabelasButton.setOnAction(event -> {
            try {
                DataInicialization.init();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso");
                alert.setHeaderText(null);
                alert.setContentText("Tabelas criadas com sucesso!");
                alert.showAndWait();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText("Erro ao criar tabelas: " + e.getMessage());
                alert.showAndWait();
            }
        });

        // Botão para operações de CRUD
        Button crudButton = new Button("Operações de CRUD");
        crudButton.setStyle("-fx-font-size: 16; -fx-padding: 10;");
        crudButton.setOnAction(event -> {
            // Abre o menu de CRUD
            CrudMenuApp crudMenuApp = new CrudMenuApp();
            crudMenuApp.start(new Stage());
        });

        root.getChildren().addAll(criarTabelasButton, crudButton);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Tela Inicial");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
