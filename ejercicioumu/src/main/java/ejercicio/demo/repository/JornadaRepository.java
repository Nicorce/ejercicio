package ejercicio.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ejercicio.demo.clases.Jornada;

@Repository
public interface JornadaRepository extends JpaRepository<Jornada, Integer> {

}