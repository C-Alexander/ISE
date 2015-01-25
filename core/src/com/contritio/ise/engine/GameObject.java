package com.contritio.ise.engine;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Alexander on 22/06/2014.
 */
public class GameObject {
    String name;
    int layer;

    public GameObject(String name, int layer) {
        this.name = name; this.layer = layer;
    }

    public void draw(SpriteBatch batch) {}
    public void setup() {}
    public void resume() {}
    public void pause() {}
    public void update(OrthographicCamera camera) {}
    public void destroy() {}
    public String getName() {
        return name;
    }
}
