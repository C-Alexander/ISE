package com.contritio.ise;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.contritio.ise.engine.GameObject;
import com.contritio.ise.engine.GameState;
import com.sksamuel.gwt.websockets.*;

/**
 * Created by Alexander on 25/01/2015.
 */
public class TacticalScreen extends GameState {
    private Vector2 backGroundSize;
    private String currentSystem = "Sol";


    public TacticalScreen() {
        super("TacticalScreen", new Texture("legacy/stars.jpg"));
        backGroundSize = new Vector2(background.getWidth(), background.getHeight());
        addList("GiantPlanets", 0);
        addList("Planets", 1);
        addList("DwarfPlanets", 2);
        addList("Asteroids", 3);
        addList("Ships", 4);
        addList("StarBases", 5);
        Websocket client = new Websocket("ws://contritio.com:19840/");
        client.addListener(new Networking());
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
    public void clearSystem() {
        GameObjectManager().find("GiantPlanets").clear();
        GameObjectManager().find("Planets").clear();
        GameObjectManager().find("DwarfPlanets").clear();
        GameObjectManager().find("Asteroids").clear();
        GameObjectManager().find("Ships").clear();
        GameObjectManager().find("StarBases").clear();

    }
    }
