package com.juego.patrones.decorator;

import com.juego.model.IPersonaje;
import com.juego.model.PersonajeDecorator;

public class EscudoDecorator extends PersonajeDecorator {
    private int reduccionDano;

    public EscudoDecorator(IPersonaje personaje, int reduccionDano) {
        super(personaje);
        this.reduccionDano = reduccionDano;
    }

    @Override
    public void recibirDano(int dano) {
        int danoReducido = Math.max(0, dano - reduccionDano);
        System.out.println(getNombre() + " bloquea " + reduccionDano
            + " puntos de dano con su escudo!");
        super.recibirDano(danoReducido);
    }

    @Override
    public String getNombre() {
        return super.getNombre() + " (con Escudo)";
    }
}