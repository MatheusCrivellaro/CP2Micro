package com.example.demo.repository;

import com.example.demo.model.Ingrediente;
import com.example.demo.model.Massa;
import com.example.demo.model.Pastel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PastelRepository extends JpaRepository<Pastel, Long> {

    List<Pastel> findByNomeContainingIgnoreCaseAndAtivoTrue(String nome);
    List<Pastel> findAllByMassaAndAtivoTrue(Massa massa);
    List<Pastel> findAllByIngredientesAndAtivoTrue(List<Ingrediente> ingredientes);
    List<Pastel> findAllByAtivoTrue();
}
