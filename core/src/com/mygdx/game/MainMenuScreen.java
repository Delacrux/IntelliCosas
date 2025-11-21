package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


public class MainMenuScreen extends Pantalla {

    private Texture fondoMenu;
	public MainMenuScreen(final GameMenu game) {

        super(game);
        fondoMenu = Recursos.getInstancia().getFondoMenu();
	}

    protected void dibujarContenido(float delta) {
        batch.draw(fondoMenu, 0, 0, 800, 480);
        font.getData().setScale(2, 2);
        font.draw(batch, "Bienvenido a ¡Atrapa, Caperucita!", 180, camera.viewportHeight/2+50);
        font.draw(batch, "Toca en cualquier lugar para comenzar!", 150, camera.viewportHeight/2-50);
    }

    @Override
    protected void manejarInput(){
        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    public void pause() {
    }
}
