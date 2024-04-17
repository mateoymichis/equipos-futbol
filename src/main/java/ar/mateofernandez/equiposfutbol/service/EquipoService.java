package ar.mateofernandez.equiposfutbol.service;

import ar.mateofernandez.equiposfutbol.model.Equipo;

import java.util.List;
import java.util.Optional;

public interface EquipoService {
    void save(Equipo equipo);
    Optional<Equipo> find(Integer id);
    List<Equipo> getAll();
    void delete (Integer id);
}
