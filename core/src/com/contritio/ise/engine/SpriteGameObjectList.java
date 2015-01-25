package com.contritio.ise.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Alexander on 22/06/2014.
 */
public class SpriteGameObjectList {
    String name;
    int layer;
    ArrayList<SpriteGameObject> gameObjects = new ArrayList<SpriteGameObject>();

    public SpriteGameObjectList(String name, int layer) {
        this.name = name;
        this.layer = layer;
    }
    public void sort() {
        Collections.sort(gameObjects, new Comparator<SpriteGameObject>() {
            @Override
            public int compare(SpriteGameObject gameObject, SpriteGameObject gameObject2) {
                return gameObject.layer - gameObject2.layer;
            }
        });
    }
    public SpriteGameObject find(String name) {
        for (SpriteGameObject e: gameObjects) {
            if (e.name.equalsIgnoreCase(name)) {
                return e;
            }
        }
        Log.warn("Could not find gameobject called: " + name);
        return new SpriteGameObject("error", 0);
    }
    public ArrayList<SpriteGameObject> getGameObjects() {
        return gameObjects;
    }
    public void setGameObjects(ArrayList<SpriteGameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }
    public int size() { return getGameObjects().size();}
    public SpriteGameObject get(int i) { return getGameObjects().get(i);}
}
