package com.dvdfu.eater.handlers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class HUDObject {
	protected float x;
	protected float y;

	public HUDObject(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public abstract void render(SpriteBatch sb, OrthographicCamera cam);

	public abstract void update();
}
