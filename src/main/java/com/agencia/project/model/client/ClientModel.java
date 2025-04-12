package com.agencia.project.model.client;

import lombok.Data;

@Data
public class ClientModel {

    private final Long id;
    private String name;
    private int age;
    private String email;
    private String document;
    private Nationality nationality;

}
