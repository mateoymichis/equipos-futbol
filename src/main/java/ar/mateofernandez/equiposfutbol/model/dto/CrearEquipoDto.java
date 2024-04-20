package ar.mateofernandez.equiposfutbol.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CrearEquipoDto {
    @NotBlank(message = "El nombre es requerido")
    private String nombre;
    @NotBlank(message = "La liga es requerido")
    private String liga;
    @NotBlank(message = "El país es requerido")
    private String pais;
}
