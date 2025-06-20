package com.sge.sistema_gestor_escolar.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "asignaturas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Asignatura {
    @Id
    private Integer idAsignatura;

    private String descripcion;
}