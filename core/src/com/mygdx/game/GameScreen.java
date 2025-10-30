package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen extends Pantalla {

	private Tarro tarro;
	private Lluvia lluvia;
    Texture fondo;

	   
	//boolean activo = true;

	public GameScreen(final GameLluviaMenu game) {
        super(game);
        batch = new SpriteBatch();

        // cargamos la imagen de fondo
        fondo = new Texture(Gdx.files.internal("fondo.png"));

        // cargamos la imagen y sonido de la manzana (64x64, mp3)
        Texture manzana = new Texture(Gdx.files.internal("manzana.png"));
        Sound sonidoManzana = Gdx.audio.newSound(Gdx.files.internal("atrapar.mp3"));

        // cargamos la imagen y sonido de la abuela (64x64, mp3)
        Texture pieManzana = new Texture(Gdx.files.internal("piemanzana.png"));
        Sound sonidoPie = Gdx.audio.newSound(Gdx.files.internal("comer.mp3"));

        // cargamos la imagen y sonido de la abuela (64x64, mp3)
        Texture abuela = new Texture(Gdx.files.internal("awela.png"));
        Sound sonidoAbuela = Gdx.audio.newSound(Gdx.files.internal("healing.mp3"));

        // cargamos la imagen y sonido del lobo (64x64, mp3)
        Texture lobo = new Texture(Gdx.files.internal("lobo.png"));
        Sound sonidoLobo = Gdx.audio.newSound(Gdx.files.internal("danio.mp3"));

        // cargamos imagen de la canasta (64x64)
        tarro = new Tarro(new Texture(Gdx.files.internal("canasto.png")),sonidoLobo, sonidoAbuela);

        // cargamos la musica de fondo (mp3)
        Music musicaFondo = Gdx.audio.newMusic(Gdx.files.internal("musicaFondo.mp3"));
        lluvia = new Lluvia(manzana, pieManzana, abuela, lobo, sonidoManzana, sonidoPie, musicaFondo);

        // creacion del tarro
        tarro.crear();

        // creacion de la lluvia
        lluvia.crear();
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
		font.draw(batch, "Gotas totales: " + tarro.getPuntos(), 5, 475);
		font.draw(batch, "Vidas : " + tarro.getVidas(), 670, 475);
		font.draw(batch, "HighScore : " + game.getHigherScore(), camera.viewportWidth/2-50, 475);
		
		if (!tarro.estaHerido()) {
			// movimiento del tarro desde teclado
	        tarro.actualizarMovimiento();        
			// caida de la lluvia 
	       if (!lluvia.actualizarMovimiento(tarro)) {
	    	  //actualizar HigherScore
	    	  if (game.getHigherScore()<tarro.getPuntos())
	    		  game.setHigherScore(tarro.getPuntos());  
	    	  //ir a la ventana de finde juego y destruir la actual
	    	  game.setScreen(new GameOverScreen(game));
	    	  dispose();
	       }
		}
		
		tarro.dibujar(batch);
		lluvia.dibujar(batch);
		
		batch.end();
	}

	@Override
	public void show() {
	  // continuar con sonido de lluvia
	  lluvia.continuar();
	}

	@Override
	public void pause() {
		lluvia.pausar();
		game.setScreen(new PausaScreen(game, this)); 
	}

	@Override
	public void dispose() {
      tarro.destruir();
      lluvia.destruir();
      fondo.dispose();
	}

}
