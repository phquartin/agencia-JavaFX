package com.agencia.project.controller.client;

import com.agencia.project.model.client.ClientModel;
import com.agencia.project.model.client.Nationality;
import com.agencia.project.service.ClientService;
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
                // Remove o listener (se houver) para evitar comportamento desnecessário
                documentoField.textProperty().removeListener((observable, oldValue, newValue) -> {});
            } else if ("Nacional".equals(nacionalidade)) {
                documentoField.setDisable(false);
                documentoField.setPromptText("CPF");

                // Remove qualquer listener existente antes de adicionar um novo
                documentoField.textProperty().removeListener((observable, oldValue, newValue) -> {});

                // Adiciona o listener para formatação do CPF
                documentoField.textProperty().addListener((observable, oldValue, newValue) -> {
                    // Remove caracteres não numéricos
                    String numericValue = newValue.replaceAll("\\D", "");

                    // Limita a 11 dígitos
                    if (numericValue.length() > 11) {
                        numericValue = numericValue.substring(0, 11);
                    }

                    // Verifica se os 11 dígitos foram digitados e aplica a formatação
                    if (numericValue.length() == 11) {
                        StringBuilder formattedValue = getFormattedValue(numericValue);
                        documentoField.setText(formattedValue.toString());
                    }
                });
            }
        });


        Button enviarButton = new Button("Enviar");
            enviarButton.setStyle("-fx-font-size: 16; -fx-background-color: #4caf50; -fx-text-fill: white; -fx-padding: 10;");
            enviarButton.setOnAction(event -> {
                try {
                    // Validações usando os validadores
                    String name = nomeField.getText().trim();
                    NameValidator.validate(name);

                    String ageStr = idadeField.getText().trim();
                    AgeValidator.validate(ageStr);

                    String email = emailField.getText().trim();
                    EmailValidator.validate(email);

                    String nationalityStr = nacionalidadeCombo.getValue();
                    String document = documentoField.getText().trim();
                    DocumentValidator.validate(nationalityStr, document);

                    // Dados válidos, criar instância do cliente
                    int age = Integer.parseInt(ageStr);
                    Nationality nationality = "Nacional".equals(nationalityStr) ? Nationality.NACIONAL : Nationality.ESTRANGEIRO;
                    ClientModel client = ClientModel.builder()
                            .name(name)
                            .age(age)
                            .email(email)
                            .document(document)
                            .nationality(nationality)
                            .build();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Formulário Enviado");
                    alert.setHeaderText(null);
                    alert.setContentText("Cliente criado com sucesso");
                    alert.showAndWait();

                    ClientService.save(client);

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

    private static StringBuilder getFormattedValue(String numericValue) {
        StringBuilder formattedValue = new StringBuilder();
        for (int i = 0; i < numericValue.length(); i++) {
            formattedValue.append(numericValue.charAt(i));
            if (i == 2 || i == 5) {
                formattedValue.append(".");
            } else if (i == 8) {
                formattedValue.append("-");
            }
        }
        return formattedValue;
    }
}
