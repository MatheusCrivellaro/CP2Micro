package com.example.demo.repository;

import com.example.demo.model.Massa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MassaRepository extends JpaRepository<Massa, Long> {

    Optional<Massa> findByNomeAndAtivoTrue(String nome);
    List<Massa> findByNomeContainingIgnoreCaseAndAtivoTrue(String nome);
    List<Massa> findAllByAtivoTrue();

}
