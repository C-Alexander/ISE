package com.contritio.ise;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Alexander on 30/01/2015.
 */
public class Planet extends Body {
    public Planet(String planetName, String spriteName, Vector2 position) {
        super(planetName, spriteName, position);
        font.setColor(Color.RED);
        font.setScale(1f);
        setTextPosition(getPosition().cpy().add(getSize().x, -getTextSize().y));
        setTextSize(new Vector2(font.getBounds(getName()).width, font.getBounds(getName()).height));
    }
}
