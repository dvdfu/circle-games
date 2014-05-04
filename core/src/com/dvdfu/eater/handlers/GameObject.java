package com.dvdfu.eater.handlers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class GameObject {
	protected Sprite sprite;
	protected Rectangle body;
	protected float x;
	protected float y;
	protected float width;
	protected float height;
	protected float xOffset;
	protected float yOffset;
	protected float spriteWidth;
	protected float spriteHeight;

	public GameObject() {
		this(0, 0, 0, 0);
	}

	public GameObject(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		body = new Rectangle(x, y, width, height);
		xOffset = 0;
		yOffset = 0;
		spriteWidth = 0;
		spriteHeight = 0;
		sprite = new Sprite();
	}

	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void setOffset(float xOffset, float yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public void setBody(float width, float height) {
		this.width = width;
		this.height = height;
	}

	public Rectangle getBody() {
		return body;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public Sprite getSprite() {
		return sprite;
	}
	
	public void update() {
		sprite.update();
		body.x = x;
		body.y = y;
	}

	public void render(SpriteBatch sb) {
		if (sprite.exists()) {
			sb.begin();
			sb.draw(sprite.getFrame(), x + xOffset, y + yOffset);
			sb.end();
		}
	}

	public void render(ShapeRenderer sr) {
		sr.begin(ShapeType.Line);
		sr.setColor(1, 0, 0, 1);
		sr.rect(x, y, width, height);
		if (sprite.exists()) {
			sr.setColor(0, 1, 0, 1);
			sr.rect(x + xOffset, y + yOffset, spriteWidth, spriteHeight);
		}
		sr.end();
	}
}
