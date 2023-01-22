package com.example.pgdbapi.repository;

import com.example.pgdbapi.model.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
    List<Candidato> findAllBySiglaUfEqualsIgnoreCase(String uf);

    List<Candidato> findAllByCargoEqualsIgnoreCase(String cargo);
}
