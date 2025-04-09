package com.agencia.project.model.client;

import java.util.Objects;

public class ClientModel {

    private Integer id;
    private final String name;
    private final int age;
    private final String email;
    private final String document;
    private final Nationality nationality;

    public ClientModel(Integer id, String name, int age, String email, String document, Nationality nationality) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.document = document;
        this.nationality = nationality;
    }

    public ClientModel(String name, int age, String email, String document, Nationality nationality) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.document = document;
        this.nationality = nationality;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ClientModel that = (ClientModel) o;
        return Objects.equals(id, that.id) && Objects.equals(document, that.document);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, document);
    }

    @Override
    public String toString() {
        return "ClientModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", document='" + document + '\'' +
                ", nationality=" + nationality +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getDocument() {
        return document;
    }

    public Nationality getNationality() {
        return nationality;
    }
}
