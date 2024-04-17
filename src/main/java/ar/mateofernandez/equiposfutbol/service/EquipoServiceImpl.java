package ar.mateofernandez.equiposfutbol.service;

import ar.mateofernandez.equiposfutbol.model.CrearEquipoDto;
import ar.mateofernandez.equiposfutbol.model.Equipo;
import ar.mateofernandez.equiposfutbol.repository.EquipoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipoServiceImpl implements EquipoService {
    private final EquipoRepository equipoRepository;

    public EquipoServiceImpl(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    @Override
    public Equipo save(CrearEquipoDto equipoDto) {
        Equipo equipo = new Equipo();
        equipo.setNombre(equipoDto.getNombre());
        equipo.setLiga(equipoDto.getLiga());
        equipo.setPais(equipoDto.getPais());

        return equipoRepository.save(equipo);
    }

    @Override
    public Optional<Equipo> find(Integer id) {
        return equipoRepository.findById(id);
    }

    @Override
    public List<Equipo> getAll() {
        return equipoRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        equipoRepository.deleteById(id);
    }
}
