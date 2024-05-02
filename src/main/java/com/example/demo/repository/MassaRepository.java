package com.grupoa.pastelaria.repository;

import com.grupoa.pastelaria.domain.model.Massa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MassaRepository extends JpaRepository<Massa, Long> {

    Optional<Massa> findByNomeAndAtivoTrue(String nome);
    List<Massa> findByNomeContainingIgnoreCaseAndAtivoTrue(String nome);
    List<Massa> findAllByAtivoTrue();

}
