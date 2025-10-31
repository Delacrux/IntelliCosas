package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;


public class PausaScreen extends Pantalla {

	private GameScreen juego;

	public PausaScreen (final GameMenu game, GameScreen juego) {
		super(game);
        this.juego = juego;
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 1.0f, 0.5f);

		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		font.draw(batch, "Juego en Pausa ", 100, 150);
		font.draw(batch, "Toca en cualquier lado para continuar !!!", 100, 100);
		batch.end();

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

