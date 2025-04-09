package com.agencia.project.validation;

public class AgeValidator {
    public static void validate(String ageStr) throws Exception {
        if (!ageStr.matches("^\\d+$")) {
            throw new Exception("A idade deve conter apenas números.");
        }
        int age = Integer.parseInt(ageStr);
        if (age < 18 || age > 130) {
            throw new Exception("A idade deve estar entre 18 e 130.");
        }
    }
}
