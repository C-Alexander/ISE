package com.contritio.ise.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.contritio.ise.InterStellarEmpires;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 860;
		config.height = 620;
		new LwjglApplication(new InterStellarEmpires(), config);
	}
}
