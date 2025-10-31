package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;


public class MainMenuScreen extends Pantalla {

    private Texture fondoMenu;
	public MainMenuScreen(final GameMenu game) {

        super(game);
        fondoMenu = new Texture(Gdx.files.internal("fondoiniciofin.jpeg"));
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);

		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
        batch.draw(fondoMenu,0,0,800,480);
		font.getData().setScale(2, 2);
		font.draw(batch, "Bienvenido a ¡Atrapa, Caperucita!", 180, camera.viewportHeight/2+50);
		font.draw(batch, "Toca en cualquier lugar para comenzar!", 150, camera.viewportHeight/2-50);

		batch.end();

		if (Gdx.input.isTouched()) {
			game.setScreen(new GameScreen(game));
			dispose();
		}
	}

    public void pause() {
    }
}
