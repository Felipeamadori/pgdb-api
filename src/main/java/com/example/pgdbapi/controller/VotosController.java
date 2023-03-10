package com.example.pgdbapi.controller;

import com.example.pgdbapi.dto.SumVotos;
import com.example.pgdbapi.model.Candidato;
import com.example.pgdbapi.model.Votos;
import com.example.pgdbapi.service.VotosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/votos")
public class VotosController {

    @Autowired
    private VotosService votosService;

    @GetMapping("/all")
    public ResponseEntity<List<Votos>> getAll() throws Exception {
        try {
            List<Votos> votos = votosService.getAll();
            return ResponseEntity.ok().body(votos);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Votos> getVotosById(@PathVariable Long id) throws Exception {
        try {
            Optional<Votos> u = votosService.getById(id);
            if (u.isPresent()) {
                return ResponseEntity.ok().body(u.get());
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/total-estado/{uf}")
    public ResponseEntity<List<SumVotos>> getTotalVotosByUf(@PathVariable String uf) throws Exception {
        try {
            List<SumVotos> u = votosService.totalVotosByUf(uf);
            if (!u.isEmpty()) {
                return ResponseEntity.ok().body(u);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/total-candidato/{id}")
    public ResponseEntity<Integer> getTotalVotosByUf(@PathVariable Long id) throws Exception {
        try {
            int u = votosService.totalCandidato(id);
            return ResponseEntity.ok().body(u);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/total-presidente")
    public ResponseEntity<List<SumVotos>> getTotalVotosPresidente() throws Exception {
        try {
            List<SumVotos> u = votosService.totalPresidente();
            return ResponseEntity.ok().body(u);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
