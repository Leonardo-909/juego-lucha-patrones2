package com.juego.patrones.strategy;

import java.util.Random;

public class AtaqueNormal implements AtaqueStrategy {
    private Random rand = new Random();

    @Override
    public int calcularDano() {
        return rand.nextInt(21) + 10; // 10 - 30
    }

    @Override
    public String descripcion() {
        return "ataque normal";
    }
}