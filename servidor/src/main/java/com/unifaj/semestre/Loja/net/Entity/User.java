package com.unifaj.semestre.Loja.net.Entity;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String cpf;
    private String password;

    public User(){
    }

    public User(String firstName, String lastName, String email, String cpf, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.cpf = cpf;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}



