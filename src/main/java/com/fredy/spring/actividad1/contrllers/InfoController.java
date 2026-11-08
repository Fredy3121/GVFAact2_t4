package com.fredy.spring.actividad1.contrllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/info")
public class InfoController {

    // Endpoint a: Identificador del proyecto y estudiante
    @GetMapping("/proyecto")
    public Map<String, String> infoProyecto() {
        Map<String, String> datos = new HashMap<>();
        datos.put("proyecto", "actividad1_t4");
        datos.put("estudiante", "Fredy");
        datos.put("estado", "Desarrollanding");
        return datos;
    }

    // Endpoint b: Lista simulada de materias
    @GetMapping("/materias")
    public List<String> listadoMaterias() {
        return List.of(
            "Administración de Bases de Datos",
            "Redes de Computadoras",
            "Taller de Sistemas Operativos",
            "Ingeniería de Software"
        );
    }
}