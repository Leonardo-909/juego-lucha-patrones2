package com.juego.model;

import com.juego.patrones.strategy.AtaqueFuerte;
import com.juego.patrones.strategy.AtaqueMagico;
import com.juego.patrones.strategy.AtaqueNormal;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Pruebas de PersonajeBase")
class PersonajeBaseTest {

    private PersonajeBase guerrero;

    @BeforeEach
    void setUp() {
        guerrero = new PersonajeBase("Thor");
    }

    @Test
    @DisplayName("Debe crear personaje con 100 HP y nombre correcto")
    void testCreacionPersonaje() {
        assertEquals("Thor", guerrero.getNombre());
        assertEquals(100, guerrero.getPuntosDeVida());
        assertTrue(guerrero.estaVivo());
    }

    @Test
    @DisplayName("Debe reducir HP al recibir dano")
    void testRecibirDano() {
        guerrero.recibirDano(30);
        assertEquals(70, guerrero.getPuntosDeVida());
    }

    @Test
    @DisplayName("HP no debe ser negativo")
    void testHpNoNegativo() {
        guerrero.recibirDano(150);
        assertEquals(0, guerrero.getPuntosDeVida());
        assertFalse(guerrero.estaVivo());
    }

    @Test
    @DisplayName("Dano negativo no debe modificar HP")
    void testDanoNegativo() {
        guerrero.recibirDano(-10);
        assertEquals(100, guerrero.getPuntosDeVida());
    }

    @Test
    @DisplayName("Ataque normal debe causar dano entre 10 y 30")
    void testAtaqueNormalRango() {
        IPersonaje oponente = new PersonajeBase("Loki");
        guerrero.atacar(oponente);
        int dano = 100 - oponente.getPuntosDeVida();
        assertTrue(dano >= 10 && dano <= 30, "Dano fue: " + dano);
    }

    @Test
    @DisplayName("Ataque fuerte debe causar dano entre 30 y 50")
    void testAtaqueFuerteRango() {
        guerrero = new PersonajeBase("Thor", new AtaqueFuerte());
        IPersonaje oponente = new PersonajeBase("Loki");
        guerrero.atacar(oponente);
        int dano = 100 - oponente.getPuntosDeVida();
        assertTrue(dano >= 30 && dano <= 50, "Dano fue: " + dano);
    }

    @Test
    @DisplayName("Ataque magico debe causar dano fijo de 25")
    void testAtaqueMagicoFijo() {
        guerrero = new PersonajeBase("Thor", new AtaqueMagico());
        IPersonaje oponente = new PersonajeBase("Loki");
        guerrero.atacar(oponente);
        assertEquals(75, oponente.getPuntosDeVida());
    }

    @Test
    @DisplayName("Debe poder cambiar estrategia en tiempo de ejecucion")
    void testCambioEstrategia() {
        guerrero.setEstrategiaAtaque(new AtaqueMagico());
        IPersonaje oponente = new PersonajeBase("Loki");
        guerrero.atacar(oponente);
        assertEquals(75, oponente.getPuntosDeVida());
    }
}