package com.sge.sistema_gestor_escolar.model;

import jakarta.persistence.*;
import lombok.*;

@Entity // Marca la clase como entidad JPA
@Table(name = "indicadores_rendimiento") // Mapea la entidad a la tabla correspondiente
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndicadorRendimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Clave primaria autoincremental
    private Integer idIndicadoresRendimiento;

    private Byte cantidadParciales; // Número de parciales

    private Byte cantidadSemestrales; // Número de semestrales

    @ManyToOne // Relación muchos a uno con Alumno
    @JoinColumn(name = "id_alumno") // Llave foránea a la tabla alumno
    private Alumno alumno;

}
