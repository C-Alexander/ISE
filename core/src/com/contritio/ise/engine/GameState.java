package com.contritio.ise.engine;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.contritio.ise.PacketData;

/**
 * Created by Alexander on 22/06/2014.
 */
public class GameState {
    public String name;
    public Texture background;
    GameObjectManager gameObjectManager;

    public GameState(String name, Texture background) {
        this.name = name;
        this.background = background;
        gameObjectManager = new GameObjectManager();
    }

    public void update(OrthographicCamera camera) {
        GameObjectManager().update(camera);
    }

    public void draw(SpriteBatch batch) {
        batch.begin();
        batch.draw(background, 0, 0, 960, 640);
        GameObjectManager().draw(batch);
        batch.end();
    }

    public void setup() { GameObjectManager().setup(); }

    public void destroy() { GameObjectManager().destroy();}

    public void pause() { GameObjectManager().pause();}

    public void resume() { GameObjectManager().resume();}

    public void addObject(GameObject object) {
        GameObjectManager().addObject(object);
    }
    public void addObject(SpriteGameObject object) {
        GameObjectManager().addObject(object, "default");  Log.debug("Added object...");
    }
    public void addObject(GameObject object, String list) {
        GameObjectManager().addObject(object);
    }
    public void addObject(SpriteGameObject object, String list) {
        GameObjectManager().addObject(object, list);
    }
    public void addList(SpriteGameObjectList object) {
        GameObjectManager().addList(object);
    }
    public void addList(String name, int layer) {
        GameObjectManager().addList(new SpriteGameObjectList(name, layer));
    }

    public GameObjectManager GameObjectManager() { return gameObjectManager; }
    public void newData(PacketData data) {}
}
