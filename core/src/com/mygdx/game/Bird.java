package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Bird {

    float gravity = -7;
    float yVelocity = 0;

    float width = 17, height = 12;
    float x, y;

    float angle = 0;

    Texture blueBird = new Texture("bluebird.png");
    TextureRegion[] sprite = {
            new TextureRegion(blueBird, 0, 0, 17, 12),
            new TextureRegion(blueBird, 17, 0, 17, 12),
            new TextureRegion(blueBird, 34, 0, 17, 12)};

    public Bird(float WORLD_WIDTH, float WORLD_HEIGHT) {
        this.x = WORLD_WIDTH / 7;
        this.y = (WORLD_HEIGHT - height) / 2;
    }

    public void draw(Batch batch) {

        if (yVelocity < -0.3 || GameScreen.state == State.START_SCREEN || GameScreen.state == State.RESULTS || GameScreen.state == State.LOST_SCREEN)
            batch.draw(sprite[0], x, y, 9, 6, width, height, 1, 1, angle);
        else if (yVelocity < 0.4)
            batch.draw(sprite[1], x, y, 9, 6, width, height, 1, 1, angle);
        else
            batch.draw(sprite[2], x, y, 9, 6, width, height, 1, 1, angle);
    }

    public void update(float deltaTime) {

        y += yVelocity;

        if (y > 30)
            yVelocity += gravity * deltaTime;
        else {
            yVelocity = 0;
            GameScreen.state = State.RESULTS;
            if (GameScreen.score.getActualScore() > GameScreen.score.getMaxScore()) {
                GameScreen.score.setMaxScore(GameScreen.score.getActualScore());

                GameScreen.pref.putInteger("key", GameScreen.score.getActualScore());
                GameScreen.pref.flush();
            }
        }

        if (GameScreen.state != State.LOST_SCREEN && GameScreen.state != State.RESULTS)
            angle = yVelocity / -gravity * 80;
    }

    public void jump() {
        yVelocity = 2.7f;
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
