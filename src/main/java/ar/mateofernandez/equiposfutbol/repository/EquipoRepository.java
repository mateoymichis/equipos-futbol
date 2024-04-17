package ar.mateofernandez.equiposfutbol.repository;

import ar.mateofernandez.equiposfutbol.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Integer> {
}
