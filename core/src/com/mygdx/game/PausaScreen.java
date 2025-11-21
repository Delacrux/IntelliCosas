package com.mygdx.game;

import com.badlogic.gdx.Gdx;


public class PausaScreen extends Pantalla {

	private GameScreen juego;

	public PausaScreen (final GameMenu game, GameScreen juego) {
		super(game);
        this.juego = juego;
	}

    protected void dibujarContenido(float delta){
        font.draw(batch, "Juego en Pausa ", 100, 150);
        font.draw(batch, "Toca en cualquier lado para continuar !!!", 100, 100);
    }

    protected void manejarInput(){
        if (Gdx.input.isTouched()) {
            game.setScreen(juego);
            dispose();
        }
    }

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

}

