package com.sge.sistema_gestor_escolar.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ingresos") // Mapea la entidad a la tabla correspondiente
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingreso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Clave primaria autoincremental
	private Integer idIngresos;

	private Float costoTotalParciales;

	private Float costoTotalSemestrales;


	@ManyToOne // Relación muchos a uno con Alumno
	@JoinColumn(name = "id_alumno") // Llave foránea a la tabla alumno
	private Alumno alumno;

}
