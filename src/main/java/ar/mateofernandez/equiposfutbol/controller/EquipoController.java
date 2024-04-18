package ar.mateofernandez.equiposfutbol.controller;

import ar.mateofernandez.equiposfutbol.exception.EquipoException;
import ar.mateofernandez.equiposfutbol.model.CrearEquipoDto;
import ar.mateofernandez.equiposfutbol.model.Equipo;
import ar.mateofernandez.equiposfutbol.model.ErrorResponse;
import ar.mateofernandez.equiposfutbol.service.EquipoServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("equipos")
public class EquipoController {
    private final EquipoServiceImpl equipoService;
    public static final String NOT_FOUND_MSG = "Equipo no encontrado";
    public static final int NOT_FOUND_CODE = 404;

    public EquipoController(EquipoServiceImpl equipoService) {
        this.equipoService = equipoService;
    }

    @GetMapping
    public ResponseEntity<List<Equipo>> getAll() {
        return new ResponseEntity<>(equipoService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<>(equipoService.find(id), HttpStatus.OK);
        } catch (EquipoException e) {
            ErrorResponse errorResponse = new ErrorResponse(NOT_FOUND_MSG, NOT_FOUND_CODE);
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/buscar")
    public List<Equipo> findByName(@RequestParam String nombre) {
        return equipoService.findByName(nombre);
    }

    @PostMapping
    public ResponseEntity<Equipo> save(@RequestBody CrearEquipoDto equipoDto) {
        Equipo equipo = equipoService.save(equipoDto);
        return new ResponseEntity<>(equipo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> edit(@PathVariable Integer id, @RequestBody CrearEquipoDto equipo) {
        try {
            return new ResponseEntity<>(equipoService.edit(id, equipo), HttpStatus.OK);
        } catch (EquipoException e) {
            ErrorResponse errorResponse = new ErrorResponse(NOT_FOUND_MSG, NOT_FOUND_CODE);
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        try {
            equipoService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EquipoException e) {
            ErrorResponse errorResponse = new ErrorResponse(NOT_FOUND_MSG, NOT_FOUND_CODE);
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }
}
