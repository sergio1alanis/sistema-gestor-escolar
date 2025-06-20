package com.sge.sistema_gestor_escolar.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "competencias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Competencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCompetencia;

    private Float promedio;

    private String acronimo;

    @ManyToOne
    @JoinColumn(name = "id_asignatura")
    private Asignatura asignatura;

    @ManyToOne
    @JoinColumn(name = "id_alumno")
    private Alumno alumno;
}
