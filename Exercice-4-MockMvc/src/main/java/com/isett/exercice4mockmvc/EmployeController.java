package com.isett.exercice4mockmvc;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/employes")
public class EmployeController {

    private final EmployeService service;

    public EmployeController(EmployeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Employe> listerTous() {
        return service.listerTous();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employe> trouverParId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.trouverParId(id));
        } catch (EmployeNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Employe> creer(@Valid @RequestBody EmployeDTO dto) {
        Employe employe = service.creer(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(employe);
    }
}
