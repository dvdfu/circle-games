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
import com.dvdfu.eater.entities.CircleObject;
import com.dvdfu.eater.entities.Player;
import com.dvdfu.eater.handlers.CameraController;
import com.dvdfu.eater.handlers.Input;
import com.dvdfu.eater.handlers.InputProcessor;
import com.dvdfu.eater.handlers.Vars;

public class Eater extends ApplicationAdapter {
	private Player p;
	private Array<CircleObject> food;
	private ShapeRenderer sr;
	private CameraController view;
	private OrthographicCamera cam;

	public void create() {
		Gdx.input.setInputProcessor(new InputProcessor());
		p = new Player();
		food = new Array<CircleObject>();
		for (int i = 0; i < 100; i++) {
			food.add(new CircleObject(MathUtils.random(-p.getRadius() * 100, p.getRadius() * 100), MathUtils.random(-p.getRadius() * 100, p.getRadius() * 100), MathUtils.random(4, 256)));
		}
		sr = new ShapeRenderer();
		view = new CameraController(Vars.SCREEN_WIDTH, Vars.SCREEN_HEIGHT);
		// view.setPan(20);
		cam = new OrthographicCamera();
	}

	public void render() {
		Gdx.graphics.setTitle("" + Gdx.graphics.getFramesPerSecond());
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		view.follow(p.getX(), p.getY());
		view.setZoom(p.getRadius() / 16);
		view.update();
		cam = view.getCam();
		sr.setProjectionMatrix(cam.combined);
		p.update();
		sr.begin(ShapeType.Line);
		sr.setColor(Color.WHITE);
		p.render(sr);
		for (CircleObject co : food) {
			if (!co.isDead()) {
				if (co.getRadius() < p.getRadius()) {
					sr.setColor(Color.GREEN);
					if (co.getCircle().overlaps(p.getCircle())) {
						p.setArea(p.getArea() + co.getArea());
						co.kill();
					}
				} else {
					sr.setColor(Color.RED);
				}
				co.update();
				co.render(sr);
			} else {
				food.add(new CircleObject(MathUtils.random(-p.getRadius() * 100, p.getRadius() * 100), MathUtils.random(-p.getRadius() * 100, p.getRadius() * 100), p.getRadius() * 4));
				food.removeValue(co, false);
			}
		}
		sr.end();
		Input.update();
		if (p.getRadius() > 320) {
			p.shrink();
			for (CircleObject co : food) {
				co.shrink();
			}
		}
	}
}
