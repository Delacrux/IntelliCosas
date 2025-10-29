package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameOverScreen extends Pantalla {

	public GameOverScreen(final GameLluviaMenu game) {
        super(game);
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		font.draw(batch, "GAME OVER ", 100, 200);
		font.draw(batch, "Toca en cualquier lado para reiniciar.", 100, 100);
		batch.end();

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
