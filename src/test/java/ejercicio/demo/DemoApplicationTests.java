package ejercicio.demo;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ejercicio.demo.clases.Jornada;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DemoApplicationTests { //pruebas de la la función generar jornada que es la única 

    private Jornada jornada;

    @BeforeEach
    public void setup() {
        jornada = new Jornada();
    }

    @Test
    public void testGenerarPrimeraJornada_4Equipos_1Pista() {
        List<Integer> equipos = Arrays.asList(1, 2, 3, 4);
        jornada.generarPrimeraJornada(1, equipos.size(), equipos);

        // 4 equipos deben jugar en pista 0
        assertEquals(4, jornada.getEquiposJuegan().size());
        assertEquals(4, jornada.getPistasEquiposJuegan().size());
        assertTrue(jornada.getPistasEquiposJuegan().stream().allMatch(p -> p == 0));

        // Ningún equipo queda sin jugar
        assertTrue(jornada.getEquiposNoJuegan().isEmpty());
    }

    @Test
    public void testGenerarPrimeraJornada_6Equipos_2Pistas() {
        List<Integer> equipos = Arrays.asList(1, 2, 3, 4, 5, 6);
        jornada.generarPrimeraJornada(2, equipos.size(), equipos);

        // 4 primeros equipos en pista 0, 2 siguientes en pista 1
        assertEquals(6, jornada.getEquiposJuegan().size());
        assertEquals(6, jornada.getPistasEquiposJuegan().size());

        // Verificar que se usaron solo pistas 0 y 1
        assertTrue(jornada.getPistasEquiposJuegan().containsAll(List.of(0, 1)));

        // Ningún equipo queda sin jugar
        assertTrue(jornada.getEquiposNoJuegan().isEmpty());
    }

    @Test
    public void testGenerarPrimeraJornada_5Equipos_1Pista() {
        List<Integer> equipos = Arrays.asList(1, 2, 3, 4, 5);
        jornada.generarPrimeraJornada(1, equipos.size(), equipos);

        // 4 juegan, 1 no juega
        assertEquals(4, jornada.getEquiposJuegan().size());
        assertEquals(1, jornada.getEquiposNoJuegan().size());
        assertEquals(5, jornada.getEquiposNoJuegan().get(0));
    }

    @Test
    public void testGenerarPrimeraJornada_8Equipos_2Pistas() {
        List<Integer> equipos = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        jornada.generarPrimeraJornada(2, equipos.size(), equipos);

        // 8 equipos, 2 pistas → 4 equipos por pista
        assertEquals(8, jornada.getEquiposJuegan().size());
        assertEquals(8, jornada.getPistasEquiposJuegan().size());
        assertTrue(jornada.getEquiposNoJuegan().isEmpty());
    }

    @Test
    public void testGenerarPrimeraJornada_MasEquiposQuePistas() {
        List<Integer> equipos = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        jornada.generarPrimeraJornada(2, equipos.size(), equipos);

        // Solo 8 pueden jugar (4 por pista), 2 quedan sin jugar
        assertEquals(8, jornada.getEquiposJuegan().size());
        assertEquals(2, jornada.getEquiposNoJuegan().size());
        assertTrue(jornada.getEquiposNoJuegan().containsAll(List.of(9, 10)));
    }

    @Test
    public void testGenerarPrimeraJornada_UnSoloEquipo() {
        List<Integer> equipos = List.of(1);
        jornada.generarPrimeraJornada(2, equipos.size(), equipos);

        // Ningún equipo juega, 1 no juega
        assertEquals(0, jornada.getEquiposJuegan().size());
        assertEquals(1, jornada.getEquiposNoJuegan().size());
        assertEquals(1, jornada.getEquiposNoJuegan().get(0));
    }
}