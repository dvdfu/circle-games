package com.dvdfu.eater.handlers;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;

public class InputProcessor extends InputAdapter {
	
	public boolean mouseMoved(int x, int y) {
		Input.mouse.x = x;
		Input.mouse.y = y;
		return true;
	}
	
	public boolean touchDragged(int x, int y, int pointer) {
		Input.mouse.x = x;
		Input.mouse.y = y;
		Input.mouseClick = true;
		return true;
	}
	
	public boolean touchDown(int x, int y, int pointer, int button) {
		Input.mouse.x = x;
		Input.mouse.y = y;
		Input.mouseClick = true;
		return true;
	}
	
	public boolean touchUp(int x, int y, int pointer, int button) {
		Input.mouse.x = x;
		Input.mouse.y = y;
		Input.mouseClick = false;
		return true;
	}
	
	public boolean keyDown(int k) {
		switch (k) {
			case Keys.UP:
			Input.setKey(Input.ARROW_UP, true); break;
			case Keys.DOWN:
			Input.setKey(Input.ARROW_DOWN, true); break;
			case Keys.LEFT:
			Input.setKey(Input.ARROW_LEFT, true); break;
			case Keys.RIGHT:
			Input.setKey(Input.ARROW_RIGHT, true); break;
			case Keys.SPACE:
			Input.setKey(Input.SPACEBAR, true); break;
		}
		return true;
	}
	
	public boolean keyUp(int k) {
		switch (k) {
			case Keys.UP:
			Input.setKey(Input.ARROW_UP, false); break;
			case Keys.DOWN:
			Input.setKey(Input.ARROW_DOWN, false); break;
			case Keys.LEFT:
			Input.setKey(Input.ARROW_LEFT, false); break;
			case Keys.RIGHT:
			Input.setKey(Input.ARROW_RIGHT, false); break;
			case Keys.SPACE:
			Input.setKey(Input.SPACEBAR,  false); break;
		}
		return true;
	}
}