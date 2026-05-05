package com.isett.exercice3datajpatest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class EmployeRepositoryTest {

    @Autowired
    private EmployeRepository repository;

    @BeforeEach
    void setUp() {
        repository.save(new Employe("Salah", "Informatique", 3500));
        repository.save(new Employe("Eya", "RH", 2800));
        repository.save(new Employe("Amar", "Informatique", 4200));
    }

    @Test
    @DisplayName("findByDepartement doit retourner Salah et Amar")
    void findByDepartement_devraitRetournerDeuxEmployes() {
        List<Employe> result = repository.findByDepartement("Informatique");
        assertThat(result).hasSize(2)
                .extracting(Employe::getNom)
                .containsExactlyInAnyOrder("Salah", "Amar");
    }

    @Test
    @DisplayName("findBySalaireSuperieurA(3000) doit retourner Salah et Amar")
    void findBySalaireSuperieurA_devraitRetournerEmployesAvecSalaireSup3000() {
        List<Employe> result = repository.findBySalaireSuperieurA(3000.0);
        assertThat(result).extracting(Employe::getNom)
                .containsExactlyInAnyOrder("Salah", "Amar");
    }

    @Test
    @DisplayName("findByDepartement inexistant doit retourner liste vide")
    void findByDepartement_inexistant_devraitRetournerVide() {
        List<Employe> result = repository.findByDepartement("Comptabilité");
        assertThat(result).isEmpty();
    }
}
