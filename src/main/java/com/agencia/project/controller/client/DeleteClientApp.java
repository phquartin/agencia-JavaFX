package com.agencia.project.controller.client;

import com.agencia.project.service.ClientService; // Comente aqui: Você pode usar o ClientService para chamar o DAO.
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DeleteClientApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(20);
        root.setStyle("-fx-padding: 20; -fx-background-color: #f7f9fc;");

        Label label = new Label("Digite o ID do Cliente para Deletar:");
        TextField idField = new TextField();
        idField.setPromptText("ID do Cliente");

        Button deletarButton = new Button("Deletar");
        deletarButton.setStyle("-fx-font-size: 16; -fx-padding: 10;");

        deletarButton.setOnAction(event -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                ClientService.delete(id);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso");
                alert.setHeaderText(null);
                alert.setContentText("Cliente deletado com sucesso.");
                alert.showAndWait();

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText("Erro: " + e.getMessage());
                alert.showAndWait();
            }
        });

        root.getChildren().addAll(label, idField, deletarButton);

        Scene scene = new Scene(root, 400, 200);
        primaryStage.setTitle("Deletar Cliente");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}