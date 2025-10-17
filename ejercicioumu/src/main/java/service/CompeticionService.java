package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import clases.Competicion;
import clases.Equipo;
import clases.Jornada;
import repository.CompeticionRepository;
import repository.EquipoRepository;
import repository.JornadaRepository;

@Service
public class CompeticionService {

    @Autowired
    private CompeticionRepository competicionRepo;

    @Autowired
    private EquipoRepository equipoRepo;

    @Autowired
    private JornadaRepository jornadaRepo;

    public Competicion registrarCompeticion(Competicion competicion) {
        return competicionRepo.save(competicion);
    }

    public Equipo registrarEquipo(Equipo equipo, int competicionId) {
        Competicion competicion = competicionRepo.findById(competicionId)
                .orElseThrow(() -> new RuntimeException("Competición no encontrada"));
        List<Integer> competiciones = equipo.getCompeticiones();
        if (competiciones == null) {
            competiciones = new ArrayList<>();
        }
        competiciones.add(competicion.getId());
        equipo.setCompeticiones(competiciones);
        return equipoRepo.save(equipo);
    }

    public List<Equipo> consultarEquiposPorCompeticion(int competicionId) {
        List<Equipo> todos = equipoRepo.findAll();
        return todos.stream()
                .filter(e -> e.getCompeticiones() != null && e.getCompeticiones().contains(competicionId))
                .collect(Collectors.toList());
    }

    public Jornada generarPrimeraJornada(int competicionId) {
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

    public Jornada consultarPrimeraJornada(int competicionId) {
        Competicion competicion = competicionRepo.findById(competicionId)
                .orElseThrow(() -> new RuntimeException("Competición no encontrada"));
        return competicion.getPrimeraJornada();
    }

    public List<Integer> consultarEquiposNoAsignados(int competicionId) {
        Jornada j = consultarPrimeraJornada(competicionId);
        return j != null ? j.getEquiposNoJuegan() : new ArrayList<>();
    }
}