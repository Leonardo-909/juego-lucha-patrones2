package com.juego.patrones.decorator;

import com.juego.model.IPersonaje;
import com.juego.model.PersonajeDecorator;

public class ArmaDecorator extends PersonajeDecorator {
    private int danoExtra;

    public ArmaDecorator(IPersonaje personaje, int danoExtra) {
        super(personaje);
        this.danoExtra = danoExtra;
    }

    @Override
    public void atacar(IPersonaje oponente) {
        super.atacar(oponente);
        System.out.println(getNombre() + " usa su arma y causa "
            + danoExtra + " puntos de dano adicional!");
        oponente.recibirDano(danoExtra);
    }

    @Override
    public String getNombre() {
        return super.getNombre() + " (con Arma)";
    }
}