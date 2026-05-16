package com.juego.juego;

import com.juego.model.IPersonaje;
import com.juego.model.PersonajeBase;
import com.juego.patrones.decorator.ArmaDecorator;
import com.juego.patrones.decorator.EscudoDecorator;
import com.juego.patrones.strategy.AtaqueFuerte;
import com.juego.patrones.strategy.AtaqueMagico;
import java.util.Scanner;

public class JuegoLucha {
    private IPersonaje jugador1;
    private IPersonaje jugador2;

    public JuegoLucha(IPersonaje j1, IPersonaje j2) {
        this.jugador1 = j1;
        this.jugador2 = j2;
    }

    public void iniciarPelea() {
        System.out.println("\n=== INICIO DE LA PELEA ===");
        System.out.println(jugador1.getNombre() + " vs " + jugador2.getNombre() + "\n");

        while (jugador1.estaVivo() && jugador2.estaVivo()) {
            turno(jugador1, jugador2);
            if (jugador2.estaVivo()) {
                turno(jugador2, jugador1);
            }
        }

        System.out.println("\n=== FIN DE LA PELEA ===");
        if (jugador1.estaVivo()) {
            System.out.println("Ganador: " + jugador1.getNombre());
        } else {
            System.out.println("Ganador: " + jugador2.getNombre());
        }
    }

    private void turno(IPersonaje atacante, IPersonaje defensor) {
        System.out.println("\n--- Turno de " + atacante.getNombre() + " ---");
        System.out.println("HP defensor: " + defensor.getPuntosDeVida());
        atacante.atacar(defensor);
        System.out.println("HP restante: " + defensor.getPuntosDeVida());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nombre del jugador 1: ");
        String nombre1 = scanner.nextLine();
        System.out.print("Nombre del jugador 2: ");
        String nombre2 = scanner.nextLine();

        // Patron Strategy: cada personaje tiene diferente tipo de ataque
        IPersonaje j1 = new PersonajeBase(nombre1, new AtaqueFuerte());
        IPersonaje j2 = new PersonajeBase(nombre2, new AtaqueMagico());

        // Patron Decorator: se agregan capacidades sin modificar las clases base
        System.out.println("\n" + nombre1 + " encontro una espada magica!");
        j1 = new ArmaDecorator(j1, 15);

        System.out.println(nombre2 + " encontro un escudo!");
        j2 = new EscudoDecorator(j2, 5);

        JuegoLucha juego = new JuegoLucha(j1, j2);
        juego.iniciarPelea();

        scanner.close();
    }
}