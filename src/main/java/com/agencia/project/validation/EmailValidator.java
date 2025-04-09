package com.agencia.project.validation;

public class EmailValidator {
    public static void validate(String email) throws Exception {
        if (!email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new Exception("O e-mail fornecido é inválido.");
        }
    }
}
