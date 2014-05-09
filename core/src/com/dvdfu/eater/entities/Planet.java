package com.dvdfu.eater.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.dvdfu.eater.handlers.CircleObject;

public class Planet extends CircleObject {
	private Vector2 acceleration;
	private Vector2 velocity;
	private float density;

	public Planet(float x, float y, float radius) {
		super(x, y, radius);
		density = 1;
		acceleration = new Vector2(0, 0);
		velocity = new Vector2(0, 0);
	}

	public void setForce(Vector2 force) {
		acceleration.set(force).scl(1 / getMass());
		// System.out.println(force.len() / getMass());
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	public void setDensity(float density) {
		this.density = density;
	}

	public float getMass() {
		return density * 4 / 3 * MathUtils.PI * radius * radius * radius;
	}

	public Vector2 getLocation() {
		return new Vector2(x, y);
	}

	public void update() {
		velocity.add(acceleration);
		x += velocity.x;
		y += velocity.y;
		super.update();
		// acceleration.set(0, 0);
	}

	public void render(ShapeRenderer sr) {
		super.render(sr);
		sr.begin(ShapeType.Line);
		sr.setColor(Color.GREEN);
		sr.line(x, y, x + velocity.x * 30, y + velocity.y * 30);
		sr.setColor(Color.CYAN);
		sr.line(x, y, x + acceleration.x * 3000, y + acceleration.y * 3000);
		sr.end();
	}
}
