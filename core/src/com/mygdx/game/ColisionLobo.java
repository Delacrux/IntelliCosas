package com.mygdx.game;

public class ColisionLobo implements EstrategiaColision {
    @Override
    public void colision(Cesta cesta) {
        cesta.dañar();
    }
}