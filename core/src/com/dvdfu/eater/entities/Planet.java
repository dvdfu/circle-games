package com.dvdfu.eater.entities;

import com.badlogic.gdx.math.Vector2;
import com.dvdfu.eater.handlers.CircleObject;
import com.dvdfu.eater.handlers.Vars;

public class Planet extends CircleObject {
	private Vector2 acceleration;
	private Vector2 velocity;
	private float density;
	private float mass;

	public Planet(float x, float y, float radius) {
		super(x, y, radius);
		density = 1;
		mass = density * radius * radius;
		acceleration = new Vector2(0, 0);
		velocity = new Vector2(0, 0);
	}
	
	public void setForce(Vector2 force) {
		acceleration.set(force).scl(1 / mass);
	}
	
	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}
	
	public void setDensity(float density) {
		this.density = density;
		mass = density * radius * radius;
	}
	
	public float getMass() {
		return mass;
	}
	
	public Vector2 getLocation() {
		return new Vector2(x, y);
	}
	
	public void update() {
		velocity.add(acceleration.x * Vars.SPF, acceleration.y * Vars.SPF);
		x += velocity.x * Vars.SPF;
		y += velocity.y * Vars.SPF;
		super.update();
	}
}
