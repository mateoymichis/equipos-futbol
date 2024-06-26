package ar.mateofernandez.equiposfutbol.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "equipos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String liga;
    private String pais;

    @Override
    public boolean equals(Object obj) {
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Equipo equipo = (Equipo) obj;
        return (equipo.getId().equals(this.getId()) &&
                equipo.getNombre().equals(this.getNombre()) &&
                equipo.getLiga().equals(this.getLiga()) &&
                equipo.getPais().equals(this.getPais()));
    }
}
