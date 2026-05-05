package com.isett.exercice4mockmvc;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class EmployeDTO {
    @NotBlank
    private String nom;

    @Positive
    private double salaire;

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public double getSalaire() { return salaire; }
    public void setSalaire(double salaire) { this.salaire = salaire; }
}
