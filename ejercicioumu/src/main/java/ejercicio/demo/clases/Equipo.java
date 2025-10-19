package ejercicio.demo.clases;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre; //Nombre del equipo

    @ElementCollection
    private List<Integer> competiciones; //Competiciones en las que participa

    //Constructores POJO
    public Equipo( String nombre, List<Integer> competiciones) {
        this.nombre = nombre;
        this.competiciones = competiciones;
    }
    public Equipo() {
    	
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Integer> getCompeticiones() {
        return competiciones;
    }

    public void setCompeticiones(List<Integer> competiciones) {
        this.competiciones = competiciones;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", competiciones=" + competiciones +
                '}';
    }
}