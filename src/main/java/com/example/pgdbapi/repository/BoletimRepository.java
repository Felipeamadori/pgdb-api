package com.example.pgdbapi.repository;

import com.example.pgdbapi.model.Boletim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoletimRepository extends JpaRepository<Boletim, Long> {

    @Modifying
    @Query(nativeQuery = true, value = " UPDATE boletim SET valido = false " +
            " WHERE zona = :zona AND secao = :secao ")
    void setValidoFalseZonaSecao(@Param("zona") int zona, @Param("secao") int secao);

    @Modifying
    @Query(nativeQuery = true, value = " UPDATE boletim SET valido = true " +
            " WHERE id = :id ")
    void setValidoTrueById(@Param("id") Long id);
}
