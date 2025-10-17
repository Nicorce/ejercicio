package clases;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Competicion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String deporte;
    private int numeroPistasPorJornada;

    @ElementCollection
    private List<LocalDate> fechasDeCelebracion;

    @OneToOne(cascade = CascadeType.ALL)
    private Jornada primeraJornada;

    // Constructor
    public Competicion(String nombre, String deporte, List<LocalDate> fechasDeCelebracion, int numeroPistasPorJornada, Jornada primeraJornada) {
        this.nombre = nombre;
        this.deporte = deporte;
        this.fechasDeCelebracion = fechasDeCelebracion;
        this.numeroPistasPorJornada = numeroPistasPorJornada;
        this.primeraJornada=primeraJornada;
    }
    public Competicion() {

    }

    // Getters y Setters
    
    public int getId() {
        return this.id;
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
    public Jornada getPrimeraJornada() {
        return this.primeraJornada;
    }

    public void setPrimeraJornada(Jornada primeraJornada) {
        this.primeraJornada = primeraJornada;
    }

    public String getDeporte() {
        return deporte;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

    public List<LocalDate> getFechasDeCelebracion() {
        return fechasDeCelebracion;
    }

    public void setFechasDeCelebracion(List<LocalDate> fechasDeCelebracion) {
        this.fechasDeCelebracion = fechasDeCelebracion;
    }

    public int getNumeroPistasPorJornada() {
        return numeroPistasPorJornada;
    }

    public void setNumeroPistasPorJornada(int numeroPistasPorJornada) {
        this.numeroPistasPorJornada = numeroPistasPorJornada;
    }

    @Override
    public String toString() {
        return "Competicion{" +
                "nombre='" + nombre + '\'' +
                ", deporte='" + deporte + '\'' +
                ", fechasDeCelebracion=" + fechasDeCelebracion +
                ", numeroPistasPorJornada=" + numeroPistasPorJornada +
                ", primeraJornada=" + primeraJornada +
                '}';
    }
}