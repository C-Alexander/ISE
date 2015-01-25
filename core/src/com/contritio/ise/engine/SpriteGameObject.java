package com.contritio.ise.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Alexander on 22/06/2014.
 */
public class SpriteGameObject extends GameObject {
            Texture sprite;
            Vector2 position;
            Vector2 velocity;
            Vector2 acceleration;
            Vector2 origin;
            Vector2 size;
            Vector2 scale;
            public float rotation;
            boolean flipX;
            boolean flipY;

            public SpriteGameObject(String name, int layer) {
                super(name, layer);
                construct(null, Vector2.Zero, Vector2.Zero, Vector2.Zero, 0);
            }
            public SpriteGameObject(String name, int layer, Texture sprite) {
                super(name, layer);
                construct(sprite, Vector2.Zero, Vector2.Zero, Vector2.Zero, 0);
            }
            public SpriteGameObject(String name, int layer, Texture sprite, Vector2 position) {
                super(name, layer);
                construct(sprite, position, Vector2.Zero, Vector2.Zero, 0);
            }
            public SpriteGameObject(String name, int layer, Texture sprite, Vector2 position, Vector2 velocity) {
                super(name, layer);
                construct(sprite, position, velocity, Vector2.Zero, 0);
            }
            public SpriteGameObject(String name, int layer, Texture sprite, Vector2 position, Vector2 velocity, Vector2 acceleration) {
                super(name, layer);
        construct(sprite, position, velocity, acceleration, 0);
    }
    public SpriteGameObject(String name, int layer, Texture sprite, Vector2 position, Vector2 velocity, Vector2 acceleration, float rotation) {
        super(name, layer);
        construct(sprite, position, velocity, acceleration, rotation);
    }
    void construct(Texture sprite, Vector2 position, Vector2 velocity, Vector2 acceleration, float rotation) {
        if (sprite != null) {
            this.sprite = sprite;
        }
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.size = new Vector2(sprite.getWidth(), sprite.getHeight());
        this.origin = this.size;
        this.scale = new Vector2(2, 2);
        this.rotation = rotation;
    }

    @Override
    public void update() {
        super.update();
        velocity.add(acceleration); //adds a vector to another vector
        //Log.debug("Velocity of " + name + " is X:" + velocity.x + " Y:" + velocity.Y);
        position.add(velocity.cpy().scl(Gdx.graphics.getDeltaTime())); //Sc1 means Scalar. It basically means multiply the vector by a float
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        if (sprite != null) {
            batch.draw(sprite, position.x, position.y, origin.x, origin.y, size.x * scale.y, size.y * scale.y, 1, 1, rotation, 0, 0, sprite.getTextureData().getWidth(), sprite.getTextureData().getHeight(), flipX, flipY);
        }
    }

    public Vector2 getVelocity() {
        return velocity;
    }
    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }
    public Vector2 getAcceleration() {
        return acceleration;
    }
    public void setAcceleration(Vector2 acceleration) {
        this.acceleration = acceleration;
    }
    public Vector2 getPosition() {
        return position;
    }
    public void setPosition(Vector2 position) {
        this.position = position;
    }
    public Texture getSprite() {
        return sprite;
    }
    public void setSprite(Texture sprite) {
        this.sprite = sprite;
    }
    public Vector2 getOrigin() {
        return origin;
    }
    public void setOrigin(Vector2 origin) {
        this.origin = origin;
    }
    public Vector2 getSize() {
        return size.cpy().scl(scale);
    }
    public void setSize(Vector2 size) {
        this.size = size;
    }
    public float getRotation() {
        return rotation;
    }
    public void setRotation(float rotation) {
        this.rotation = rotation;
    }
    public boolean isFlipX() {
        return flipX;
    }
    public void setFlipX(boolean flipX) {
        this.flipX = flipX;
    }
    public boolean isFlipY() {
        return flipY;
    }
    public void setFlipY(boolean flipY) {
        this.flipY = flipY;
    }
    public Vector2 getScale() {
        return scale;
    }
    public void setScale(Vector2 scale) {
        this.scale = scale;
    }
}
