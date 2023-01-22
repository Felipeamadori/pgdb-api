package com.example.pgdbapi.repository;


import com.example.pgdbapi.model.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Long> {

    List<Partido> findAllBySiglaEqualsIgnoreCase(String sigla);
}
