package com.mygdx.game;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.Gdx;


public class Recursos {
    private static Recursos instancia;
    private Texture fondoJuego;
    private Texture fondoMenu;
    private Texture fondoGameOver;
    private Texture manzana;
    private Texture abuela;
    private Texture lobo;
    private Texture pieManzana;
    private Texture cesta;
    private Sound sonidoAbuela;
    private Sound sonidoPie;
    private Sound sonidoManzana;
    private Sound sonidoLobo;
    private Music musicaFondo;
    private BitmapFont fuente;

    private Recursos(){
        fondoJuego = new Texture(Gdx.files.internal("fondo.png"));
        fondoMenu = new Texture("fondoiniciofin.jpeg");
        fondoGameOver = new Texture("fondoiniciofin.jpeg");
        manzana = new Texture(Gdx.files.internal("manzana.png"));
        sonidoManzana = Gdx.audio.newSound(Gdx.files.internal("atrapar.mp3"));
        pieManzana = new Texture(Gdx.files.internal("piemanzana.png"));
        sonidoPie = Gdx.audio.newSound(Gdx.files.internal("comer.mp3"));
        abuela = new Texture(Gdx.files.internal("awela.png"));
        sonidoAbuela = Gdx.audio.newSound(Gdx.files.internal("healing.mp3"));
        lobo = new Texture(Gdx.files.internal("lobo.png"));
        sonidoLobo = Gdx.audio.newSound(Gdx.files.internal("danio.mp3"));
        cesta = new Texture(Gdx.files.internal("canasto.png"));
        musicaFondo = Gdx.audio.newMusic(Gdx.files.internal("musicaFondo.mp3"));
        fuente = new BitmapFont();
    }

    public static Recursos getInstancia() {
        if (instancia == null) {
            instancia = new Recursos();
        }
        return instancia;
    }

    public Texture getFondoJuego() {
        return fondoJuego;
    }

    public Texture getFondoMenu() {
        return fondoMenu;
    }

    public Texture getFondoGameOver() {
        return fondoGameOver;
    }

    public Texture getManzana() {
        return manzana;
    }

    public Texture getAbuela() {
        return abuela;
    }

    public Texture getLobo() {
        return lobo;
    }

    public Texture getPieManzana() {
        return pieManzana;
    }

    public Texture getCesta() {
        return cesta;
    }

    public Sound getSonidoAbuela() {
        return sonidoAbuela;
    }

    public Sound getSonidoPie() {
        return sonidoPie;
    }

    public Sound getSonidoManzana() {
        return sonidoManzana;
    }

    public Sound getSonidoLobo() {
        return sonidoLobo;
    }

    public Music getMusicaFondo() {
        return musicaFondo;
    }

    public BitmapFont getFuente() {
        return fuente;
    }

    public void dispose() {
        fondoJuego.dispose();
        fondoMenu.dispose();
        fondoGameOver.dispose();
        manzana.dispose();
        abuela.dispose();
        lobo.dispose();
        pieManzana.dispose();
        cesta.dispose();
        sonidoAbuela.dispose();
        sonidoPie.dispose();
        sonidoManzana.dispose();
        sonidoLobo.dispose();
        musicaFondo.dispose();
        fuente.dispose();
    }

}
