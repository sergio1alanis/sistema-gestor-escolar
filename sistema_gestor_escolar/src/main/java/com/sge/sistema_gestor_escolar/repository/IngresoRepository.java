package com.sge.sistema_gestor_escolar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sge.sistema_gestor_escolar.model.Ingreso;

public interface IngresoRepository extends JpaRepository<Ingreso, Integer> {

}
