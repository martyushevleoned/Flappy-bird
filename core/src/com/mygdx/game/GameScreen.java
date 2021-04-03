package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen {

    //screen
    private final Camera camera;
    private final Viewport viewport;

    //graphics
    private final SpriteBatch batch;
    private Texture background;
    private final Texture tapToStart;

    //world parameters
    private final int WORLD_WIDTH = 288;
    private final int WORLD_HEIGHT = 512;

    //game objects
    Bird bird;
    Ground ground;
    Wall[] wall;
    public static State state;

    GameScreen() {

        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        background = new Texture("darkfon.png");
        tapToStart = new Texture("taptostart.png");
        batch = new SpriteBatch();

        bird = new Bird(WORLD_WIDTH, WORLD_HEIGHT);
        ground = new Ground();
        wall = new Wall[]{new Wall(WORLD_WIDTH), new Wall(WORLD_WIDTH * 1.75f)};

        wall[0].generate(WORLD_HEIGHT);
        wall[1].generate(WORLD_HEIGHT);

        state = State.START_SCREEN;
    }

    @Override
    public void render(float deltaTime) {
        batch.begin();

        //detect
        detectInput();
        detectCollision();

        if (state == State.GAMEPLAY) {
            //update
            wall[0].update(deltaTime, WORLD_WIDTH, WORLD_HEIGHT);
            wall[1].update(deltaTime, WORLD_WIDTH, WORLD_HEIGHT);
            ground.update(deltaTime);
            bird.update(deltaTime);

        } else if (state == State.FALL)
            bird.update(deltaTime);


        //draw
        batch.draw(background, 0, 0, WORLD_WIDTH, WORLD_HEIGHT);
        wall[0].draw(batch);
        wall[1].draw(batch);
        ground.draw(batch);
        bird.draw(batch);

        if (state == State.START_SCREEN)
            batch.draw(tapToStart, (WORLD_WIDTH - 114f) / 2, (WORLD_HEIGHT - 98f) / 2, 114, 98);


        batch.end();
    }

    private void detectInput() {
        if (Gdx.input.justTouched()) {

            if (state == State.START_SCREEN) {
                bird.jump();
                state = State.GAMEPLAY;
            } else if (state == State.GAMEPLAY) {
                bird.jump();
            } else if (state == State.LOST_SCREEN) {
                state = State.START_SCREEN;
                resetParameters();
            }
        }
    }

    private void detectCollision() {
        if (bird.getY() <= 60)
            state = State.FALL;

        if (wallCollision(bird.getX(), bird.getY()))
            state = State.FALL;

        if (wallCollision(bird.getX() + bird.getWidth(), bird.getY()))
            state = State.FALL;

        if (wallCollision(bird.getX(), bird.getY() + bird.getHeight()))
            state = State.FALL;

        if (wallCollision(bird.getX() + bird.getWidth(), bird.getY() + bird.getHeight()))
            state = State.FALL;
    }

    private boolean wallCollision(float x, float y) {
        for (int i = 0; i < 2; i++)
            if (x > wall[i].getX() + 5 && x < wall[i].getX() + wall[i].getWidth() - 5)
                if (y > wall[i].getY() + wall[i].getEmptySpace() || y < wall[i].getY())
                    return true;
        return false;
    }

    private void resetParameters() {
        //bird
        bird.setAngle(0);
        bird.setYVelocity(0);
        bird.setY((WORLD_HEIGHT - bird.getWidth()) / 2);

        //ground
        ground.setX(0);

        //wall
        wall[0].setX(WORLD_WIDTH);
        wall[1].setX(WORLD_WIDTH * 1.75f);
        wall[0].generate(WORLD_HEIGHT);
        wall[1].generate(WORLD_HEIGHT);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void show() {

    }
}
