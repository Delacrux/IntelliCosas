package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Controlador implements Dibujar{
	private Array<Rectangle> posElementos;
	private Array<Integer> tipoElementos;
    private long lastDropTime;
    private Texture manzana;
    private Texture pieManzana;
    private Texture abuela;
    private Texture lobo;
    private Sound sonidoManzana;
    private Sound sonidoPie;
    private Music musicaFondo;

    //manzana, pieManzana, abuela, lobo, sonidoManzana, sonidoPie, musicaFondo
	public Controlador(Texture manzana, Texture pieManzana, Texture abuela, Texture lobo, Sound sonidoManzana, Sound sonidoPie, Music mm) {
		musicaFondo = mm;
		this.sonidoManzana = sonidoManzana;
        this.sonidoPie = sonidoPie;
		this.manzana = manzana;
        this.abuela = abuela;
        this.pieManzana = pieManzana;
		this.lobo = lobo;
	}
	
	public void crear() {
		posElementos = new Array<Rectangle>();
		tipoElementos = new Array<Integer>();
		crearGotaDeLluvia();
	      // start the playback of the background music immediately
	      musicaFondo.setLooping(true);
	      musicaFondo.play();
	}
	
	private void crearGotaDeLluvia() {
        Rectangle raindrop = new Rectangle();
        raindrop.x = MathUtils.random(0, 800-64);
        raindrop.y = 480;
        raindrop.width = 64;
        raindrop.height = 64;
        posElementos.add(raindrop);
        // ver el tipo de elemento
        int p = MathUtils.random(1,100);
        if (p<=25)
            tipoElementos.add(1);
        else if(p<=75)
            tipoElementos.add(2);
        else if(p<=90)
            tipoElementos.add(3);
        else
            tipoElementos.add(4);

        lastDropTime = TimeUtils.nanoTime();
    }
	
   public boolean actualizarMovimiento(Cesta cesta) {
	   // generar gotas de lluvia 
	   if(TimeUtils.nanoTime() - lastDropTime > 100000000) crearGotaDeLluvia();
	  
	   
	   // revisar si las gotas cayeron al suelo o chocaron con el tarro
	   for (int i = 0; i < posElementos.size; i++ ) {
		  Rectangle raindrop = posElementos.get(i);
	      raindrop.y -= 300 * Gdx.graphics.getDeltaTime();
	      //cae al suelo y se elimina
	      if(raindrop.y + 64 < 0) {
	    	  posElementos.removeIndex(i);
	    	  tipoElementos.removeIndex(i);
	      }
	      if(raindrop.overlaps(cesta.getArea())) { //la gota choca con el tarro
	    	if(tipoElementos.get(i)==1) { // gota dañina
	    	  cesta.dañar();
	    	  if (cesta.getVidas()<=0)
	    		 return false; // si se queda sin vidas retorna falso /game over
	    	  posElementos.removeIndex(i);
	          tipoElementos.removeIndex(i);
	      	}

            else if(tipoElementos.get(i)==2){ // manzana a recolectar
	    	  cesta.sumarPuntos(10);
	          sonidoManzana.play();
	          posElementos.removeIndex(i);
	          tipoElementos.removeIndex(i);
	      	}

            else if(tipoElementos.get(i)==3){ //pie a recolectar
                cesta.sumarPuntos(25);
                sonidoPie.play();
                posElementos.removeIndex(i);
                tipoElementos.removeIndex(i);
            }

            else if(tipoElementos.get(i)==4){ //abuela
                cesta.curar();
                posElementos.removeIndex(i);
                tipoElementos.removeIndex(i);
            }
	      }
	   } 
	  return true; 
   }

    @Override
    public void dibujar(SpriteBatch batch) {
        for(int i = 0; i < posElementos.size; i++)
        {
            Rectangle raindrop = posElementos.get(i);
            if(tipoElementos.get(i) == 1)
                batch.draw(lobo, raindrop.x, raindrop.y);
            else if(tipoElementos.get(i) == 2)
                batch.draw(manzana, raindrop.x, raindrop.y);
            else if(tipoElementos.get(i) == 3)
                batch.draw(pieManzana, raindrop.x, raindrop.y);
            else if(tipoElementos.get(i) == 4)
                batch.draw(abuela, raindrop.x, raindrop.y);
        }
    }
    public void destruir() {
      sonidoManzana.dispose();
      musicaFondo.dispose();
      sonidoPie.dispose();
   }
    public void pausar() {
	  musicaFondo.stop();
   }
    public void continuar() {
	  musicaFondo.play();
   }
   
}
