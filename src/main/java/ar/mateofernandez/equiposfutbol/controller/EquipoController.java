package ar.mateofernandez.equiposfutbol.controller;

import ar.mateofernandez.equiposfutbol.exception.EquipoException;
import ar.mateofernandez.equiposfutbol.model.dto.CrearEquipoDto;
import ar.mateofernandez.equiposfutbol.model.Equipo;
import ar.mateofernandez.equiposfutbol.model.response.ErrorResponse;
import ar.mateofernandez.equiposfutbol.service.EquipoServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("equipos")
public class EquipoController {
    private final EquipoServiceImpl equipoService;
    public static final String NOT_FOUND_MSG = "Equipo no encontrado";
    public static final int NOT_FOUND_CODE = 404;
    public static final String BAD_REQUEST_MSG = "La solicitud es invalida";
    public static final int BAD_REQUEST_CODE = 400;

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
    public ResponseEntity<Object> save(@Validated @RequestBody CrearEquipoDto equipoDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ErrorResponse errorResponse = new ErrorResponse(BAD_REQUEST_MSG, BAD_REQUEST_CODE);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
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
