package com.sge.sistema_gestor_escolar.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "promedios_asignaturas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromedioAsignatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPromedioAsignatura;

    private Float promedio;

    @ManyToOne
    @JoinColumn(name = "id_asignatura")
    private Asignatura asignatura;
}
