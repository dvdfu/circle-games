package com.dvdfu.eater.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class SolarSystem {
	private Array<Planet> planets;
	private Planet sun;
	private float size;
	
	public SolarSystem() {
		sun = new Planet(0, 0, 512);
		planets = new Array<Planet>();
		planets.add(sun);
		for (int i = 0; i < 10; i++) {
			float randDist = MathUtils.random(1000, 3000);
			float randAngle = MathUtils.random(2 * MathUtils.PI);
			float randr = MathUtils.random(32, 128);
			Planet p = new Planet(randDist * MathUtils.cos(randAngle), randDist * MathUtils.sin(randAngle), randr);
			Vector2 velocity = new Vector2(randDist * MathUtils.cos(randAngle + MathUtils.PI / 2), randDist * MathUtils.sin(randAngle + MathUtils.PI / 2)).scl(1f / 100);
			p.setVelocity(velocity);
			planets.add(p);
		}
		size = 0;
	}
	
	public void update() {
		size = 0;
		Vector2[] forces = new Vector2[planets.size];
		for (int i = 0; i < forces.length; i++) {
			forces[i] = new Vector2(0, 0);
		}
		for (int i = 0; i < planets.size; i++) {
			Planet p1 = planets.get(i);
			for (int j = i + 1; j < planets.size; j++) {
				Planet p2 = planets.get(j);
				Vector2 force = forceOn1By2(p1, p2);
				forces[i].add(force);
				forces[j].add(force.scl(-1));
			}
		}

		for (int i = 0; i < forces.length; i++) {
			planets.get(i).setForce(forces[i]);
			planets.get(i).update();
		}
	}
	
	private Vector2 forceOn1By2(Planet p1, Planet p2) {
		Vector2 direction = p2.getLocation().sub(p1.getLocation());
		float length = direction.len();
		float magnitude = 0;
		if (length != 0) {
			magnitude = p1.getMass() * p2.getMass() / length / length;
			if (Math.abs(length) > size) {
				size = Math.abs(length);
			}
		}
		return direction.nor().scl(magnitude);
	}
	
	public void render(ShapeRenderer sr) {
		for (Planet p : planets) {
			p.render(sr);
		}
	}
	
	public Planet getSun() {
		return sun;
	}
	
	public float getSize() {
		return size;
	}
}
