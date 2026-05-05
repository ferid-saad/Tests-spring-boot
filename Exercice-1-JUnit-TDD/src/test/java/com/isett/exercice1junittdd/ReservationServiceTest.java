package com.isett.exercice1junittdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReservationServiceTest {

    com.isett.reservation.ReservationService service;

    @BeforeEach
    void setUp() {
        service = new com.isett.reservation.ReservationService();
    }

    @Test
    @DisplayName("Séjour de 3 nuits sans remise → prix plein")
    void calculerPrixTotal_sejourCourt_devraitAppliquerPrixPlein() {
        double resultat = service.calculerPrixTotal(100, 3);
        assertThat(resultat).isEqualTo(300);
    }

    @Test
    @DisplayName("Séjour de 7 nuits → remise de 10%")
    void calculerPrixTotal_sejourLong_devraitAppliquerRemise() {
        double resultat = service.calculerPrixTotal(100, 7);
        assertThat(resultat).isEqualTo(630);
    }

    @Test
    @DisplayName("Prix par nuit négatif → exception")
    void calculerPrixTotal_prixNegatif_devraitLancerException() {
        assertThrows(IllegalArgumentException.class,
                () -> service.calculerPrixTotal(-50, 3));
    }

    @Test
    @DisplayName("Disponibilité valide → true")
    void verifierDisponibilite_datesValides_devraitRetournerTrue() {
        boolean dispo = service.verifierDisponibilite(
                LocalDate.of(2024, 5, 1),
                LocalDate.of(2024, 5, 5));
        assertThat(dispo).isTrue();
    }

    @Test
    @DisplayName("Disponibilité avec date null → exception")
    void verifierDisponibilite_dateNull_devraitLancerException() {
        assertThrows(IllegalArgumentException.class,
                () -> service.verifierDisponibilite(null, LocalDate.now()));
    }

    @Test
    @DisplayName("Code de confirmation → format HOTEL-XXXXXX")
    void genererCodeConfirmation_devraitRespecterFormat() {
        String code = service.genererCodeConfirmation();
        assertThat(code).startsWith("HOTEL-");
        assertThat(code).hasSize(12);
    }
}
