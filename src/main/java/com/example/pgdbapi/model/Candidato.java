package com.example.pgdbapi.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "candidato")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Candidato implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String siglaUf;

    private String nome;

    private String cargo;

    private int numCand;

    @ManyToOne
    @JoinColumn(name="partido_id", nullable=false)
    private Partido partido;

}
