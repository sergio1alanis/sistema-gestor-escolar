package com.sge.sistema_gestor_escolar.service;

import com.sge.sistema_gestor_escolar.model.Alumno;
import com.sge.sistema_gestor_escolar.model.Asignatura;
import com.sge.sistema_gestor_escolar.model.Competencia;
import com.sge.sistema_gestor_escolar.model.IndicadorRendimiento;
import com.sge.sistema_gestor_escolar.model.Ingreso;
import com.sge.sistema_gestor_escolar.model.PromedioAsignatura;
import com.sge.sistema_gestor_escolar.model.AlumnoAsignatura;
import com.sge.sistema_gestor_escolar.repository.AlumnoRepository;
import com.sge.sistema_gestor_escolar.repository.AsignaturaRepository;
import com.sge.sistema_gestor_escolar.repository.CompetenciaRepository;
import com.sge.sistema_gestor_escolar.repository.IndicadorRendimientoRepository;
import com.sge.sistema_gestor_escolar.repository.IngresoRepository;
import com.sge.sistema_gestor_escolar.repository.PromedioAsignaturaRepository;
import com.sge.sistema_gestor_escolar.repository.AlumnoAsignaturaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service // Marca la clase como un servicio de Spring
@RequiredArgsConstructor // Inyección de dependencias por constructor
public class CalculoServiceImpl implements CalculoService {
    private final CompetenciaRepository competenciaRepository;
    private final PromedioAsignaturaRepository promedioAsignaturaRepository;
    private final AlumnoRepository alumnoRepository;
    private final AsignaturaRepository asignaturaRepository;
    private final AlumnoAsignaturaRepository alumnoAsignaturaRepository;
    private final IndicadorRendimientoRepository indicadorRendimientoRepository;
    private final IngresoRepository ingresoRepository;

    // Removed duplicate ejecutarCalculoCompleto() with void return type

    @Override
    public void calcularPromediosAlumnosYAsignaturas() {
        List<Alumno> alumnos = alumnoRepository.findAll();
        List<Asignatura> asignaturas = asignaturaRepository.findAll();

        // Calcula y guarda el promedio de cada alumno por asignatura en Competencia
        for (Alumno alumno : alumnos) {
            for (Asignatura asignatura : asignaturas) {
                List<AlumnoAsignatura> calificaciones = alumnoAsignaturaRepository
                        .findByAlumnoIdAlumnoAndAsignaturaIdAsignatura(
                                alumno.getIdAlumno(), asignatura.getIdAsignatura());

                if (calificaciones.size() == 3) { // Solo si hay 3 calificaciones
                    double suma = calificaciones.stream()
                            .mapToInt(a -> a.getCalificacion())
                            .sum();
                    double promedio =  BigDecimal.valueOf(suma / 3.0)
                            .setScale(1, RoundingMode.HALF_UP)
                            .doubleValue();

                    // Determina el acrónimo según el promedio
                    String acronimo;
                    if (promedio >= 90)
                        acronimo = "AD";
                    else if (promedio >= 80)
                        acronimo = "SA";
                    else if (promedio >= 70)
                        acronimo = "EP";
                    else
                        acronimo = "NA";

                    // Guarda el resultado en la tabla Competencia
                    Competencia comp = new Competencia();
                    comp.setPromedio((float) promedio);
                    comp.setAcronimo(acronimo);
                    comp.setAlumno(alumno);
                    comp.setAsignatura(asignatura);
                    competenciaRepository.save(comp);
                }
            }
        }

        // Calcula y guarda el promedio general por asignatura en PromedioAsignatura
        for (Asignatura asignatura : asignaturas) {
            double total = 0;
            int alumnosContados = 0;

            for (Alumno alumno : alumnos) {
                List<AlumnoAsignatura> lista = alumnoAsignaturaRepository
                        .findByAlumnoIdAlumnoAndAsignaturaIdAsignatura(alumno.getIdAlumno(),
                                asignatura.getIdAsignatura());

                if (lista.size() == 3) {
                    double suma = lista.stream().mapToInt(a -> a.getCalificacion()).sum();
                    total += (suma / 3.0);
                    alumnosContados++;
                }
            }

            if (alumnosContados > 0) {
                double promedioAsignatura = total / alumnosContados;
                promedioAsignatura = BigDecimal.valueOf(promedioAsignatura).setScale(1, RoundingMode.HALF_UP)
                        .doubleValue();

                // Guarda el promedio general de la asignatura
                PromedioAsignatura pa = new PromedioAsignatura();
                pa.setAsignatura(asignatura);
                pa.setPromedio((float) promedioAsignatura);
                promedioAsignaturaRepository.save(pa);
            }
        }

        // Calcula y guarda el indicador de rendimiento para cada alumno
        for(Alumno alumno: alumnos) {
            int totalSemestrales =0;
            int totalParciales = 0;

            for(Asignatura asignatura: asignaturas) {
                List<AlumnoAsignatura> calificaciones = alumnoAsignaturaRepository
                        .findByAlumnoIdAlumnoAndAsignaturaIdAsignatura(alumno.getIdAlumno(), asignatura.getIdAsignatura());

                if(calificaciones.size() == 3) {
                    int suma = calificaciones.stream()
                            .mapToInt(AlumnoAsignatura::getCalificacion)
                            .sum();
                    double promedio = suma / 3.0;
                    boolean algunaReprobada = calificaciones.stream()
                            .anyMatch(a -> a.getCalificacion() < 80);
                    if(promedio <80){
                        totalSemestrales++;
                    } else if(algunaReprobada) {
                        long parciales = calificaciones.stream()
                                .filter(a -> a.getCalificacion() < 80)
                                .count();
                        totalParciales += parciales;
                    }
                }
            }
            // Guarda el indicador de rendimiento del alumno
            IndicadorRendimiento ir= new IndicadorRendimiento();
            ir.setAlumno(alumno);
            ir.setCantidadSemestrales((byte) totalSemestrales);
            ir.setCantidadParciales((byte) totalParciales);
            indicadorRendimientoRepository.save(ir);

        }

		// Calcular ingresos por alumno
		for (Alumno alumno : alumnos) {
			IndicadorRendimiento ir = indicadorRendimientoRepository
				.findAll()
				.stream()
				.filter(x -> x.getAlumno().getIdAlumno().equals(alumno.getIdAlumno()))
				.findFirst()
				.orElse(null);

			if (ir != null) {
				float totalParciales = ir.getCantidadParciales() * 100.0f;
				float totalSemestrales = ir.getCantidadSemestrales() * 350.0f;

				Ingreso ingreso = new Ingreso();
				ingreso.setAlumno(alumno);
				ingreso.setCostoTotalParciales(totalParciales);
				ingreso.setCostoTotalSemestrales(totalSemestrales);
				ingresoRepository.save(ingreso);
			}
		}


    }

			@Override
		public List<String> ejecutarCalculoCompleto() {
			calcularPromediosAlumnosYAsignaturas();

			List<String> resumen = new ArrayList<>();
			List<Alumno> alumnos = alumnoRepository.findAll();
			List<Ingreso> ingresos = ingresoRepository.findAll();

			for (Alumno alumno : alumnos) {
				Ingreso ingreso = ingresos.stream()
						.filter(i -> i.getAlumno().getIdAlumno().equals(alumno.getIdAlumno()))
						.findFirst().orElse(null);

				if (ingreso != null) {
					resumen.add(
							"Alumno: " + alumno.getNombre() + " " + alumno.getApellidoPaterno()
									+ " | Parciales: $" + ingreso.getCostoTotalParciales()
									+ " | Semestrales: $" + ingreso.getCostoTotalSemestrales()
									+ " | Total: $" + (ingreso.getCostoTotalParciales() + ingreso.getCostoTotalSemestrales())
					);
				}
			}

			return resumen;
		}

}
