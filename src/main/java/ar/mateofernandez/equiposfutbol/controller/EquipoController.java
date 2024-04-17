package ar.mateofernandez.equiposfutbol.controller;

import ar.mateofernandez.equiposfutbol.model.Equipo;
import ar.mateofernandez.equiposfutbol.service.EquipoServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
