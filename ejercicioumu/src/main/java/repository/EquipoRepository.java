package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import clases.Equipo;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Integer> {

}