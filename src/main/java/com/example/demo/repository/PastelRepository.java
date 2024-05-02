package com.grupoa.pastelaria.repository;

import com.grupoa.pastelaria.domain.model.Ingrediente;
import com.grupoa.pastelaria.domain.model.Massa;
import com.grupoa.pastelaria.domain.model.Pastel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PastelRepository extends JpaRepository<Pastel, Long> {

    List<Pastel> findByNomeContainingIgnoreCaseAndAtivoTrue(String nome);
    List<Pastel> findAllByMassaAndAtivoTrue(Massa massa);
    List<Pastel> findAllByIngredientesAndAtivoTrue(List<Ingrediente> ingredientes);
    List<Pastel> findAllByAtivoTrue();
}
