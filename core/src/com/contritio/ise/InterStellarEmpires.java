package com.contritio.ise;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class InterStellarEmpires extends ApplicationAdapter {
	SpriteBatch batch;
	Texture backGround;
	private Vector2 backGroundSize;
	@Override
	public void create () {
		batch = new SpriteBatch();
		backGround = new Texture("legacy/stars.jpg");
		backGroundSize = new Vector2(backGround.getWidth(), backGround.getHeight()); //Yes, I know there's a wrap method with GL. But that means needing a second batch etc.
		//thisl do.

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //I cant freaking believe this still can not be transparent. I know WebGL supports it.
		//But I am not freaking forking LibGDX just to change that. Especially not because they're even more stubborn than I am. They refuse to let me pull request if I dont use Eclipse...
		batch.begin();
		for (int x = 0; x < Gdx.graphics.getWidth(); x += backGroundSize.x) {
			for (int y = 0; y < Gdx.graphics.getHeight(); y += backGroundSize.y) {
				batch.draw(backGround, x, y);
			}
		}
		batch.end();
	}
}
