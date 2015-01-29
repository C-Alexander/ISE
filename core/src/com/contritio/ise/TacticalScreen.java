package com.contritio.ise;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.contritio.ise.engine.*;
import com.sksamuel.gwt.websockets.*;
import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;

/**
 * Created by Alexander on 25/01/2015.
 */
public class TacticalScreen extends GameState {
    private Vector2 backGroundSize;
    private String currentSystem = "Sol";
    Websocket client = new Websocket("ws://contritio.com:11984/");
    private int konamiStage = 0;


    public TacticalScreen() {
        super("TacticalScreen", new Texture("legacy/stars.jpg"));
        backGroundSize = new Vector2(background.getWidth(), background.getHeight());
        addList("GiantPlanets", 0);
        addList("Planets", 1);
        addList("DwarfPlanets", 2);
        addList("Asteroids", 3);
        addList("Ships", 4);
        addList("StarBases", 5);
        client.addListener(new Networking());
        client.open();
        //client.open();
    }
    @Override
    public void update(OrthographicCamera camera) {
        super.update(camera);
        konamiCode();
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
        GameObjectManager().findList("GiantPlanets").clear();
        GameObjectManager().findList("Planets").clear();
        GameObjectManager().findList("DwarfPlanets").clear();
        GameObjectManager().findList("Asteroids").clear();
        GameObjectManager().findList("Ships").clear();
        GameObjectManager().findList("StarBases").clear();
    }
    @Override
    public void newData(PacketData data) {
        super.newData(data);
        if (data.planetData != null) {
            GameObjectManager().findList("Planets").find(data.planetData.name).destroy();
            GameObjectManager().addObject(new Planet(data.planetData.name, data.planetData.texture, new Vector2(data.planetData.xPosition, data.planetData.yPosition)), "Planets");
            }
        if (data.solarSystemData != null) {
            clearSystem();
            currentSystem = data.solarSystemData.name;
            for (PlanetData planet : data.solarSystemData.planets) {
                GameObjectManager().addObject(new Planet(planet.name, planet.texture, new Vector2(planet.xPosition, planet.yPosition)), "Planets");
            }
        }
    }
    public void konamiCode() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && konamiStage <= 1) {
            konamiStage++;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && konamiStage >= 2 && konamiStage <= 3) {
            konamiStage++;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && (konamiStage == 4 || konamiStage == 6)) {
            konamiStage++;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && (konamiStage == 5 || konamiStage == 7)) {
            konamiStage++;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.B) && (konamiStage == 8)) {
            konamiStage++;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.A) && (konamiStage == 9)) {
            konamiStage = 0;
            Log.info("KonamiCode Detected. Entering System Development Mode.");
            Log.info("Welcome, to Magrathea. ");
            DevSystemScreen devSystemScreen = new DevSystemScreen();
            devSystemScreen.name = "Magrathea";
            GameManager.getInstance().addGameState(devSystemScreen);
            GameManager.getInstance().switchTo("Magrathea");
        }
    }
    }
