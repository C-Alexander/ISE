package com.contritio.ise.engine;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Alexander on 22/06/2014.
 */
public class GameObjectManager {
    ArrayList<SpriteGameObjectList> spriteGameObjectLists = new ArrayList<SpriteGameObjectList>();
    private GameObjectList gameObjects;
    private SpriteGameObjectList normal;
    public GameObjectManager() {
        spriteGameObjectLists.add(new SpriteGameObjectList("default", 0));
        normal = spriteGameObjectLists.get(0);
        gameObjects = new GameObjectList("default", 0);
    }

    public SpriteGameObjectList findList(String name) {
        for (SpriteGameObjectList e: spriteGameObjectLists) {
            if (e.name.equalsIgnoreCase(name)) {
                return e;
            }
        }
        Log.warn("Could not find gameobjectlist called: " + name);
        return new SpriteGameObjectList("error", 0);
    }

    public void draw(SpriteBatch batch) {
        if (gameObjects.gameObjects.size() > 0) {
            for (GameObject e : gameObjects.gameObjects) {
                e.draw(batch);
            }
        }
        for (SpriteGameObjectList e: spriteGameObjectLists) {
            for (SpriteGameObject o: e.getGameObjects()) {
                o.draw(batch);
            }
        }
    }
    public void update(OrthographicCamera camera) {
        if (gameObjects.gameObjects.size() > 0) {
            for (GameObject e : gameObjects.gameObjects) {
                e.update(camera);
            }
        }
        for (SpriteGameObjectList e: spriteGameObjectLists) {
            for (SpriteGameObject o: e.getGameObjects()) {
                o.update(camera);
            }
        }
    }
    public void setup() {
        if (gameObjects.gameObjects.size() > 0) {
            for (GameObject e : gameObjects.gameObjects) {
                e.setup();
            }
        }
        for (SpriteGameObjectList e: spriteGameObjectLists) {
            for (SpriteGameObject o: e.getGameObjects()) {
                o.setup();
            }
        }
    }
    public void resume() {
        if (gameObjects.gameObjects.size() > 0) {
            for (GameObject e : gameObjects.gameObjects) {
                e.resume();
            }
        }
        for (SpriteGameObjectList e: spriteGameObjectLists) {
            for (SpriteGameObject o: e.getGameObjects()) {
                o.resume();
            }
        }
    }
    public void pause() {
        if (gameObjects.gameObjects.size() > 0) {
            for (GameObject e : gameObjects.gameObjects) {
                e.pause();
            }
        }
        for (SpriteGameObjectList e: spriteGameObjectLists) {
            for (SpriteGameObject o: e.getGameObjects()) {
                o.pause();
            }
        }
    }
    public void destroy() {
        if (gameObjects.gameObjects.size() > 0) {
            for (GameObject e : gameObjects.gameObjects) {
                e.destroy();
            }
        }
        for (SpriteGameObjectList e: spriteGameObjectLists) {
            for (SpriteGameObject o: e.getGameObjects()) {
                o.destroy();
            }
        }
    }

    public void addObject(GameObject object) {
        objectAdder(object);
    }
    public void addObject(SpriteGameObject object) {
        spriteObjectAdder(object, "default");
    }
    public void addObject(SpriteGameObject object, String list) {
        spriteObjectAdder(object, list);
    }
    private void spriteObjectAdder(SpriteGameObject object, String list) {
        findList(list).getGameObjects().add(object);
        findList(list).sort();
    }
    private void objectAdder(GameObject object) {
        gameObjects.gameObjects.add(object);
        gameObjects.sort();
    }

    public void addList(SpriteGameObjectList object) {
        listAdder(object);
    }
    public void addList(String name, int layer) {
        listAdder(new SpriteGameObjectList(name, layer));
    }
    private void listAdder(SpriteGameObjectList object) {
        spriteGameObjectLists.add(object);
        sort();
    }

    public boolean collidesWith(SpriteGameObject o1, SpriteGameObject o2) {
        Rectangle r1 = new Rectangle(o1.getPosition().x, o1.getPosition().y, o1.getSize().x, o1.getSize().y);
        Rectangle r2 = new Rectangle(o2.getPosition().x, o2.getPosition().y, o2.getSize().x, o2.getSize().y);
        return Intersector.overlaps(r1, r2);
    }

    public void sort() {
        Collections.sort(spriteGameObjectLists, new Comparator<SpriteGameObjectList>() {
            @Override
            public int compare(SpriteGameObjectList o, SpriteGameObjectList o2) {
                return o.layer - o2.layer;
            }
        });
    }

    public GameObjectList getGameObjects() {
        return gameObjects;
    }
    public SpriteGameObjectList getDefault() {
        return normal;
    }
}
