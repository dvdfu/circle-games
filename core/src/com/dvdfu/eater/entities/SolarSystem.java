package com.dvdfu.eater.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class SolarSystem {
	private Array<Planet> planets;
	
	public SolarSystem() {
		planets = new Array<Planet>();
		for (int i = 0; i < 2; i++) {
			float randx = MathUtils.random(-200, 200);
			float randy = MathUtils.random(-200, 200);
			float randr = MathUtils.random(16, 64);
			planets.add(new Planet(randx, randy, randr));
		}
	}
	
	public void update() {
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
		System.out.println(direction.len());
		float length = direction.len();
		float magnitude = p1.getMass() * p2.getMass() / length / length;
		return direction.nor().scl(magnitude);
	}
	
	public void render(ShapeRenderer sr) {
		for (Planet p : planets) {
			p.render(sr);
		}
	}
}
