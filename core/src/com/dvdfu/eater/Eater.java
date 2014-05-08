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
import com.dvdfu.eater.handlers.CameraController;
import com.dvdfu.eater.handlers.Input;
import com.dvdfu.eater.handlers.InputProcessor;
import com.dvdfu.eater.handlers.Vars;

public class Eater extends ApplicationAdapter {
	private Player p;
	private Array<Food> food;
	private ShapeRenderer sr;
	private CameraController view;
	private OrthographicCamera cam;

	public void create() {
		Gdx.input.setInputProcessor(new InputProcessor());
		p = new Player();
		food = new Array<Food>();
		for (int i = 0; i < 100; i++) {
			food.add(new Food(MathUtils.random(-p.getRadius() * 100, p.getRadius() * 100), MathUtils.random(-p.getRadius() * 100, p.getRadius() * 100), MathUtils.random(16, 256)));
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
		view.setPanRate(20);
		view.setZoomRate(20);
		cam = view.getCam();
		sr.setProjectionMatrix(cam.combined);
		p.update();
		sr.begin(ShapeType.Line);
		sr.setColor(Color.WHITE);
		p.render(sr);
		for (Food co : food) {
			if (!co.isDead()) {
				if (co.getRadius() < p.getRadius()) {
					sr.setColor(Color.GREEN);
					if (co.getCircle().overlaps(p.getCircle())) {
						p.setArea(p.getArea() + co.getArea());
						co.kill();
					}
				} else {
					sr.setColor(new Color(1 - (p.getArea() / co.getArea()), p.getArea() / co.getArea(), 0, 1));
				}
				co.update();
				co.render(sr);
			} else {
				float newx = MathUtils.random(-p.getRadius() * 100, p.getRadius() * 100);
				float newy = MathUtils.random(-p.getRadius() * 100, p.getRadius() * 100);
				float newr = co.getRadius() * 10;
				while (newx - newr > cam.position.x - cam.viewportWidth / 2 &&
						newx + newr < cam.position.x + cam.viewportWidth / 2 &&
						newy - newr > cam.position.y - cam.viewportHeight / 2 &&
						newy + newr < cam.position.y + cam.viewportHeight / 2) {
					newx = MathUtils.random(-p.getRadius() * 100, p.getRadius() * 100);
					newy = MathUtils.random(-p.getRadius() * 100, p.getRadius() * 100);
				}
				food.add(new Food(newx, newy, newr));
				food.removeValue(co, false);
			}
		}
		sr.end();
		
		Input.update();
		if (p.getRadius() > 320) {
			view.panInstant(view.getPosition().x / 10, view.getPosition().y / 10);
			view.zoomInstant(view.getZoom() / 10);
			p.shrink();
			for (Food co : food) {
				co.shrink();
			}
		}
	}
}
