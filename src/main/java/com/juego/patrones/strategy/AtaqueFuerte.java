package com.juego.patrones.strategy;

import java.util.Random;

public class AtaqueFuerte implements AtaqueStrategy {
    private Random rand = new Random();

    @Override
    public int calcularDano() {
        return rand.nextInt(21) + 30; // 30 - 50
    }

    @Override
    public String descripcion() {
        return "ataque FUERTE";
    }
}