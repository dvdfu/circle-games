package com.dvdfu.eater.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.dvdfu.eater.Eater;
import com.dvdfu.eater.Space;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1280*3/4;
		config.height = 720*3/4;
		new LwjglApplication(new Space(), config);
	}
}
