package com.example.demo.repository;

import com.example.demo.model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {

    List<Ingrediente> findByNomeContainingIgnoreCaseAndAtivoTrue(String nome);
    Optional<Ingrediente> findByIdAndAtivoTrue(Long id);
    List<Ingrediente> findAllByAtivoTrue();

}
