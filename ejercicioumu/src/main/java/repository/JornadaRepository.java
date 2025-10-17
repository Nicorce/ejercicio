package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import clases.Jornada;

@Repository
public interface JornadaRepository extends JpaRepository<Jornada, Integer> {

}