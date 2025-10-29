package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;


public class MainMenuScreen extends Pantalla {

	public MainMenuScreen(final GameLluviaMenu game) {
        super(game);
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);

		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		font.getData().setScale(2, 2);
		font.draw(batch, "Bienvenido a Recolecta Gotas!!! ", 100, camera.viewportHeight/2+50);
		font.draw(batch, "Toca en cualquier lugar para comenzar!", 100, camera.viewportHeight/2-50);

		batch.end();

		if (Gdx.input.isTouched()) {
			game.setScreen(new GameScreen(game));
			dispose();
		}
	}

    public void pause() {
    }
}
