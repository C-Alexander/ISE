package com.contritio.ise.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Alexander on 22/06/2014.
 */
public class GameObjectList {
    String name;
    int layer;
    ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();

    public GameObjectList(String name, int layer) {
        this.name = name;
        this.layer = layer;
    }
    public void sort() {
        Collections.sort(gameObjects, new Comparator<GameObject>() {
            @Override
            public int compare(GameObject gameObject, GameObject gameObject2) {
                return gameObject.layer - gameObject2.layer;
            }
        });
    }
    public GameObject find(String name) {
        for (GameObject e: gameObjects) {
            if (e.name.equalsIgnoreCase(name)) {
                return e;
            }
        }
        Log.warn("Could not find gameobject called: " + name);
        return new GameObject("error", 0);
    }
    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }
    public void setGameObjects(ArrayList<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }
    public int size() { return getGameObjects().size();}
    public GameObject get(int i) { return getGameObjects().get(i);}
}
