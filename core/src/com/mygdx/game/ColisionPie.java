package com.mygdx.game;

public class ColisionPie implements EstrategiaColision{
    @Override
    public void colision(Cesta cesta){
        cesta.sumarPuntos(25);
    }
}