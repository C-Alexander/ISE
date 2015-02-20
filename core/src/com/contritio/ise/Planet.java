package com.contritio.ise;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Alexander on 30/01/2015.
 */
public class Planet extends Body {
    public Planet(String planetName, String spriteName, Vector2 position) {
        super(planetName, spriteName, position);
        font.setColor(Color.RED);
    }
}
