package com.sge.sistema_gestor_escolar.controller;

import com.sge.sistema_gestor_escolar.service.CalculoService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/calculos")
@RequiredArgsConstructor
public class CalculoController {

    private final CalculoService calculoService;

    @PostMapping("/promedios")
    public String calcularPromedios() {
        calculoService.calcularPromediosAlumnosYAsignaturas();
        return "Cálculo de promedios ejecutado con éxito.";
    }

    @PostMapping("/todo")
    public List<String> ejecutarTodo() {
        return calculoService.ejecutarCalculoCompleto();
    }

    @GetMapping("/prueba")
    public String prueba() {
        return  "Verficarndo el funcionamiento de este metodo GET";
    }

}

