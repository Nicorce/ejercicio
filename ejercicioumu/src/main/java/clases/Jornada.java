package clases;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Jornada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ElementCollection
    private List<Integer> equiposJuegan = new ArrayList<>();

    @ElementCollection
    private List<Integer> pistasEquiposJuegan = new ArrayList<>();

    @ElementCollection
    private List<Integer> equiposNoJuegan = new ArrayList<>();
    

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
    public void generarPrimeraJornada(int numPistas, int numequipos,List<Integer> idequipos ) {
    	int contador=0;
    	int pista=0;
    	for (int i=0;i<=numPistas-1;i++) {
    		if((contador+2)<=numequipos-1) {
    	        addEquipoJuega(idequipos.get(contador), pista);
    	        addEquipoJuega(idequipos.get(contador + 1), pista);
        		contador=contador+2;
        		pista++;
    		}else {
    			if((contador+1)<=numequipos) {
    		        addEquipoNoJuega(idequipos.get(contador));
            		contador++;
        		}
    		}

    	}
    	if(contador<numequipos-1) {
    		for(int i=contador;i<=numequipos-1;i++) {
    	        addEquipoNoJuega(idequipos.get(contador));
        		contador++;
    		}
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