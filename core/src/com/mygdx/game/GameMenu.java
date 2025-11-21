package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

	public class GameMenu extends Game {

		private SpriteBatch batch;
		private BitmapFont font;
		private int higherScore;

		public void create() {
			batch = new SpriteBatch();
			font = Recursos.getInstancia().getFuente(); // use libGDX's default Arial font
			this.setScreen(new MainMenuScreen(this));
		}

		public void render() {
			super.render(); // important!
		}

		public void dispose() {
            batch.dispose();
            Recursos.getInstancia().dispose();
		}

		public SpriteBatch getBatch() {
			return batch;
		}

		public BitmapFont getFont() {
			return font;
		}

		public int getHigherScore() {
			return higherScore;
		}

		public void setHigherScore(int higherScore) {
			this.higherScore = higherScore;
		}

	}