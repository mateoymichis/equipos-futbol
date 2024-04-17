package ar.mateofernandez.equiposfutbol.controller;

import ar.mateofernandez.equiposfutbol.exception.EquipoException;
import ar.mateofernandez.equiposfutbol.model.CrearEquipoDto;
import ar.mateofernandez.equiposfutbol.model.Equipo;
import ar.mateofernandez.equiposfutbol.service.EquipoServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("equipos")
public class EquipoController {
    EquipoServiceImpl equipoService;

    public EquipoController(EquipoServiceImpl equipoService) {
        this.equipoService = equipoService;
    }

    @GetMapping
    public ResponseEntity<List<Equipo>> getAll() {
        return new ResponseEntity<>(equipoService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Equipo>> get(@PathVariable("id") Integer id) {
        Optional<Equipo> equipo = equipoService.find(id);
        return new ResponseEntity<>(equipo, HttpStatus.OK);
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
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        try {
            equipoService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EquipoException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
