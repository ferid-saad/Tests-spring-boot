package com.isett.reservation;

import java.time.LocalDate;
import java.util.Random;

public class ReservationService {

    // Calcule le prix total d'un séjour
    // Applique -10% si la durée est >= 7 nuits
    public double calculerPrixTotal(double prixParNuit, int nombreNuits) {
        if (prixParNuit < 0 || nombreNuits <= 0) {
            throw new IllegalArgumentException("Prix ou nombre de nuits invalide");
        }
        double total = prixParNuit * nombreNuits;
        if (nombreNuits >= 7) {
            total *= 0.9; // remise 10%
        }
        return total;
    }

    // Vérifie si une chambre est disponible
    // Lance IllegalArgumentException si les dates sont nulles
    public boolean verifierDisponibilite(LocalDate debut, LocalDate fin) {
        if (debut == null || fin == null) {
            throw new IllegalArgumentException("Dates invalides");
        }
        return !debut.isAfter(fin);
    }

    // Retourne un code de confirmation au format "HOTEL-XXXXXX" (6 chiffres)
    public String genererCodeConfirmation() {
        int code = new Random().nextInt(999999);
        return String.format("HOTEL-%06d", code);
    }
}
