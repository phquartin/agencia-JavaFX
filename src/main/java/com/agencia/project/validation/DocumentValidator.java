package com.agencia.project.validation;

public class DocumentValidator {
    public static void validate(String nationality, String document) throws Exception {
        switch (nationality) {
            case "Nacional":
                if (!document.matches("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$")) {
                    throw new Exception("O CPF fornecido é inválido.");
                }
                break;
            case "Estrangeiro":
                if (!document.matches("^[A-Z]{2}\\d{6,9}$")) {
                    throw new Exception("O Passaporte fornecido é inválido.");
                }
                break;
            default:
                throw new Exception("Nacionalidade não reconhecida.");
        }
    }
}
