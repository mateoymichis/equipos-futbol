package ar.mateofernandez.equiposfutbol.service;

import ar.mateofernandez.equiposfutbol.exception.EquipoException;
import ar.mateofernandez.equiposfutbol.model.dto.CrearEquipoDto;
import ar.mateofernandez.equiposfutbol.model.Equipo;

import java.util.List;
import java.util.Optional;

public interface EquipoService {
    Equipo save(CrearEquipoDto equipo);
    Optional<Equipo> find(Integer id) throws EquipoException;
    List<Equipo> getAll();
    List<Equipo> findByName(String nombre);
    void delete (Integer id) throws EquipoException;
    boolean exists(Integer id);
    Optional<Equipo> edit(Integer id, CrearEquipoDto equipo) throws EquipoException;
}
