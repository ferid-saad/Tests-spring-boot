package com.isett.exercice2mockito;

import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class FacturationService {
    private final ClientRepository clientRepository;
    private final NotificationService notificationService;

    public FacturationService(ClientRepository clientRepository,
                              NotificationService notificationService) {
        this.clientRepository = clientRepository;
        this.notificationService = notificationService;
    }

    public Facture genererFacture(Long clientId, double montant) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Client introuvable : " + clientId));

        Facture facture = new Facture(client, montant);
        notificationService.envoyerEmail(client.getEmail(), "Votre facture : " + montant + "€");
        return facture;
    }
}
