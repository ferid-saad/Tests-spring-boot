package com.isett.exercice4mockmvc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeController.class)
class EmployeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeService service;

    @Test
    @DisplayName("GET /api/employes doit retourner 200 et une liste JSON")
    void listerTous_devraitRetournerListeEmployes() throws Exception {
        when(service.listerTous()).thenReturn(List.of(
                new Employe(1L, "Salah", 3500),
                new Employe(2L, "Eya", 2800)
        ));

        mockMvc.perform(get("/api/employes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nom").value("Salah"));
    }

    @Test
    @DisplayName("GET /api/employes/{id} inexistant doit retourner 404")
    void trouverParId_inexistant_devraitRetourner404() throws Exception {
        when(service.trouverParId(99L)).thenThrow(new EmployeNotFoundException("Introuvable"));

        mockMvc.perform(get("/api/employes/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST invalide doit retourner 400 et ne pas appeler le service")
    void creer_invalide_devraitRetourner400() throws Exception {
        mockMvc.perform(post("/api/employes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nom\": \"\", \"salaire\": -500}"))
                .andExpect(status().isBadRequest());

        verify(service, never()).creer(any());
    }
}
