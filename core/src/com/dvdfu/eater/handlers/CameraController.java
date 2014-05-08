package com.dvdfu.eater.handlers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class CameraController {
	private OrthographicCamera cam;
	private int panRate;
	private int shake;
	private float zoomRate;
	private float zoom;
	private Vector2 position;
	private Vector2 distance;
	private Vector2 focus;

	public CameraController(float viewWidth, float viewHeight) {
		cam = new OrthographicCamera(viewWidth, viewHeight);
		cam.setToOrtho(false, viewWidth, viewHeight);
		cam.position.set(viewWidth / 2f, viewWidth / 2f, 0f);
		cam.update();
		panRate = 0;
		zoomRate = 1;
		zoom = 1;
		cam.zoom = 1;
		shake = 0;
		position = new Vector2(cam.position.x, cam.position.y);
		distance = new Vector2(0, 0);
		focus = new Vector2(0, 0);
	}

	public void panInstant(float x, float y) {
		focus.x = x;
		focus.y = y;
		cam.position.x = x;
		cam.position.y = y;
		distance.x = 0;
		distance.y = 0;
	}

	public void setPanRate(int panRate) {
		this.panRate = panRate;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void zoomInstant(float zoom) {
		this.zoom = zoom;
		cam.zoom = zoom;
	}

	public void setZoom(float zoom) {
		this.zoom = zoom;
	}

	public void setZoomRate(float zoomRate) {
		this.zoomRate = zoomRate;
	}

	public float getZoom() {
		return cam.zoom;
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
		if (panRate == 0 || distance.len() < 1f) {
			cam.position.x = focus.x;
			cam.position.y = focus.y;
		} else {
			cam.position.x -= distance.x / panRate;
			cam.position.y -= distance.y / panRate;
		}
		if (zoomRate > 1) {
			float dif = zoom - cam.zoom;
			cam.zoom += dif / zoomRate;
			if (Math.abs(dif) < 0.01) {
				cam.zoom = zoom;
			}
		}
		else {
			cam.zoom = zoom;
		}
		cam.update();
	}
}
