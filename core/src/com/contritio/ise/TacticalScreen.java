package com.contritio.ise;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.contritio.ise.engine.GameState;

/**
 * Created by Alexander on 25/01/2015.
 */
public class TacticalScreen extends GameState {
    private Vector2 backGroundSize;
    public TacticalScreen() {
        super("TacticalScreen", new Texture("legacy/stars.jpg"));
        backGroundSize = new Vector2(background.getWidth(), background.getHeight());
        addObject(new Planet("Earth", "legacy/Body/Earth.gif", new Vector2(10, 10)));
    }
    @Override
    public void draw(SpriteBatch batch) {
        batch.begin();
        for (int x = 0; x < Gdx.graphics.getWidth(); x += backGroundSize.x) {
            for (int y = 0; y < Gdx.graphics.getHeight(); y += backGroundSize.y) {
                batch.draw(background, x, y);
            }
        }
        GameObjectManager().draw(batch);
        batch.end();
    }
    }
