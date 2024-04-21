package ar.mateofernandez.equiposfutbol.controller;

import ar.mateofernandez.equiposfutbol.exception.EquipoException;
import ar.mateofernandez.equiposfutbol.model.Equipo;
import ar.mateofernandez.equiposfutbol.model.dto.CrearEquipoDto;
import ar.mateofernandez.equiposfutbol.service.EquipoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EquipoControllerTest {

    @Mock
    private EquipoServiceImpl equipoService;

    @InjectMocks
    private EquipoController equipoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        Equipo equipo1 = new Equipo(1, "Equipo1", "Liga1", "País1");
        Equipo equipo2 = new Equipo(2, "Equipo2", "Liga2", "País2");
        List<Equipo> equipos = Arrays.asList(equipo1, equipo2);

        when(equipoService.getAll()).thenReturn(equipos);

        ResponseEntity<List<Equipo>> response = equipoController.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(equipos, response.getBody());
    }

    @Test
    void testGetById() throws EquipoException {
        Equipo equipo = new Equipo(1, "Equipo1", "Liga1", "País1");
        Optional<Equipo> equipoEsperado = Optional.of(equipo);

        when(equipoService.find(1)).thenReturn(Optional.of(equipo));

        ResponseEntity<Object> response = equipoController.get(1);

        assertEquals( HttpStatus.OK, response.getStatusCode());
        assertEquals(equipoEsperado, response.getBody());
    }

    @Test
    void testGetByIdNotFound() throws EquipoException {
        when(equipoService.find(1)).thenThrow(new EquipoException("Equipo no encontrado"));

        ResponseEntity<Object> response = equipoController.get(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testFindByName() {
        Equipo equipo1 = new Equipo(1, "Equipo1", "Liga1", "País1");
        Equipo equipo2 = new Equipo(2, "Equipo2", "Liga2", "País2");
        List<Equipo> equipos = Arrays.asList(equipo1, equipo2);

        when(equipoService.findByName("Equipo")).thenReturn(equipos);

        List<Equipo> response = equipoController.findByName("Equipo");

        assertEquals(equipos, response);
    }

    @Test
    void testSave() {
        CrearEquipoDto equipoDto = new CrearEquipoDto("Equipo1", "Liga1", "País1");
        Equipo equipo = new Equipo(1, "Equipo1", "Liga1", "País1");
        BindingResult bindingResult = mock(BindingResult.class);

        when(bindingResult.hasErrors()).thenReturn(false);
        when(equipoService.save(equipoDto)).thenReturn(equipo);

        ResponseEntity<Object> response = equipoController.save(equipoDto, bindingResult);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(equipo, response.getBody());
    }

    @Test
    void testSaveBadRequest() {
        CrearEquipoDto equipoDto = new CrearEquipoDto("Equipo1", "Liga1", "País1");
        BindingResult bindingResult = mock(BindingResult.class);

        when(bindingResult.hasErrors()).thenReturn(true);

        ResponseEntity<Object> response = equipoController.save(equipoDto, bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testEdit() throws EquipoException {
        CrearEquipoDto equipoDto = new CrearEquipoDto("EquipoEditado", "Liga1", "País1");
        Equipo equipo = new Equipo(1, "EquipoEditado", "Liga1", "País1");
        Optional<Equipo> equipoEsperado = Optional.of(equipo);

        when(equipoService.edit(1, equipoDto)).thenReturn(Optional.of(equipo));

        ResponseEntity<Object> response = equipoController.edit(1, equipoDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(equipoEsperado, response.getBody());
    }

    @Test
    void testEditNotFound() throws EquipoException {
        CrearEquipoDto equipoDto = new CrearEquipoDto("Equipo1", "Liga1", "País1");

        when(equipoService.edit(1, equipoDto)).thenThrow(new EquipoException("Equipo no encontrado"));

        ResponseEntity<Object> response = equipoController.edit(1, equipoDto);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDelete() {
        ResponseEntity<Object> response = equipoController.delete(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testDeleteNotFound() throws EquipoException {
        doThrow(new EquipoException("Equipo no encontrado")).when(equipoService).delete(1);

        ResponseEntity<Object> response = equipoController.delete(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
