package ejercicio.demo.clases;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Jornada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ElementCollection
    private List<Integer> equiposJuegan = new ArrayList<>(); //Equipos que juegan la jornada

    @ElementCollection
    private List<Integer> pistasEquiposJuegan = new ArrayList<>(); //Pistas en las que juega cada equipo que juega la jornada

    @ElementCollection
    private List<Integer> equiposNoJuegan = new ArrayList<>(); //Equipos que no juegan la jornada
    
    //Constructores POJO
    public Jornada() {
        this.equiposJuegan = new ArrayList<>();
        this.pistasEquiposJuegan = new ArrayList<>();
        this.equiposNoJuegan = new ArrayList<>();
    }


    public Jornada(List<Integer> equiposjuegan, List<Integer> pistasequiposjuegan, List<Integer> equiposnojuegan) {
        this.equiposJuegan = equiposjuegan;
        this.pistasEquiposJuegan = pistasequiposjuegan;
        this.equiposNoJuegan = equiposnojuegan;
    }

    // Getters y Setters
    public List<Integer> getEquiposJuegan() {
        return equiposJuegan;
    }

    public void setEquiposJuegan(List<Integer> equiposjuegan) {
        this.equiposJuegan = equiposjuegan;
    }

    public List<Integer> getPistasEquiposJuegan() {
        return pistasEquiposJuegan;
    }

    public void setPistasEquiposJuegan(List<Integer> pistasequiposjuegan) {
        this.pistasEquiposJuegan = pistasequiposjuegan;
    }

    public List<Integer> getEquiposNoJuegan() {
        return equiposNoJuegan;
    }

    public void setEquiposNoJuegan(List<Integer> equiposnojuegan) {
        this.equiposNoJuegan = equiposnojuegan;
    }

    public void addEquipoJuega(Integer equipoId, Integer pista) {
        this.equiposJuegan.add(equipoId);
        this.pistasEquiposJuegan.add(pista);
    }
    public void addEquipoNoJuega(Integer equipoId) {
        this.equiposNoJuegan.add(equipoId);

    }
    //Generar la primera jornada, si fuese necesario generar más jornadas sería necesario modificar la función
    public void generarPrimeraJornada(int numPistas, int numequipos, List<Integer> idequipos) {
        int contador = 0;
        int pista = 0;

        // Recorremos pistas mientras haya pistas libres y equipos por jugar
        while (pista < numPistas && contador < numequipos) {
            int restantes = numequipos - contador;

            if (restantes >= 4) {
                // Asignamos 4 equipos a la pista 
                addEquipoJuega(idequipos.get(contador), pista);
                addEquipoJuega(idequipos.get(contador + 1), pista);
                addEquipoJuega(idequipos.get(contador + 2), pista);
                addEquipoJuega(idequipos.get(contador + 3), pista);
                contador += 4;
            } else if (restantes >= 2) {
                // Asignamos 2 equipos a la pista
                addEquipoJuega(idequipos.get(contador), pista);
                addEquipoJuega(idequipos.get(contador + 1), pista);
                contador += 2;
            } else {
                break;
            }

            pista++;
        }

        // Cualquier equipo restante no juega
        while (contador < numequipos) {
            addEquipoNoJuega(idequipos.get(contador));
            contador++;
        }
    }

    @Override
    public String toString() {
        return "Jornada{" +
                "equiposjuegan=" + equiposJuegan +
                ", pistasequiposjuegan=" + pistasEquiposJuegan +
                ", equiposnojuegan=" + equiposNoJuegan +
                '}';
    }
}