package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

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
    public final void render(float delta) {
        limpiarPantalla();
        actualizarCamara();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        dibujarContenido(delta);
        batch.end();
        manejarInput();
    }

    protected void limpiarPantalla() {
        ScreenUtils.clear(0, 0, 0.2f, 1);
    }

    protected void actualizarCamara() {
        camera.update();
    }

    protected abstract void dibujarContenido(float delta);

    protected void manejarInput() {
        //Algunas clases tendrán esto, otras no lo necesitarán (por eso no lo dejamos abstract)
    }



    @Override
    public void show (){

    }


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