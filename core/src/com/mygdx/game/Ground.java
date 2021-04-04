package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Ground {

    float xVelocity = 80;
    float width = 168, height = 30;
    float x = 0;

    Texture ground = new Texture("ground.png");

    public void draw(Batch batch) {
        batch.draw(ground, x, 0, width, height);
        batch.draw(ground, x + width, 0, width, height);
    }

    public void update(float deltaTime) {
        x -= xVelocity * deltaTime;
        if (x + width <= 0)
            x = 0;
    }

    public void setX(float x) {
        this.x = x;
    }
}
