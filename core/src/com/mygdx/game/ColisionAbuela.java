package com.mygdx.game;

public class ColisionAbuela implements EstrategiaColision{
    @Override
    public void colision(Cesta cesta) {
        cesta.curar();
    }
}