package com.contritio.ise;

/**
 * Created by Alexander on 25/01/2015.
 */

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.contritio.ise.engine.Log;
import com.contritio.ise.engine.SpriteGameObject;

public class Planet extends SpriteGameObject {

public Planet(String planetName, String spriteName, Vector2 position) {
    super(planetName, 0, new Texture(spriteName), position);
}

    @Override
    public void update(OrthographicCamera camera) {
      //  rotation += 0.1; it looks better when still. perhaps we should make cloud overlays that move?
        super.update(camera);
    }
    @Override
    public void onClick() {
        Log.debug("Clicked a planet!");
    }
}
