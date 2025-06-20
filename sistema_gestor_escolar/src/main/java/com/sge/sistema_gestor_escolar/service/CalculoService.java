package com.sge.sistema_gestor_escolar.service;

import java.util.List;

public interface CalculoService {
	void calcularPromediosAlumnosYAsignaturas();
	List<String> ejecutarCalculoCompleto();


}
