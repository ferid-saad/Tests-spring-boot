package com.isett.exercice4mockmvc;

import java.util.List;

public interface EmployeService {
    List<Employe> listerTous();
    Employe trouverParId(Long id);
    Employe creer(EmployeDTO dto);
}
