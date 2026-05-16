package com.juego.patrones;

import com.juego.model.IPersonaje;
import com.juego.model.PersonajeBase;
import com.juego.patrones.decorator.ArmaDecorator;
import com.juego.patrones.decorator.EscudoDecorator;
import com.juego.patrones.strategy.AtaqueMagico;
import com.juego.patrones.strategy.AtaqueNormal;
import com.juego.patrones.strategy.AtaqueFuerte;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Pruebas de patrones Decorator y Strategy")
class PatronesTest {

    @Test
    @DisplayName("ArmaDecorator debe aplicar dano extra al oponente")
    void testArmaDecoratorDanoExtra() {
        IPersonaje mockOponente = mock(IPersonaje.class);
        IPersonaje base = new PersonajeBase("Guerrero", new AtaqueMagico());
        IPersonaje armado = new ArmaDecorator(base, 15);

        armado.atacar(mockOponente);

        // Verifica que recibirDano fue llamado dos veces (ataque base + arma)
        verify(mockOponente, times(2)).recibirDano(anyInt());
        // Verifica que el extra de 15 fue aplicado
        verify(mockOponente).recibirDano(15);
    }

    @Test
    @DisplayName("ArmaDecorator debe modificar el nombre del personaje")
    void testArmaDecoratorNombre() {
        IPersonaje base = new PersonajeBase("Thor");
        IPersonaje armado = new ArmaDecorator(base, 10);
        assertTrue(armado.getNombre().contains("Thor"));
        assertTrue(armado.getNombre().contains("Arma"));
    }

    @Test
    @DisplayName("EscudoDecorator debe reducir el dano recibido")
    void testEscudoDecoratorReduceDano() {
        IPersonaje base = new PersonajeBase("Guerrero");
        IPersonaje escudado = new EscudoDecorator(base, 5);

        escudado.recibirDano(20);
        // Con escudo de 5, recibe solo 15
        assertEquals(85, escudado.getPuntosDeVida());
    }

    @Test
    @DisplayName("EscudoDecorator no debe resultar en HP negativo")
    void testEscudoDecoratorHpNoNegativo() {
        IPersonaje base = new PersonajeBase("Guerrero");
        IPersonaje escudado = new EscudoDecorator(base, 200);

        escudado.recibirDano(50);
        assertEquals(100, escudado.getPuntosDeVida());
    }

    @Test
    @DisplayName("EscudoDecorator debe modificar el nombre del personaje")
    void testEscudoDecoratorNombre() {
        IPersonaje base = new PersonajeBase("Athena");
        IPersonaje escudada = new EscudoDecorator(base, 5);
        assertTrue(escudada.getNombre().contains("Athena"));
        assertTrue(escudada.getNombre().contains("Escudo"));
    }

    @Test
    @DisplayName("Se pueden combinar Decorator y Strategy")
    void testCombinacionPatrones() {
        IPersonaje base = new PersonajeBase("Mago", new AtaqueMagico());
        IPersonaje armado = new ArmaDecorator(base, 10);
        IPersonaje oponente = new PersonajeBase("Enemigo");

        armado.atacar(oponente);
        // AtaqueMagico = 25 fijo + ArmaDecorator = 10 extra => 35 de dano total
        assertEquals(65, oponente.getPuntosDeVida());
    }

    @Test
    @DisplayName("AtaqueNormal genera dano en rango 10-30")
    void testAtaqueNormalRango() {
        AtaqueNormal normal = new AtaqueNormal();
        for (int i = 0; i < 50; i++) {
            int d = normal.calcularDano();
            assertTrue(d >= 10 && d <= 30);
        }
    }

    @Test
    @DisplayName("AtaqueFuerte genera dano en rango 30-50")
    void testAtaqueFuerteRango() {
        AtaqueFuerte fuerte = new AtaqueFuerte();
        for (int i = 0; i < 50; i++) {
            int d = fuerte.calcularDano();
            assertTrue(d >= 30 && d <= 50);
        }
    }

    @Test
    @DisplayName("AtaqueMagico siempre da 25 de dano")
    void testAtaqueMagicoFijo() {
        assertEquals(25, new AtaqueMagico().calcularDano());
    }
}