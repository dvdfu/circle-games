package com.dvdfu.eater.handlers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class CameraController {
	private OrthographicCamera cam;
	private int pan;
	private int shake;
	private float zoom;
	private Vector2 position;
	private Vector2 distance;
	private Vector2 focus;

	public CameraController(float viewWidth, float viewHeight) {
		cam = new OrthographicCamera(viewWidth, viewHeight);
		cam.setToOrtho(false, viewWidth, viewHeight);
		cam.position.set(viewWidth / 2f, viewWidth / 2f, 0f);
		cam.update();
		pan = 0;
		zoom = 1;
		cam.zoom = 1f;
		shake = 0;
		position = new Vector2(cam.position.x, cam.position.y);
		distance = new Vector2(0, 0);
		focus = new Vector2(0, 0);
	}

	public void setPan(int pan) {
		this.pan = pan;
	}

	public void setZoom(float zoom) {
		cam.zoom = zoom;
	}

	public void setShake(int shake) {
		this.shake = shake;
	}

	public OrthographicCamera getCam() {
		return cam;
	}

	public void follow(float x, float y) {
		focus.x = x;
		focus.y = y;
	}

	public void update() {
		if (shake > 0) {
			position.set(cam.position.x + MathUtils.random(-shake, shake), cam.position.y + MathUtils.random(-shake, shake));
		} else {
			position.set(cam.position.x, cam.position.y);
		}
		distance = position.sub(focus);
		if (pan == 0) {
			cam.position.x = focus.x;
			cam.position.y = focus.y;
		} else {
			if (distance.len() < 1f) {
				cam.position.x = focus.x;
				cam.position.y = focus.y;
			} else {
				cam.position.x -= distance.x / pan;
				cam.position.y -= distance.y / pan;
			}
		}
		cam.update();
	}
}
