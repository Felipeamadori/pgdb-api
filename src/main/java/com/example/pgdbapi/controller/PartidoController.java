package com.example.pgdbapi.controller;

import com.example.pgdbapi.model.Candidato;
import com.example.pgdbapi.model.Partido;
import com.example.pgdbapi.service.PartidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/partido")
public class PartidoController {

    private PartidoService partidoService;

    public PartidoController(PartidoService partidoService) {
        this.partidoService = partidoService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Partido>> getAll() throws Exception {
        try {
            List<Partido> partidos = partidoService.getAll();
            return ResponseEntity.ok().body(partidos);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Partido> getPartidoById(@PathVariable Long id) throws Exception {
        try {
            Optional<Partido> u = partidoService.getById(id);
            if (u.isPresent()) {
                return ResponseEntity.ok().body(u.get());
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Partido> createPartido(@RequestBody Partido partido, UriComponentsBuilder uriBuilder) throws Exception {
        try {
            Partido part = partidoService.create(partido);
            URI uri = uriBuilder.path("partido/{id}").buildAndExpand(part.getId()).toUri();
            return ResponseEntity.created(uri).body(part);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/by-sigla/{sigla}")
    public ResponseEntity<List<Partido>> getPartidoBySigla(@PathVariable String sigla) throws Exception {
        try {
            List<Partido> u = partidoService.getBySigla(sigla);
            if (!u.isEmpty()) {
                return ResponseEntity.ok().body(u);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
