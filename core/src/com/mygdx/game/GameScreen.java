package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends Pantalla {

	private Cesta cesta;
	private Controlador controlador;
    Texture fondo;

	   
	//boolean activo = true;

	public GameScreen(final GameMenu game) {
        super(game);
        batch = new SpriteBatch();

        // cargamos la imagen de fondo
        fondo = Recursos.getInstancia().getFondoJuego();

        // cargamos la imagen y sonido de la manzana (64x64, mp3)
        Texture manzana = Recursos.getInstancia().getManzana();
        Sound sonidoManzana = Recursos.getInstancia().getSonidoManzana();

        // cargamos la imagen y sonido de la abuela (64x64, mp3)
        Texture pieManzana = Recursos.getInstancia().getPieManzana();
        Sound sonidoPie = Recursos.getInstancia().getSonidoPie();

        // cargamos la imagen y sonido de la abuela (64x64, mp3)
        Texture abuela = Recursos.getInstancia().getAbuela();
        Sound sonidoAbuela = Recursos.getInstancia().getSonidoAbuela();

        // cargamos la imagen y sonido del lobo (64x64, mp3)
        Texture lobo = Recursos.getInstancia().getLobo();
        Sound sonidoLobo = Recursos.getInstancia().getSonidoLobo();

        // cargamos imagen de la canasta (64x64)
        cesta = new Cesta(Recursos.getInstancia().getCesta(), sonidoLobo, sonidoAbuela, sonidoManzana, sonidoPie);

        // cargamos la musica de fondo (mp3)
        Music musicaFondo = Recursos.getInstancia().getMusicaFondo();
        controlador = new Controlador(manzana, pieManzana, abuela, lobo, musicaFondo);

        // creacion del tarro
        cesta.crear();

        // creacion de la lluvia
        controlador.crear();
	}

    @Override
    protected void dibujarContenido(float delta) {
        batch.draw(fondo, 0, 0, 800, 480);
        font.draw(batch, "Gotas totales: " + cesta.getPuntos(), 5, 475);
        font.draw(batch, "Vidas : " + cesta.getVidas(), 670, 475);
        font.draw(batch, "HighScore : " + game.getHigherScore(), camera.viewportWidth/2-50, 475);

        if (!cesta.estaHerido()) {
            cesta.actualizarMovimiento();
            if (!controlador.actualizarMovimiento(cesta)) {
                if (game.getHigherScore() < cesta.getPuntos())
                    game.setHigherScore(cesta.getPuntos());
                game.setScreen(new GameOverScreen(game));
                dispose();
                return;
            }
        }

        cesta.dibujar(batch);
        controlador.dibujar(batch);
    }


    @Override
    protected void manejarInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            controlador.pausar();
            game.setScreen(new PausaScreen(game, this));
        }
    }

    @Override
	public void show() {
	  // continuar con sonido de lluvia
	  controlador.continuar();
	}

	@Override
	public void pause() {
		controlador.pausar();
		game.setScreen(new PausaScreen(game, this)); 
	}

	@Override
	public void dispose() {
      cesta.destruir();
      controlador.destruir();
	}

}