package com.juego.model;

public abstract class PersonajeDecorator implements IPersonaje {
    protected IPersonaje personajeWrappeado;

    public PersonajeDecorator(IPersonaje personaje) {
        this.personajeWrappeado = personaje;
    }

    @Override
    public void atacar(IPersonaje oponente) {
        personajeWrappeado.atacar(oponente);
    }

    @Override
    public void recibirDano(int dano) {
        personajeWrappeado.recibirDano(dano);
    }

    @Override
    public boolean estaVivo() { return personajeWrappeado.estaVivo(); }

    @Override
    public String getNombre() { return personajeWrappeado.getNombre(); }

    @Override
    public int getPuntosDeVida() { return personajeWrappeado.getPuntosDeVida(); }
}