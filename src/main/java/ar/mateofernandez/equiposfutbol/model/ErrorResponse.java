package ar.mateofernandez.equiposfutbol.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponse {
    private String mensaje;
    private int  codigo;
}
