package ar.mateofernandez.equiposfutbol.repository;

import ar.mateofernandez.equiposfutbol.model.Equipo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class EquipoRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EquipoRepository equipoRepository;

    @Test
    void testFindByNombreContaining() {
        Equipo equipo1 = new Equipo(1, "Equipo1", "Liga1", "País1");
        Equipo equipo2 = new Equipo(2, "Equipo2", "Liga2", "País2");
        Equipo equipo3 = new Equipo(3, "Equipo3", "Liga3", "País3");

        entityManager.merge(equipo1);
        entityManager.merge(equipo2);
        entityManager.merge(equipo3);

        List<Equipo> equipos = equipoRepository.findByNombreContaining("Equipo");

        assertEquals(3, equipos.size());
        assertTrue(equipos.contains(equipo1));
        assertTrue(equipos.contains(equipo2));
        assertTrue(equipos.contains(equipo3));
    }

    @Test
    void testFindByNombreContainingNotFound() {
        Equipo equipo1 = new Equipo(1, "Equipo1", "Liga1", "País1");
        Equipo equipo2 = new Equipo(2, "Equipo2", "Liga2", "País2");
        Equipo equipo3 = new Equipo(3, "Equipo3", "Liga3", "País3");

        entityManager.merge(equipo1);
        entityManager.merge(equipo2);
        entityManager.merge(equipo3);

        List<Equipo> equipos = equipoRepository.findByNombreContaining("NoExiste");

        assertEquals(0, equipos.size());
    }
}
