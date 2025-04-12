package com.agencia.project.model.vpackage;

import lombok.Data;

@Data
public class VacationPackageModel {

    private final Long id;
    private String name;
    private int days;
    private double price;
    private VacationPackageType type;

}
