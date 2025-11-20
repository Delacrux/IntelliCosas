package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;


public class Cesta implements Dibujar{
    private Rectangle bucket;
    private Texture bucketImage;
    private Sound sonidoHerido;
    private Sound sonidoCurar;
    private Sound sonidoManzana;
    private Sound sonidoPie;
    private int vidas = 3;
    private int puntos = 0;
    private int velx = 400;
    private boolean herido = false;
    private int tiempoHeridoMax=50;
    private int tiempoHerido;
    private boolean curado = false;
    private int tiempoCuradoMax = 50;
    private int tiempoCurado;



    public Cesta(Texture tex, Sound sH, Sound sC, Sound sM, Sound sP) {
		   bucketImage = tex;
		   sonidoHerido = sH;
           sonidoCurar = sC;
           sonidoManzana = sM;
           sonidoPie = sP;
	   }

    public int getVidas() {
			return vidas;
		}

    public int getPuntos() {
        return puntos;
    }
    public Rectangle getArea() {
        return bucket;
    }
    public void sumarPuntos(int pp) {
        if(pp==10){
            sonidoManzana.play();
        }
        else{
            sonidoPie.play();
        }
        puntos+=pp;
    }


   public void crear() {
          bucket = new Rectangle();
          bucket.x = 800 / 2 - 64 / 2;
          bucket.y = 20;
          bucket.width = 64;
          bucket.height = 64;
   }

   public void dañar() {
      vidas--;
      herido = true;
      tiempoHerido=tiempoHeridoMax;
      sonidoHerido.play();
   }

   public void dibujar(SpriteBatch batch) {
       if (curado) { //Se encarga de dar efecto y de bajar el cooldown de curado.
           batch.setColor(0.5f, 1f, 0.5f, 1f); // lo ponemos de color verde claro
           batch.draw(bucketImage, bucket.x, bucket.y);
           batch.setColor(1f, 1f, 1f, 1f); // restaurar color normal
           tiempoCurado--;
           if (tiempoCurado <= 0) curado = false;
       }

       else if (herido) {//efecto cuando recibe daño. Reduce cooldown de daño
           batch.setColor(1f, 0.3f, 0.3f, 1f); // ponemos color rojo claro
           batch.draw(bucketImage, bucket.x, bucket.y + MathUtils.random(-5, 5));
           batch.setColor(1f, 1f, 1f, 1f); // restaurar color original
           tiempoHerido--;
           if (tiempoHerido <= 0) herido = false;
       }

       else { //Sin recibir daño ni curacion
           batch.draw(bucketImage, bucket.x, bucket.y);
       }
   }

   public void curar() {
       if (vidas < 3){
           vidas++;
           curado = true;
           tiempoCurado = tiempoCuradoMax;
           sonidoCurar.play();
       }
   }

   public void actualizarMovimiento() {
       // movimiento desde mouse/touch
       /*if(Gdx.input.isTouched()) {
              Vector3 touchPos = new Vector3();
              touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
              camera.unproject(touchPos);
              bucket.x = touchPos.x - 64 / 2;
        }*/
       //movimiento desde teclado
       if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) bucket.x -= velx * Gdx.graphics.getDeltaTime();
       if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) bucket.x += velx * Gdx.graphics.getDeltaTime();
       // que no se salga de los bordes izq y der
       if(bucket.x < 0) bucket.x = 0;
       if(bucket.x > 800 - 64) bucket.x = 800 - 64;
   }

	public void destruir() {
		    bucketImage.dispose();
	   }
	
   public boolean estaHerido() {
	   return herido;
   }
	   
}
