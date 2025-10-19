package ejercicio.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import ejercicio.demo.clases.Competicion;
import ejercicio.demo.clases.Equipo;
import ejercicio.demo.clases.Jornada;
import ejercicio.demo.repository.CompeticionRepository;
import ejercicio.demo.repository.EquipoRepository;
import ejercicio.demo.repository.JornadaRepository;

@Service
public class CompeticionService {

    @Autowired
    private CompeticionRepository competicionRepo;

    @Autowired
    private EquipoRepository equipoRepo;

    @Autowired
    private JornadaRepository jornadaRepo;

    public Competicion registrarCompeticion(Competicion competicion) { //Función para registrar una competición
        return competicionRepo.save(competicion);
    }

    public Equipo registrarEquipo(Equipo equipo, int competicionId) { //Función para registrar un equipo
        Competicion competicion = competicionRepo.findById(competicionId)
                .orElseThrow(() -> new RuntimeException("Competición no encontrada"));

        List<Integer> competiciones = equipo.getCompeticiones();
        if (competiciones == null) {
            competiciones = new ArrayList<>();
        }

        //Comprobar si ya está inscrito
        if (competiciones.contains(competicion.getId())) {
            throw new RuntimeException("El equipo ya está inscrito en esta competición");
        }

        //Si no está, se añade
        competiciones.add(competicion.getId());
        equipo.setCompeticiones(competiciones);

        return equipoRepo.save(equipo);
    }

    public List<Equipo> consultarEquiposPorCompeticion(int competicionId) { //Función para consultar equipos de una competición
        List<Equipo> todos = equipoRepo.findAll();
        return todos.stream()
                .filter(e -> e.getCompeticiones() != null && e.getCompeticiones().contains(competicionId))
                .collect(Collectors.toList());
    }

    public Jornada generarPrimeraJornada(int competicionId) { //Función para generar la primera jornada de una competición
        Competicion competicion = competicionRepo.findById(competicionId)
                .orElseThrow(() -> new RuntimeException("Competición no encontrada"));

        List<Equipo> equipos = consultarEquiposPorCompeticion(competicionId);
        List<Integer> idsEquipos = equipos.stream().map(Equipo::getId).collect(Collectors.toList());

        Jornada j = new Jornada();
        j.generarPrimeraJornada(competicion.getNumeroPistasPorJornada(), idsEquipos.size(), idsEquipos);
        competicion.setPrimeraJornada(j);
        jornadaRepo.save(j);
        competicionRepo.save(competicion);
        return j;
    }

    public Jornada consultarPrimeraJornada(int competicionId) { //Función para consultar la primera jornada de una competición
        Competicion competicion = competicionRepo.findById(competicionId)
                .orElseThrow(() -> new RuntimeException("Competición no encontrada"));
        return competicion.getPrimeraJornada();
    }

    public List<Integer> consultarEquiposNoAsignados(int competicionId) { //Función para consultar los equipos no asignados a la primera jornada
        Jornada j = consultarPrimeraJornada(competicionId); //llama a consultar primera jornada
        return j != null ? j.getEquiposNoJuegan() : new ArrayList<>();
    }
}