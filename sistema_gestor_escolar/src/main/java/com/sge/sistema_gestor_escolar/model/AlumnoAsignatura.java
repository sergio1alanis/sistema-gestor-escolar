package com.sge.sistema_gestor_escolar.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "alumnos_asignaturas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoAsignatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAlumnosAsignaturas;

    private Byte unidad;

    private Byte calificacion;

    @ManyToOne
    @JoinColumn(name = "id_alumno")
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "id_asignatura")
    private Asignatura asignatura;
}
