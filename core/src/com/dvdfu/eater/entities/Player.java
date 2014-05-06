package com.dvdfu.eater.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.dvdfu.eater.handlers.Input;

public class Player extends CircleObject {
	private float vy;
	private float vx;
	public Player() {
		super(0, 0, 32);
		vx = 0;
		vy = 0;
	}

	public void update() {
		super.update();
		handleKeys();
		x += vx;
		y += vy;
		if (Math.abs(vx) > 0.5) {
			vx *= 0.95;
		} else {
			vx = 0;
		}
		if (Math.abs(vy) > 0.5) {
			vy *= 0.95;
		} else {
			vy = 0;
		}
		//setRadius(getRadius() + 1);
	}

	private void handleKeys() {
		if (Input.KeyDown(Input.ARROW_UP) && vy < radius) {
			vy += radius / 32;
		}
		if (Input.KeyDown(Input.ARROW_DOWN) && vy > -radius) {
			vy -= radius / 32;
		}
		if (Input.KeyDown(Input.ARROW_RIGHT) && vx < radius) {
			vx += radius / 32;
		}
		if (Input.KeyDown(Input.ARROW_LEFT) && vx > -radius) {
			vx -= radius / 32;
		}
	}
	
	public void stop() {
		vy = 0;
		vx = 0;
	}

	public void render(ShapeRenderer sr) {
		sr.circle(x, y, radius);
	}
}
