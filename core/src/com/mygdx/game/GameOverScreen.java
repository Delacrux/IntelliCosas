package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameOverScreen extends Pantalla {

    private Texture fondoGameOver;
	public GameOverScreen(final GameMenu game) {

        super(game);
        fondoGameOver = Recursos.getInstancia().getFondoGameOver();
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
        batch.draw(fondoGameOver, 0 , 0, 800, 480);

		font.draw(batch, "GAME OVER ", 290, 300);
		font.draw(batch, "Toca en cualquier lado para reiniciar.", 150, 240);
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
