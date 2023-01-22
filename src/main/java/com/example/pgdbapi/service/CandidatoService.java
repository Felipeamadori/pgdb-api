package com.example.pgdbapi.service;

import com.example.pgdbapi.model.Candidato;
import com.example.pgdbapi.repository.CandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidatoService {
    @Autowired
    CandidatoRepository candidatoRepository;

    public List<Candidato> getAll() throws Exception {
        try {
            return candidatoRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Optional<Candidato> getById(Long id) throws Exception {
        try {
            return candidatoRepository.findById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Candidato create(Candidato candidato) throws Exception {
        try {
            return candidatoRepository.save(candidato);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Candidato> getByUf(String uf) throws Exception {
        try {
            return candidatoRepository.findAllBySiglaUfEqualsIgnoreCase(uf);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Candidato> getByCargo(String cargo) throws Exception {
        try {
            return candidatoRepository.findAllByCargoEqualsIgnoreCase(cargo);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
