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
		sun = new Planet(0, 0, 800);
		planets = new Array<Planet>();
		planets.add(sun);
		for (int i = 0; i < 5; i++) {
			float randDist = MathUtils.random(3000, 5000);
			float randAngle = MathUtils.random(MathUtils.PI * 2);
			float randr = MathUtils.random(80, 240);;
			Planet p = new Planet(randDist * MathUtils.cos(randAngle), randDist * MathUtils.sin(randAngle), randr);
			Vector2 velocity = new Vector2(MathUtils.cos(randAngle + MathUtils.PI / 2), MathUtils.sin(randAngle + MathUtils.PI / 2)).scl(20);
			p.setVelocity(velocity);
			planets.add(p);
		}
		size = 0;
	}

	public void update() {
		size = 0;
		Vector2[] forces = new Vector2[planets.size];
		for (int i = 0; i < planets.size; i++) {
			forces[i] = new Vector2(0, 0);
		}
		for (int i = 0; i < planets.size; i++) {
			for (int j = i + 1; j < planets.size; j++) {
				Vector2 force = forceOn1By2(planets.get(i), planets.get(j));
				forces[i].add(force);
				forces[j].add(force.scl(-1));
			}
		}
		for (int i = 0; i < planets.size; i++) {
			Planet p = planets.get(i);
			p.setForce(forces[i]);
			p.update();
			// if (p.getLocation().sub(sun.getLocation()).len() > 20000) {
			// p = null;
			// planets.removeIndex(i);
			// i--;
			// }
		}
	}
	
	public void addPlanet(float x, float y, float r) {
		Planet p = new Planet(x, y, r);
		p.setVelocity(new Vector2(20, 0));
		planets.add(p);
	}

	private Vector2 forceOn1By2(Planet p1, Planet p2) {
		Vector2 direction = p2.getLocation().sub(p1.getLocation());
		float length = direction.len();
		float magnitude = 0;
		if (length >= 1) {
			magnitude = p1.getMass() * p2.getMass() / length / length / length / 1000;
			if (Math.abs(length) > size) {
				size = Math.abs(length);
			}
		}
		return direction.scl(magnitude);
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
