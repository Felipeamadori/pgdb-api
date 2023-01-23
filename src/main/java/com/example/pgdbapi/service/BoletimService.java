package com.example.pgdbapi.service;

import com.example.pgdbapi.dao.BoletimDao;
import com.example.pgdbapi.model.Boletim;
import com.example.pgdbapi.model.Votos;
import com.example.pgdbapi.repository.BoletimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BoletimService {
    @Autowired
    private BoletimRepository boletimRepository;

    public List<Boletim> getAll() throws Exception {
        try {
            return boletimRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    @Transactional
    public Boletim saveBoletim(Boletim boletim) throws Exception {
        try {
            return boletimRepository.save(boletim);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Optional<Boletim> getById(Long id) throws Exception {
        try {
            return boletimRepository.findById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public String validarBoletim(Boletim boletim) throws Exception {
        try {
            boletimRepository.setValidoFalseZonaSecao(boletim.getZona(), boletim.getSecao());
            boletimRepository.setValidoTrueById(boletim.getId());
            return "OK";
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
