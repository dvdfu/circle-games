package com.dvdfu.eater.handlers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class HUD {
	private Array<HUDObject> elements;
	private OrthographicCamera cam;
	
	public HUD() {
		elements = new Array<HUDObject>();
		cam = new OrthographicCamera();
	}
	
	public void render(SpriteBatch sb) {
		sb.begin();
		for (HUDObject i : elements) {
			i.render(sb, cam);
		}
		sb.end();
	}	
	public void update() {
		for (HUDObject i : elements) {
			i.update();
		}
	}
	
	public void addElement(HUDObject e) {
		elements.add(e);
	}
	
	public void setView(OrthographicCamera cam) {
		this.cam = cam;
	}
}
