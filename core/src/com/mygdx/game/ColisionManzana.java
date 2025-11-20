package com.mygdx.game;

public class ColisionManzana implements EstrategiaColision{
    @Override
    public void colision(Cesta cesta){
        cesta.sumarPuntos(10);
    }
}
