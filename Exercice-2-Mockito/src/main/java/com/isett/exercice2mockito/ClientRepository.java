package com.isett.exercice2mockito;

import java.util.Optional;

public interface ClientRepository {
    Optional<Client> findById(Long id);
}
