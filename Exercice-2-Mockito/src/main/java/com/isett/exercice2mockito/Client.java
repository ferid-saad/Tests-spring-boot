package com.isett.exercice2mockito;

public class Client {
    private Long id;
    private String email;

    public Client(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public Long getId() { return id; }
    public String getEmail() { return email; }
}
