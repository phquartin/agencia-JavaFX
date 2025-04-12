package com.agencia.project.model.client;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ClientModel {

    private final Long id;
    private String name;
    private int age;
    private String email;
    private String document;
    private Nationality nationality;

}
