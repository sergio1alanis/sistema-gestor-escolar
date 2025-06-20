package com.sge.sistema_gestor_escolar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sge.sistema_gestor_escolar.model.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {


}
