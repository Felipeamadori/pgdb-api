package com.example.pgdbapi.repository;

import com.example.pgdbapi.dto.SumVotos;
import com.example.pgdbapi.model.Votos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotosRepository extends JpaRepository<Votos, Long> {

    @Query(value = "SELECT c.id as id_cand, count(v.qtd_votos) as total " +
            " FROM candidato c " +
            " JOIN votos v on c.id = v.candidato_id " +
            " JOIN boletim b on b.id = v.boletim_id " +
            " WHERE b.valido = true AND UPPER(c.sigla_uf) = UPPER(:uf) " +
            " GROUP BY c.id ", nativeQuery = true)
    List<SumVotos> countVotosByUf(@Param("uf") String uf);

    @Query(value = "SELECT count(v.qtd_votos) as total " +
            " FROM candidato c " +
            " JOIN votos v on c.id = v.candidato_id " +
            " JOIN boletim b on b.id = v.boletim_id " +
            " WHERE b.valido = true AND c.id = :id ", nativeQuery = true)
    int totalVotosByCandidato(@Param("id") Long id);

    @Query(value = "SELECT c.id as id_cand, count(v.qtd_votos) as total " +
            " FROM candidato c " +
            " JOIN votos v on c.id = v.candidato_id " +
            " JOIN boletim b on b.id = v.boletim_id " +
            " WHERE b.valido = true AND c.sigla_uf is null " +
            " GROUP BY c.id ", nativeQuery = true)
    List<SumVotos> countVotosPresidente();
}
