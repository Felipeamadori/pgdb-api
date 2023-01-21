package com.example.pgdbapi.service;

import com.example.pgdbapi.model.Candidato;
import com.example.pgdbapi.model.Votos;
import com.example.pgdbapi.repository.VotosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VotosService {

    @Autowired
    private VotosRepository votosRepository;

    public List<Votos> getAll() throws Exception {
        try {
            return votosRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Optional<Votos> getById(Long id) throws Exception {
        try {
            return votosRepository.findById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
