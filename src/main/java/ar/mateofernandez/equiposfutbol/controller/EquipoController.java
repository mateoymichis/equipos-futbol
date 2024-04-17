package ar.mateofernandez.equiposfutbol.controller;

import ar.mateofernandez.equiposfutbol.model.Equipo;
import ar.mateofernandez.equiposfutbol.service.EquipoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("equipos")
public class EquipoController {
    EquipoService equipoService;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Equipo>> get(@PathVariable("id") Integer id) {
        Optional<Equipo> equipo = equipoService.find(id);
        return new ResponseEntity<>(equipo, HttpStatus.OK);
    }
}
