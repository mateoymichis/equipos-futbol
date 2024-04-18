package ar.mateofernandez.equiposfutbol.repository;

import ar.mateofernandez.equiposfutbol.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Integer> {
    List<Equipo> findByNombreContaining(String nombre);
}
