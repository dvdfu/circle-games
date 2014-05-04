package com.dvdfu.eater.handlers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HUDText extends HUDObject {
	private BitmapFont font;
	private String text;

	public HUDText(String text, float x, float y) {
		super(x, y);
		this.text = text;
		font = new BitmapFont();
	}
	
	public void render(SpriteBatch sb, OrthographicCamera cam) {
		font.draw(sb, text, x + cam.position.x - cam.viewportWidth / 2, y + cam.position.y - cam.viewportHeight / 2);
	}
	
	public void setText(String text) {
		this.text = text;
	}

	public void update() {}
}
