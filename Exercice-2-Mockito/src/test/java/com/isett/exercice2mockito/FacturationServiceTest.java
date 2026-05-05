package com.isett.exercice2mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FacturationServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private FacturationService facturationService;

    private Client client;

    @BeforeEach
    void setUp() {
        client = new Client(1L, "contact@acme.com");
    }

    @Test
    @DisplayName("Cas nominal : facture générée et notification envoyée")
    void genererFacture_clientExistant_devraitRetournerFactureEtEnvoyerEmail() {
        // Stub
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        // Act
        Facture facture = facturationService.genererFacture(1L, 250.0);

        // Assert
        assertThat(facture).isNotNull();
        assertThat(facture.getMontant()).isEqualTo(250.0);

        // Verify interaction
        verify(notificationService, times(1))
                .envoyerEmail("contact@acme.com", "Votre facture : 250.0€");
    }

    @Test
    @DisplayName("Cas d'erreur : client introuvable → exception et pas de notification")
    void genererFacture_clientInexistant_devraitLancerExceptionEtNePasEnvoyerEmail() {
        // Stub
        when(clientRepository.findById(99L)).thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(ClientNotFoundException.class,
                () -> facturationService.genererFacture(99L, 100.0));

        // Verify interaction
        verify(notificationService, never()).envoyerEmail(anyString(), anyString());
    }
}
