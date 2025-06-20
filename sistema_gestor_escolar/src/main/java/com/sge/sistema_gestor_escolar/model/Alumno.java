package com.sge.sistema_gestor_escolar.model;

import jakarta.persistence.*;
import lombok.*;

@Entity // Clase mapeada como entidad JPA
@Table(name = "alumnos") // Tabla asociada en la base de datos
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alumno {
    @Id // Clave primaria
    private Integer idAlumno;

    private String nombre;

    @Column(name = "apellido_paterno")
    private String apellidoPaterno;

    @Column(name = "apellido_materno")
    private String apellidoMaterno;
}
