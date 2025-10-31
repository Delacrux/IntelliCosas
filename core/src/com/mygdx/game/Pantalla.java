package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Pantalla implements Screen {
    protected final GameMenu game;
    protected SpriteBatch batch;
    protected BitmapFont font;
    protected OrthographicCamera camera;

    public Pantalla(final GameMenu game){
        this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void show (){

    }

    /** Called when the screen should render itself.
     * @param delta The time in seconds since the last render. */
    @Override
    public abstract void render (float delta); //Metodos a utilizar jijija

    /** @see ApplicationListener#resize(int, int) */
    @Override
    public void resize (int width, int height){

    }

    /** @see ApplicationListener#pause() */
    @Override
    public abstract void pause (); //Metodo a utilizar maybe jijija

    /** @see ApplicationListener#resume() */
    @Override
    public void resume (){

    }

    /** Called when this screen is no longer the current screen for a {@link Game}. */
    @Override
    public void hide (){

    }

    /** Called when this screen should release all resources. */
    @Override
    public void dispose (){

    }
}

