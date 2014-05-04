package com.dvdfu.eater.handlers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

public class HUDCountable extends HUDImage {
	private int num;
	private float xMargin;
	//private float yMargin;
	private boolean xLoop;
	private boolean yLoop;

	public HUDCountable(TextureRegion reg, float x, float y, float xMargin, float yMargin) {
		this(new TextureRegion[] { reg }, x, y, xMargin, yMargin);
	}

	public HUDCountable(TextureRegion reg[], float x, float y, float xMargin, float yMargin) {
		super(reg, x, y);
		this.xMargin = xMargin;
		//this.yMargin = yMargin;
		num = 10;
		xLoop = false;
		yLoop = false;
	}

	public void setLoop(boolean xLoop, boolean yLoop) {
		this.xLoop = xLoop;
		this.yLoop = yLoop;
	}

	public void render(SpriteBatch sb, OrthographicCamera cam) {
		if (xLoop || yLoop) {
			for (int i = 0; i < MathUtils.ceil(Vars.SCREEN_WIDTH / width); i++) {
				for (int j = 0; j < MathUtils.ceil(Vars.SCREEN_HEIGHT / height); j++) {
					if (xLoop && yLoop) {
						sb.draw(image.getFrame(), i * width + cam.position.x - cam.viewportWidth / 2, j * height + cam.position.y - cam.viewportHeight / 2);
					} else if (xLoop) {
						sb.draw(image.getFrame(), i * width + cam.position.x - cam.viewportWidth / 2, y + cam.position.y - cam.viewportHeight / 2);
					} else {
						sb.draw(image.getFrame(), x + cam.position.x - cam.viewportWidth / 2, y * height + cam.position.y - cam.viewportHeight / 2);
					}
				}
			}
		} else {
			for (int i = 0; i < num; i++) {
				sb.draw(image.getFrame(), x + cam.position.x - cam.viewportWidth / 2 + i * (width + xMargin), y + cam.position.y - cam.viewportHeight / 2);
			}
		}
	}

	public void setDelay(float delay) {
		image.setDelay(delay);
	}

	public void setNum(int n) {
		num = n;
	}

	public int getNum() {
		return num;
	}
}
