package com.contritio.ise;

/**
 * Created by Alexander on 25/01/2015.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.contritio.ise.engine.GameManager;
import com.contritio.ise.engine.Log;
import com.contritio.ise.engine.SpriteGameObject;

public class Planet extends SpriteGameObject {
    boolean beingDragged = false;
public Planet(String planetName, String spriteName, Vector2 position) {
    super(planetName, 0, new Texture(spriteName), position);
    font.setColor(Color.RED);
    font.setScale(1f);
    setTextPosition(getPosition().cpy().add(getSize().x, -getTextSize().y));
    setTextSize(new Vector2(font.getBounds(getName()).width, font.getBounds(getName()).height));
}

    @Override
    public void update(OrthographicCamera camera) {
      //  rotation += 0.1; it looks better when still. perhaps we should make cloud overlays that move?
        super.update(camera);
        beingDragged(camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)));
    }
    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        font.draw(batch, getName(), getTextPosition().x, getTextPosition().y);
    }
    @Override
    public void onClick(Vector2 clickLocation) {
        Log.debug("Clicked a planet!");
    }
    @Override
    public void onTextClick(Vector2 clickLocation) {
        Log.debug("Clicked a planets text!");
    }
    @Override
    public void whileClicked(Vector2 clickLocation) {
        if (Gdx.input.isKeyPressed(Input.Keys.Q) && GameManager.getInstance().GameState().name.equalsIgnoreCase("Magrathea")) {
            beingDragged = true;
        }
    }
    public void beingDragged(Vector3 clickLocation) {
        if (!Gdx.input.isKeyPressed(Input.Keys.Q)) {
            beingDragged = false;
        } else {
            Vector2 clickLocation2 = new Vector2(clickLocation.x, clickLocation.y);
            setPosition(clickLocation2.cpy().sub(getSize().cpy().scl(0.5f)));
        }
    }
    @Override
    public void setPosition(Vector2 position) {
        super.setPosition(position);
        setTextPosition(getPosition().cpy().add(getSize().x, -getTextSize().y));
    }
}
