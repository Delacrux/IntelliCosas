package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.HashMap;

public class Controlador implements Dibujar{
	private Array<Rectangle> posElementos;
	private Array<Integer> tipoElementos;
    private HashMap<Integer, EstrategiaColision> estrategias;
    private long ultimaCreacion;
    private Texture manzana;
    private Texture pieManzana;
    private Texture abuela;
    private Texture lobo;
    private Music musicaFondo;

    //manzana, pieManzana, abuela, lobo, sonidoManzana, sonidoPie, musicaFondo
	public Controlador(Texture manzana, Texture pieManzana, Texture abuela, Texture lobo, Music mm) {
		musicaFondo = mm;
        estrategias = new HashMap<>();
        estrategias.put(1, new ColisionLobo());
        estrategias.put(2, new ColisionManzana());
        estrategias.put(3, new ColisionPie());
        estrategias.put(4, new ColisionAbuela());
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
        Rectangle elemento = new Rectangle();
        elemento.x = MathUtils.random(0, 800-64);
        elemento.y = 480;
        elemento.width = 64;
        elemento.height = 64;
        posElementos.add(elemento);
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

        ultimaCreacion = TimeUtils.nanoTime();
    }
	
   public boolean actualizarMovimiento(Cesta cesta) {
	   // generar elementos
	   if(TimeUtils.nanoTime() - ultimaCreacion > 100000000) crearGotaDeLluvia();
	  
	   
	   // revisar si los elementos cayeron al suelo
	   for (int i = 0; i < posElementos.size; i++ ) {
		  Rectangle elemento = posElementos.get(i);
	      elemento.y -= 300 * Gdx.graphics.getDeltaTime();
	      //cae al suelo y se elimina
	      if(elemento.y + 64 < 0) {
	    	  posElementos.removeIndex(i);
	    	  tipoElementos.removeIndex(i);
	      }
	      if(elemento.overlaps(cesta.getArea())) { // elemento choca con el tarro
              int tipo = tipoElementos.get(i);
              EstrategiaColision strat =  estrategias.get(tipo);
              if(strat != null) {
                  strat.colision(cesta);
                  if(tipo==1 && cesta.getVidas()<=0) {
                      return false; // si se queda sin vidas retorna falso /game over
                  }
              }
              posElementos.removeIndex(i);
              tipoElementos.removeIndex(i);
	      }
	   } 
	  return true; 
   }

    @Override
    public void dibujar(SpriteBatch batch) {
        for(int i = 0; i < posElementos.size; i++)
        {
            Rectangle elemento = posElementos.get(i);
            if(tipoElementos.get(i) == 1)
                batch.draw(lobo, elemento.x, elemento.y);
            else if(tipoElementos.get(i) == 2)
                batch.draw(manzana, elemento.x, elemento.y);
            else if(tipoElementos.get(i) == 3)
                batch.draw(pieManzana, elemento.x, elemento.y);
            else if(tipoElementos.get(i) == 4)
                batch.draw(abuela, elemento.x, elemento.y);
        }
    }
    public void destruir() {
      musicaFondo.stop();
   }
    public void pausar() {
	  musicaFondo.stop();
   }
    public void continuar() {
	  musicaFondo.play();
   }
   
}
