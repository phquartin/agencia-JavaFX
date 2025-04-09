package com.agencia.project;

import com.agencia.project.model.client.ClientModel;
import com.agencia.project.model.client.Nationality;
import com.agencia.project.validation.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class FormularioApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(15);
        root.setStyle("-fx-padding: 20; -fx-background-color: #f0f4f8;");

        TextField nomeField = new TextField();
        nomeField.setPromptText("Nome");
        nomeField.setStyle("-fx-font-size: 14;");

        TextField emailField = new TextField();
        emailField.setPromptText("E-mail");
        emailField.setStyle("-fx-font-size: 14;");

        TextField idadeField = new TextField();
        idadeField.setPromptText("Idade");
        idadeField.setStyle("-fx-font-size: 14;");

        ComboBox<String> nacionalidadeCombo = new ComboBox<>();
        nacionalidadeCombo.getItems().addAll("Nacional", "Estrangeiro");
        nacionalidadeCombo.setPromptText("Nacionalidade");
        nacionalidadeCombo.setStyle("-fx-font-size: 14;");

        TextField documentoField = new TextField();
        documentoField.setPromptText("Documento");
        documentoField.setDisable(true);
        documentoField.setStyle("-fx-font-size: 14;");

        nacionalidadeCombo.setOnAction(event -> {
            String nacionalidade = nacionalidadeCombo.getValue();
            if ("Estrangeiro".equals(nacionalidade)) {
                documentoField.setDisable(false);
                documentoField.setPromptText("Passaporte");
            } else if ("Nacional".equals(nacionalidade)) {
                documentoField.setDisable(false);
                documentoField.setPromptText("CPF");
            }
        });

        Button enviarButton = new Button("Enviar");
        enviarButton.setStyle("-fx-font-size: 16; -fx-background-color: #4caf50; -fx-text-fill: white; -fx-padding: 10;");
        enviarButton.setOnAction(event -> {
            try {
                // Validações usando os validadores
                String nome = nomeField.getText().trim();
                NameValidator.validate(nome);

                String idadeStr = idadeField.getText().trim();
                AgeValidator.validate(idadeStr);

                String email = emailField.getText().trim();
                EmailValidator.validate(email);

                String nacionalidadeStr = nacionalidadeCombo.getValue();
                String documento = documentoField.getText().trim();
                DocumentValidator.validate(nacionalidadeStr, documento);

                // Dados válidos, criar instância do cliente
                int idade = Integer.parseInt(idadeStr);
                Nationality nacionalidade = "Nacional".equals(nacionalidadeStr) ? Nationality.NACIONAL : Nationality.ESTRANGEIRO;
                ClientModel client = new ClientModel(nome, idade, email, documento, nacionalidade);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Formulário Enviado");
                alert.setHeaderText(null);
                alert.setContentText("Cliente criado com sucesso:\n" + client);
                alert.showAndWait();

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        });



        root.getChildren().addAll(nomeField, emailField, idadeField, nacionalidadeCombo, documentoField, enviarButton);

        Scene scene = new Scene(root, 450, 400);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
        primaryStage.setTitle("Formulário");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
