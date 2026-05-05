package com.isett.exercice3datajpatest;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Employe {
    @Id @GeneratedValue
    private Long id;
    private String nom;
    private String departement;
    private double salaire;

    public Employe() {}

    public Employe(String nom, String departement, double salaire) {
        this.nom = nom;
        this.departement = departement;
        this.salaire = salaire;
    }

    public Long getId() { return id; }
    public String getNom() { return nom; }
    public String getDepartement() { return departement; }
    public double getSalaire() { return salaire; }
}
