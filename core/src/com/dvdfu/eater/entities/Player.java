package com.dvdfu.eater.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.dvdfu.eater.handlers.Input;

public class Player extends CircleObject {
	public Player() {
		super(0, 0, 32);
	}

	public void update() {
		super.update();
		handleKeys();
		//setRadius(getRadius() + 1);
	}

	private void handleKeys() {
		if (Input.KeyDown(Input.ARROW_UP)) {
			y += radius / 4;
		}
		if (Input.KeyDown(Input.ARROW_DOWN)) {
			y -= radius / 4;
		}
		if (Input.KeyDown(Input.ARROW_RIGHT)) {
			x += radius / 4;
		}
		if (Input.KeyDown(Input.ARROW_LEFT)) {
			x -= radius / 4;
		}
	}

	public void render(ShapeRenderer sr) {
		sr.circle(x, y, radius);
	}
}
