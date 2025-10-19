package ejercicio.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ejercicio.demo.clases.Equipo;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Integer> { //No están implementadas las interfaces de los repositorios porque no hace falta con Spring Boot

}//Como extiende la interfaz de JpaRepository Spring Boot genera automáticamente una implementación que permite usar todos los métodos de JpaRepository y para este ejercicio tan básico no hace falta más 
