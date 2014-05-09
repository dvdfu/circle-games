package com.dvdfu.eater;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.dvdfu.eater.entities.Food;
import com.dvdfu.eater.entities.Player;
import com.dvdfu.eater.entities.SolarSystem;
import com.dvdfu.eater.handlers.CameraController;
import com.dvdfu.eater.handlers.Input;
import com.dvdfu.eater.handlers.InputProcessor;
import com.dvdfu.eater.handlers.Vars;

public class Space extends ApplicationAdapter {
	private ShapeRenderer sr;
	private CameraController view;
	private OrthographicCamera cam;
	private SolarSystem ss;

	public void create() {
		Gdx.input.setInputProcessor(new InputProcessor());
		sr = new ShapeRenderer();
		view = new CameraController(Vars.SCREEN_WIDTH * 3, Vars.SCREEN_HEIGHT * 3);
		cam = new OrthographicCamera();
		ss = new SolarSystem();
	}

	public void render() {
		Gdx.graphics.setTitle("" + Gdx.graphics.getFramesPerSecond());
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		view.follow(ss.getSun().getX(), ss.getSun().getY());
		view.setZoom(10);
		view.update();
		cam = view.getCam();
		sr.setProjectionMatrix(cam.combined);
		ss.update();
		ss.render(sr);
		if (Input.MousePressed()) {
			ss.addPlanet(cam.position.x + Input.mouse.x * view.getZoom(), cam.position.y - Input.mouse.y * view.getZoom(), 128);
		}
		Input.update();
	}
}
