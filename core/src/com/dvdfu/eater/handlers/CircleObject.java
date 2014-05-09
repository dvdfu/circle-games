package com.dvdfu.eater.handlers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;

public class CircleObject {
	protected Sprite sprite;
	protected Circle body;
	protected float x;
	protected float y;
	protected float radius;

	public CircleObject() {
		this(0, 0, 0);
	}

	public CircleObject(float x, float y, float radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		body = new Circle(x, y, radius);
		sprite = new Sprite();
	}

	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public Circle getBody() {
		return body;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getRadius() {
		return radius;
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
			sb.draw(sprite.getFrame(), x, y);
			sb.end();
		}
	}

	public void render(ShapeRenderer sr) {
		sr.begin(ShapeType.Line);
		sr.setColor(1, 0, 0, 1);
		sr.circle(x, y, radius);
		sr.end();
	}
}
