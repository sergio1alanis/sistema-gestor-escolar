package com.sge.sistema_gestor_escolar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sge.sistema_gestor_escolar.model.AlumnoAsignatura;

// Repositorio JPA para AlumnoAsignatura
public interface AlumnoAsignaturaRepository extends JpaRepository<AlumnoAsignatura, Integer> {
    // Busca por id de alumno
    List<AlumnoAsignatura> findByAlumnoIdAlumno(Integer idAlumno);
    // Busca por id de asignatura
    List<AlumnoAsignatura> findByAsignaturaIdAsignatura(Integer idAsignatura);
    // Busca por id de alumno y asignatura
    List<AlumnoAsignatura> findByAlumnoIdAlumnoAndAsignaturaIdAsignatura(Integer idAlumno, Integer idAsignatura);
}
