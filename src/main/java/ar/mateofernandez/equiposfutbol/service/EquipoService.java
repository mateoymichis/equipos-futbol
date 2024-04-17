package ar.mateofernandez.equiposfutbol.service;

import ar.mateofernandez.equiposfutbol.model.Equipo;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.Optional;

public interface EquipoService {
    void save(Equipo equipo);
    Optional<Equipo> find(Integer id);
    void delete (Integer id);
}
