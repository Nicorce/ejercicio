package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import clases.Competicion;
import clases.Equipo;
import clases.Jornada;
import service.CompeticionService;

@RestController
@RequestMapping("/api")
public class CompeticionController {

    @Autowired
    private CompeticionService service;

    // Registrar competición
    @PostMapping("/competiciones")
    public Competicion crearCompeticion(@RequestBody Competicion competicion) {
        return service.registrarCompeticion(competicion);
    }

    // Registrar equipo en una competición
    @PostMapping("/competiciones/{id}/equipos")
    public Equipo crearEquipo(@PathVariable int id, @RequestBody Equipo equipo) {
        return service.registrarEquipo(equipo, id);
    }

    // Consultar equipos por competición
    @GetMapping("/competiciones/{id}/equipos")
    public List<Equipo> getEquiposPorCompeticion(@PathVariable int id) {
        return service.consultarEquiposPorCompeticion(id);
    }

    // Generar primera jornada
    @PostMapping("/competiciones/{id}/jornada")
    public Jornada generarPrimeraJornada(@PathVariable int id) {
        return service.generarPrimeraJornada(id);
    }

    // Consultar primera jornada
    @GetMapping("/competiciones/{id}/jornada")
    public Jornada getPrimeraJornada(@PathVariable int id) {
        return service.consultarPrimeraJornada(id);
    }

    // Consultar equipos no asignados
    @GetMapping("/competiciones/{id}/equipos-no-asignados")
    public List<Integer> getEquiposNoAsignados(@PathVariable int id) {
        return service.consultarEquiposNoAsignados(id);
    }
}
