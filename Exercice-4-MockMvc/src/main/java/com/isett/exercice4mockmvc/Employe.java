package com.isett.exercice4mockmvc;

public class Employe {
    private Long id;
    private String nom;
    private double salaire;

    public Employe(Long id, String nom, double salaire) {
        this.id = id;
        this.nom = nom;
        this.salaire = salaire;
    }

    public Long getId() { return id; }
    public String getNom() { return nom; }
    public double getSalaire() { return salaire; }
}
