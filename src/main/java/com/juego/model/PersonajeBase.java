package com.juego.model;

import com.juego.patrones.strategy.AtaqueStrategy;
import com.juego.patrones.strategy.AtaqueNormal;

public class PersonajeBase implements IPersonaje {
    private String nombre;
    private int puntosDeVida;
    private AtaqueStrategy estrategiaAtaque;

    public PersonajeBase(String nombre) {
        this.nombre = nombre;
        this.puntosDeVida = 100;
        this.estrategiaAtaque = new AtaqueNormal();
    }

    // Constructor que acepta estrategia personalizada (patron Strategy)
    public PersonajeBase(String nombre, AtaqueStrategy estrategia) {
        this.nombre = nombre;
        this.puntosDeVida = 100;
        this.estrategiaAtaque = estrategia;
    }

    // Permite cambiar la estrategia en tiempo de ejecucion
    public void setEstrategiaAtaque(AtaqueStrategy estrategia) {
        this.estrategiaAtaque = estrategia;
    }

    @Override
    public void atacar(IPersonaje oponente) {
        int dano = estrategiaAtaque.calcularDano();
        System.out.println(nombre + " usa " + estrategiaAtaque.descripcion()
            + " contra " + oponente.getNombre()
            + " causando " + dano + " puntos de dano.");
        oponente.recibirDano(dano);
    }

    @Override
    public void recibirDano(int dano) {
        if (dano < 0) return;
        this.puntosDeVida -= dano;
        if (this.puntosDeVida < 0) this.puntosDeVida = 0;
    }

    @Override
    public boolean estaVivo() { return puntosDeVida > 0; }

    @Override
    public String getNombre() { return nombre; }

    @Override
    public int getPuntosDeVida() { return puntosDeVida; }
}