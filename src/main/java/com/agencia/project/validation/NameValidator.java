package com.agencia.project.validation;

public class NameValidator {
    public static void validate(String name) throws Exception {
        if (!name.matches("^[a-zA-ZÀ-ÿ\\s]+$")) {
            throw new Exception("O nome deve conter apenas letras.");
        }
    }
}
