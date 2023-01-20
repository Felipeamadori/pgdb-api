package com.example.pgdbapi.repository;

import com.example.pgdbapi.model.Boletim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoletimRepository extends JpaRepository<Boletim, Long> {
}
