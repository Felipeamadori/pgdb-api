package com.example.pgdbapi.service;

import com.example.pgdbapi.model.Partido;
import com.example.pgdbapi.repository.PartidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartidoService {

    private PartidoRepository partidoRepository;

    public PartidoService(PartidoRepository partidoRepository) {
        this.partidoRepository = partidoRepository;
    }

    public List<Partido> getAll() throws Exception {
        try {
            return partidoRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Optional<Partido> getById(Long id) throws Exception {
        try {
            return partidoRepository.findById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Partido create(Partido partido) throws Exception {
        try {
            return partidoRepository.save(partido);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Partido> getBySigla(String sigla) throws Exception {
        try {
            return partidoRepository.findAllBySiglaEqualsIgnoreCase(sigla);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
