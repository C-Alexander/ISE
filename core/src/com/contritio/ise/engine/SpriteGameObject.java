package com.contritio.ise.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

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
            Vector2 textPosition;
            Vector2 textSize;
            Vector2 scale;
            Vector3 clickLocation;
            public float rotation;
            boolean flipX;
            boolean flipY;
            public BitmapFont font;

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
        font = new BitmapFont();
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.size = new Vector2(sprite.getWidth(), sprite.getHeight());
        textPosition = new Vector2 (0, 0);
        textSize = new Vector2(0,0);
        this.origin = this.size;
        this.scale = new Vector2(1, 1); //Why did I set it to 2? Must have been some failed test...
        this.rotation = rotation;
    }

    @Override
    public void update(OrthographicCamera camera) {
        super.update(camera);
        velocity.add(acceleration); //adds a vector to another vector
        //Log.debug("Velocity of " + name + " is X:" + velocity.x + " Y:" + velocity.Y);
        position.add(velocity.cpy().scl(Gdx.graphics.getDeltaTime())); //Sc1 means Scalar. It basically means multiply the vector by a float
        if (Gdx.input.isTouched()) {
            clickLocation = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(clickLocation);
            if (pointCollision(clickLocation)) {
                if (Gdx.input.justTouched()) {
                    onClick(new Vector2(clickLocation.x, clickLocation.y)); //tbh, I should probably use circle collision for planets, but thisl also work for ships, asteroids and such so...
                } else {
                    whileClicked(new Vector2(clickLocation.x, clickLocation.y));
                }
            } else if (textCollision(clickLocation)) {
                    if (Gdx.input.justTouched()) {
                        onTextClick(new Vector2(clickLocation.x, clickLocation.y)); //tbh, I should probably use circle collision for planets, but thisl also work for ships, asteroids and such so...
                    } else {
                        whileTextClicked(new Vector2(clickLocation.x, clickLocation.y));
                    }
            }
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        if (sprite != null) {
            batch.draw(sprite, position.x, position.y, origin.x, origin.y, size.x * scale.y, size.y * scale.y, 1, 1, rotation, 0, 0, sprite.getTextureData().getWidth(), sprite.getTextureData().getHeight(), flipX, flipY);
        }
    }
    @Override
    public void destroy() {
        font.dispose();
    }
    public boolean pointCollision(int xPosition, int yPosition) {
        return (pointCollision(new Vector2(xPosition, yPosition)));
    }
    public boolean pointCollision(Vector3 position) {
        return (pointCollision(new Vector2(position.x, position.y)));
    }
    public boolean pointCollision(Vector2 position) {
        return (generalPointCollision(position, getPosition(), getSize()));
    }
    public boolean textCollision(int xPosition, int yPosition) {
        return (textCollision(new Vector2(xPosition, yPosition)));
    }
    public boolean textCollision(Vector3 position) {
        return (textCollision(new Vector2(position.x, position.y)));
    }
    public boolean textCollision(Vector2 position) {
        return generalPointCollision(position, textPosition, textSize);
    }
    private boolean generalPointCollision (Vector2 posToCheck, Vector2 posOfObject, Vector2 sizeToCheck) {
        return (posToCheck.x > posOfObject.x
                && posToCheck.x < posOfObject.x + sizeToCheck.x
                && posToCheck.y > posOfObject.y
                && posToCheck.y < posOfObject.y + sizeToCheck.y);
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
    public Vector2 getTextPosition() {
        return textPosition;
    }
    public void setTextPosition(Vector2 textPosition) {
        this.textPosition = textPosition;
    }
    public Vector2 getTextSize() {
        return textSize;
    }
    public void setTextSize(Vector2 textSize) {
        this.textSize = textSize;
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
    public void onClick(Vector2 clickLocation) {}
    public void whileClicked(Vector2 clickLocation) {}
    public void onTextClick(Vector2 clickLocation) {}
    public void whileTextClicked(Vector2 clickLocation) {}
}
