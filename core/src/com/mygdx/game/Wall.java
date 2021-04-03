package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Random;

public class Wall {

    Random rnd = new Random();

    float indent = 50;
    float xVelocity = 140;
    float emptySpace = 140;
    float width = 52, height = 640;
    float x, y = 160;

    public Wall(float x) {
        this.x = x;
    }

    Texture tubes = new Texture("tubes.png");
    TextureRegion[] sprite = {new TextureRegion(tubes, 0, 0, 26, 320),
            new TextureRegion(tubes, 26, 0, 26, 320)};

    public void draw(Batch batch) {
        batch.draw(sprite[1], x, y - height, width, height);
        batch.draw(sprite[0], x, y + emptySpace, width, height);
    }

    public void update(float deltaTime, int WORLD_WIDTH, int WORLD_HEIGHT) {
        x -= xVelocity * deltaTime;
        if (x + width <= 0) {
            x += WORLD_WIDTH * 1.5;
            generate(WORLD_HEIGHT);
        }
    }

    public void generate(int WORLD_HEIGHT){
        y = rnd.nextInt((int) (WORLD_HEIGHT - (indent * 2) - 60 - emptySpace));
        y += 60 + indent;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getEmptySpace() {
        return emptySpace;
    }

    public float getWidth() {
        return width;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
