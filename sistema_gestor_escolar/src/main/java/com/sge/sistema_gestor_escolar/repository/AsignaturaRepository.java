package com.sge.sistema_gestor_escolar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sge.sistema_gestor_escolar.model.Asignatura;

public interface AsignaturaRepository extends JpaRepository<Asignatura, Integer> {
	// Aquí puedes agregar métodos personalizados si es necesario

}
