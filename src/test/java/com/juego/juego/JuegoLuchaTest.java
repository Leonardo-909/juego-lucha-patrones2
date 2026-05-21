package com.juego.juego;

import com.juego.model.IPersonaje;
import com.juego.model.PersonajeBase;
import com.juego.patrones.strategy.AtaqueMagico;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas de JuegoLucha")
class JuegoLuchaTest {

    @Test
    @DisplayName("La pelea termina cuando un personaje queda sin HP")
    void testPeleaTermina() {
        IPersonaje j1 = new PersonajeBase("Heroe", new AtaqueMagico());
        IPersonaje j2 = new PersonajeBase("Villano", new AtaqueMagico());

        JuegoLucha juego = new JuegoLucha(j1, j2);
        juego.iniciarPelea();

        // Al terminar, al menos uno debe estar muerto
        assertFalse(j1.estaVivo() && j2.estaVivo());
    }

    @Test
    @DisplayName("Al terminar la pelea, el ganador sigue vivo")
    void testGanadorVivoAlTerminar() {
        IPersonaje j1 = new PersonajeBase("Heroe", new AtaqueMagico());
        IPersonaje j2 = new PersonajeBase("Villano", new AtaqueMagico());

        JuegoLucha juego = new JuegoLucha(j1, j2);
        juego.iniciarPelea();

        boolean alMenosUnoVivo = j1.estaVivo() || j2.estaVivo();
        assertTrue(alMenosUnoVivo);
    }
}