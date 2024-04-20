package ar.mateofernandez.equiposfutbol.service;

import ar.mateofernandez.equiposfutbol.exception.EquipoException;
import ar.mateofernandez.equiposfutbol.model.Equipo;
import ar.mateofernandez.equiposfutbol.model.dto.CrearEquipoDto;
import ar.mateofernandez.equiposfutbol.repository.EquipoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EquipoServiceTest {
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Mock
    EquipoRepository equipoRepository;

    @InjectMocks
    EquipoServiceImpl equipoService;

    @Test
    void crear_equipo_ok() {
        CrearEquipoDto equipoDto = getEquipoDto();
        Equipo equipoGuardado = getEquipo();

        when(equipoRepository.save(any(Equipo.class))).thenReturn(equipoGuardado);

        Equipo equipoResultado = equipoService.save(equipoDto);

        assertNotNull(equipoResultado);
        assertEquals(equipoDto.getNombre(), equipoResultado.getNombre());
        assertEquals(equipoDto.getLiga(), equipoResultado.getLiga());
        assertEquals(equipoDto.getPais(), equipoResultado.getPais());
        assertEquals(1, equipoResultado.getId());
    }

    @Test
    void ver_equipo_ok() throws EquipoException {
        Integer id = 1;
        Equipo equipo = getEquipo();

        when(equipoRepository.existsById(id)).thenReturn(true);
        when(equipoRepository.findById(id)).thenReturn(Optional.of(equipo));

        Optional<Equipo> equipoResultado = equipoService.find(id);


        assertTrue(equipoResultado.isPresent());
        assertEquals(id, equipoResultado.get().getId());
        assertEquals("Equipo Ejemplo", equipoResultado.get().getNombre());
        assertEquals("La Liga", equipoResultado.get().getLiga());
        assertEquals("Argentina", equipoResultado.get().getPais());
    }

    @Test
    void buscar_equipo_inexistente() {
        Integer id = 1;

        when(equipoRepository.existsById(id)).thenReturn(false);

        assertThrows(EquipoException.class, () -> {
            equipoService.find(id);
        });
    }

    @Test
    void ver_todos_equipos_ok() {
        Equipo equipo1 = getEquipo();

        Equipo equipo2 = new Equipo();
        equipo2.setId(2);
        equipo2.setNombre("Boca Juniors");
        equipo2.setLiga("Primera A");
        equipo2.setPais("Argentina");

        List<Equipo> equipos = Arrays.asList(equipo1, equipo2);

        when(equipoRepository.findAll()).thenReturn(equipos);

        List<Equipo> equiposResultado = equipoService.getAll();

        assertNotNull(equiposResultado);
        assertEquals(2, equiposResultado.size());

        Equipo resultado1 = equiposResultado.get(0);
        assertEquals(1, resultado1.getId());
        assertEquals("Equipo Ejemplo", resultado1.getNombre());
        assertEquals("La Liga", resultado1.getLiga());
        assertEquals("Argentina", resultado1.getPais());

        Equipo resultado2 = equiposResultado.get(1);
        assertEquals(2, resultado2.getId());
        assertEquals("Boca Juniors", resultado2.getNombre());
        assertEquals("Primera A", resultado2.getLiga());
        assertEquals("Argentina", resultado2.getPais());
    }

    @Test
    void buscar_equipo_ok() {
        String nombre = "Barcelona";

        Equipo equipo1 = new Equipo();
        equipo1.setId(1);
        equipo1.setNombre("FC Barcelona");
        equipo1.setLiga("La Liga");
        equipo1.setPais("España");

        Equipo equipo2 = new Equipo();
        equipo2.setId(2);
        equipo2.setNombre("Barcelona SC");
        equipo2.setLiga("Serie A");
        equipo2.setPais("Ecuador");

        List<Equipo> equipos = Arrays.asList(equipo1, equipo2);

        when(equipoRepository.findByNombreContaining(nombre)).thenReturn(equipos);

        List<Equipo> equiposResultado = equipoService.findByName(nombre);

        assertNotNull(equiposResultado);
        assertEquals(2, equiposResultado.size());

        Equipo resultado1 = equiposResultado.get(0);
        assertEquals(1, resultado1.getId());
        assertEquals("FC Barcelona", resultado1.getNombre());
        assertEquals("La Liga", resultado1.getLiga());
        assertEquals("España", resultado1.getPais());

        Equipo resultado2 = equiposResultado.get(1);
        assertEquals(2, resultado2.getId());
        assertEquals("Barcelona SC", resultado2.getNombre());
        assertEquals("Serie A", resultado2.getLiga());
        assertEquals("Ecuador", resultado2.getPais());
    }

    @Test
    void borrar_equipo_ok() throws EquipoException {
        Integer id = 1;

        when(equipoRepository.existsById(id)).thenReturn(true);

        equipoService.delete(id);

        verify(equipoRepository, times(1)).deleteById(id);
    }

    @Test
    void borrar_equipo_inexistente() {
        Integer id = 1;

        when(equipoRepository.existsById(id)).thenReturn(false);

        assertThrows(EquipoException.class, () -> {
            equipoService.delete(id);
        });
    }

    @Test
    void existe_ok() {
        Integer id = 1;

        when(equipoRepository.existsById(id)).thenReturn(true);

        boolean existe = equipoService.exists(id);

        assertTrue(existe);

        verify(equipoRepository, times(1)).existsById(id);
    }

    @Test
    void editar_equipo_ok() throws EquipoException {
        Integer id = 1;
        CrearEquipoDto equipoDto = getEquipoDto();

        Equipo equipoEditado = getEquipo();

        when(equipoRepository.existsById(id)).thenReturn(true);
        when(equipoRepository.getReferenceById(id)).thenReturn(equipoEditado);
        when(equipoRepository.save(any(Equipo.class))).thenReturn(equipoEditado);

        Optional<Equipo> equipoResultado = equipoService.edit(id, equipoDto);

        assertTrue(equipoResultado.isPresent());
        assertEquals(id, equipoResultado.get().getId());
        assertEquals("Equipo Ejemplo", equipoResultado.get().getNombre());
        assertEquals("La Liga", equipoResultado.get().getLiga());
        assertEquals("Argentina", equipoResultado.get().getPais());
    }

    @Test
    void editar_equipo_inexistente() {
        Integer id = 1;
        CrearEquipoDto equipoDto = getEquipoDto();

        when(equipoRepository.existsById(id)).thenReturn(false);

        assertThrows(EquipoException.class, () -> {
            equipoService.edit(id, equipoDto);
        });
    }

    private Equipo getEquipo() {
        return new Equipo(1, "Equipo Ejemplo", "La Liga", "Argentina");
    }

    private CrearEquipoDto getEquipoDto() {
        return new CrearEquipoDto("Equipo Ejemplo", "La Liga", "Argentina");
    }
}
