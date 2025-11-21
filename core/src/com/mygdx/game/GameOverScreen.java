package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


public class GameOverScreen extends Pantalla {

    private Texture fondoGameOver;
	public GameOverScreen(final GameMenu game) {

        super(game);
        fondoGameOver = Recursos.getInstancia().getFondoGameOver();
	}

    protected void dibujarContenido(float delta) {
        batch.draw(fondoGameOver, 0 , 0, 800, 480);
        font.draw(batch, "GAME OVER ", 290, 300);
        font.draw(batch, "Toca en cualquier lado para reiniciar.", 150, 240);
    }

    @Override
    protected void manejarInput() {
        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }


    @Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

}
