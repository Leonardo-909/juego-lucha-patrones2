package com.juego.patrones.strategy;

public class AtaqueMagico implements AtaqueStrategy {

    @Override
    public int calcularDano() {
        return 25; // dano fijo magico
    }

    @Override
    public String descripcion() {
        return "ataque MAGICO";
    }
}