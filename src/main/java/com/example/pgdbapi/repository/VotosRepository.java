package com.example.pgdbapi.repository;

import com.example.pgdbapi.model.Votos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotosRepository extends JpaRepository<Votos, Long> {
}
