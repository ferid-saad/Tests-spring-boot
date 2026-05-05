package com.isett.exercice2mockito;

public class Facture {
    private Client client;
    private double montant;

    public Facture(Client client, double montant) {
        this.client = client;
        this.montant = montant;
    }

    public Client getClient() { return client; }
    public double getMontant() { return montant; }
}
