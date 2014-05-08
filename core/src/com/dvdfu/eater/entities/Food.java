package com.dvdfu.eater.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.dvdfu.eater.handlers.GameObject;

public class Food extends GameObject {
	protected float radius;
	protected Circle body;
	protected boolean dead;

	public Food(float x, float y, float radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		body = new Circle(x, y, radius);
		dead = false;
	}
	
	public void update() {
		body.set(x, y, radius);
	}

	public void render(ShapeRenderer sr) {
		sr.circle(x, y, radius);
	}
	
	public Circle getCircle() {
		 return body;
	}
	
	public float getArea() {
		return radius * radius;
	}
	
	public void setArea(float area) {
		setRadius((float) Math.sqrt(area));
	}
	
	public float getRadius() {
		return radius;
	}
	
	public void setRadius(float radius) {
		this.radius = radius;
	}
	
	public void kill() {
		dead = true;
	}
	
	public boolean isDead() {
		return dead;
	}
	
	public void shrink() {
		radius /= 10;
		x /= 10;
		y /= 10;
	}
}
