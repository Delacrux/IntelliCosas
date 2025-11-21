package com.mygdx.game;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

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
	public void render(float delta) {
		//limpia la pantalla con color azul obscuro.
		ScreenUtils.clear(0, 0, 0.2f, 1);
		//actualizar matrices de la cámara
		camera.update();
		//actualizar 
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
        //dibujar fondo
        batch.draw(fondo, 0, 0, 800, 480);
		//dibujar textos
		font.draw(batch, "Gotas totales: " + cesta.getPuntos(), 5, 475);
		font.draw(batch, "Vidas : " + cesta.getVidas(), 670, 475);
		font.draw(batch, "HighScore : " + game.getHigherScore(), camera.viewportWidth/2-50, 475);
		
		if (!cesta.estaHerido()) {
			// movimiento del tarro desde teclado
	        cesta.actualizarMovimiento();
			// caida de la lluvia 
	       if (!controlador.actualizarMovimiento(cesta)) {
	    	  //actualizar HigherScore
	    	  if (game.getHigherScore()< cesta.getPuntos())
	    		  game.setHigherScore(cesta.getPuntos());
	    	  //ir a la ventana de finde juego y destruir la actual
	    	  game.setScreen(new GameOverScreen(game));
	    	  dispose();
	       }
		}
		
		cesta.dibujar(batch);
		controlador.dibujar(batch);
		
		batch.end();
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