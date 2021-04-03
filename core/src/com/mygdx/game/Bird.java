package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Bird {

    float gravity = -12;
    float yVelocity = 0;

    float width = 34, height = 24;
    float x, y;

    float angle = 0;

    Texture blueBird = new Texture("bluebird.png");
    TextureRegion[] sprite = {new TextureRegion(blueBird, 0, 0, 17, 12),
            new TextureRegion(blueBird, 17, 0, 17, 12),
            new TextureRegion(blueBird, 34, 0, 17, 12)};

    public Bird(float WORLD_WIDTH, float WORLD_HEIGHT) {
        this.x = WORLD_WIDTH / 7;
        this.y = (WORLD_HEIGHT - width) / 2;
    }

    public void draw(Batch batch) {

        if (yVelocity < -0.7 || GameScreen.state == State.START_SCREEN)
            batch.draw(sprite[0], x, y, 17, 12, width, height, 1, 1, angle);
        else if (yVelocity < 1)
            batch.draw(sprite[1], x, y, 17, 12, width, height, 1, 1, angle);
        else
            batch.draw(sprite[2], x, y, 17, 12, width, height, 1, 1, angle);
    }

    public void update(float deltaTime) {

        y += yVelocity;

        if (y > 60)
            yVelocity += gravity * deltaTime;
        else {
            yVelocity = 0;
            GameScreen.state = State.LOST_SCREEN;
        }

        if (GameScreen.state != State.LOST_SCREEN)
            angle = yVelocity / -gravity * 80;
    }

    public void jump() {
        yVelocity = 5.5f;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void setYVelocity(float yVelocity) {
        this.yVelocity = yVelocity;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
}
