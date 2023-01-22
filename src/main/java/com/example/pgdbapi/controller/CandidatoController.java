package com.example.pgdbapi.controller;

import com.example.pgdbapi.dto.UsuarioDto;
import com.example.pgdbapi.model.Candidato;
import com.example.pgdbapi.service.CandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/candidato")
public class CandidatoController {
    @Autowired
    CandidatoService candidatoService;

    @GetMapping("/all")
    public ResponseEntity<List<Candidato>> getAll() throws Exception {
        try {
            List<Candidato> candidatos = candidatoService.getAll();
            return ResponseEntity.ok().body(candidatos);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Candidato> getCandidatoById(@PathVariable Long id) throws Exception {
        try {
            Optional<Candidato> u = candidatoService.getById(id);
            if (u.isPresent()) {
                return ResponseEntity.ok().body(u.get());
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Candidato> createCandidato(@RequestBody Candidato candidato, UriComponentsBuilder uriBuilder) throws Exception {
        try {
            Candidato cand = candidatoService.create(candidato);
            URI uri = uriBuilder.path("candidato/{id}").buildAndExpand(cand.getId()).toUri();
            return ResponseEntity.created(uri).body(cand);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/by-uf/{uf}")
    public ResponseEntity<List<Candidato>> getCandidatoByUf(@PathVariable String uf) throws Exception {
        try {
            List<Candidato> u = candidatoService.getByUf(uf);
            if (!u.isEmpty()) {
                return ResponseEntity.ok().body(u);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/by-cargo/{cargo}")
    public ResponseEntity<List<Candidato>> getCandidatoByCargo(@PathVariable String cargo) throws Exception {
        try {
            List<Candidato> u = candidatoService.getByCargo(cargo);
            if (!u.isEmpty()) {
                return ResponseEntity.ok().body(u);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
