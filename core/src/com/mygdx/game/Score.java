package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Score {

    int maxScore;
    int actualScore = 0;
    float width = 13, height = 18;
    float yGameOver = -21;

    Texture gameOverSprite = new Texture("gameover.png");
    TextureRegion gameOver = new TextureRegion(gameOverSprite, 0, 0, 96, 21);
    TextureRegion table = new TextureRegion(gameOverSprite, 0, 21, 113, 57);
    TextureRegion medal;

    Texture numbers = new Texture("numbers.png");
    TextureRegion[] num = {
            new TextureRegion(numbers, 0, 0, 14, 18),
            new TextureRegion(numbers, 14, 0, 14, 18),
            new TextureRegion(numbers, 14 * 2, 0, 14, 18),
            new TextureRegion(numbers, 14 * 3, 0, 14, 18),
            new TextureRegion(numbers, 14 * 4, 0, 14, 18),
            new TextureRegion(numbers, 14 * 5, 0, 14, 18),
            new TextureRegion(numbers, 14 * 6, 0, 14, 18),
            new TextureRegion(numbers, 14 * 7, 0, 14, 18),
            new TextureRegion(numbers, 14 * 8, 0, 14, 18),
            new TextureRegion(numbers, 14 * 9, 0, 14, 18)};

    Texture smallNumbers = new Texture("smallnumbers.png");
    TextureRegion[] smallNum = {
            new TextureRegion(smallNumbers, 0, 0, 8, 7),
            new TextureRegion(smallNumbers, 8, 0, 8, 7),
            new TextureRegion(smallNumbers, 8 * 2, 0, 8, 7),
            new TextureRegion(smallNumbers, 8 * 3, 0, 8, 7),
            new TextureRegion(smallNumbers, 8 * 4, 0, 8, 7),
            new TextureRegion(smallNumbers, 8 * 5, 0, 8, 7),
            new TextureRegion(smallNumbers, 8 * 6, 0, 8, 7),
            new TextureRegion(smallNumbers, 8 * 7, 0, 8, 7),
            new TextureRegion(smallNumbers, 8 * 8, 0, 8, 7),
            new TextureRegion(smallNumbers, 8 * 9, 0, 8, 7)};

    public void drawScore(Batch batch, int WORLD_WIDTH, int WORLD_HEIGHT) {

        if (actualScore < 10)
            batch.draw(num[actualScore], (WORLD_WIDTH - width) / 2, WORLD_HEIGHT - height * 2, width, height);
        else if (actualScore < 100) {
            batch.draw(num[actualScore / 10], WORLD_WIDTH / 2f - width, WORLD_HEIGHT - height * 2, width, height);
            batch.draw(num[actualScore % 10], WORLD_WIDTH / 2f, WORLD_HEIGHT - height * 2, width, height);
        } else if (actualScore < 1000) {
            batch.draw(num[actualScore / 100], (WORLD_WIDTH - width) / 2 - width, WORLD_HEIGHT - height * 2, width, height);
            batch.draw(num[actualScore % 100 / 10], (WORLD_WIDTH - width) / 2, WORLD_HEIGHT - height * 2, width, height);
            batch.draw(num[actualScore % 10], (WORLD_WIDTH - width) / 2 + width, WORLD_HEIGHT - height * 2, width, height);
        }
    }

    public void drawResults(Batch batch, int WORLD_WIDTH) {

        batch.draw(gameOver, (WORLD_WIDTH - 96) / 2f, yGameOver, 96, 21);
        batch.draw(table, (WORLD_WIDTH - 113) / 2f, yGameOver - 57 - 5, 113, 57);

        if (maxScore < 10)
            batch.draw(smallNum[maxScore], (WORLD_WIDTH - 113) / 2f + 90 + 5, yGameOver - 57 + 8, 8, 7);
        else if (maxScore < 100) {
            batch.draw(smallNum[maxScore / 10], (WORLD_WIDTH - 113) / 2f + 90 - 2, yGameOver - 57 + 8, 8, 7);
            batch.draw(smallNum[maxScore % 10], (WORLD_WIDTH - 113) / 2f + 90 + 5, yGameOver - 57 + 8, 8, 7);
        } else if (maxScore < 1000) {
            batch.draw(smallNum[maxScore / 100], (WORLD_WIDTH - 113) / 2f + 90 - 9, yGameOver - 57 + 8, 8, 7);
            batch.draw(smallNum[maxScore % 100 / 10], (WORLD_WIDTH - 113) / 2f + 90 - 2, yGameOver - 57 + 8, 8, 7);
            batch.draw(smallNum[maxScore % 10], (WORLD_WIDTH - 113) / 2f + 90 + 5, yGameOver - 57 + 8, 8, 7);
        }

        if (actualScore < 10)
            batch.draw(smallNum[actualScore], (WORLD_WIDTH - 113) / 2f + 90 + 5, yGameOver - 57 + 29, 8, 7);
        else if (actualScore < 100) {
            batch.draw(smallNum[actualScore / 10], (WORLD_WIDTH - 113) / 2f + 90 - 2, yGameOver - 57 + 29, 8, 7);
            batch.draw(smallNum[actualScore % 10], (WORLD_WIDTH - 113) / 2f + 90 + 5, yGameOver - 57 + 29, 8, 7);
        } else if (actualScore < 1000) {
            batch.draw(smallNum[actualScore / 100], (WORLD_WIDTH - 113) / 2f + 90 - 9, yGameOver - 57 + 29, 8, 7);
            batch.draw(smallNum[actualScore % 100 / 10], (WORLD_WIDTH - 113) / 2f + 90 - 2, yGameOver - 57 + 29, 8, 7);
            batch.draw(smallNum[actualScore % 10], (WORLD_WIDTH - 113) / 2f + 90 + 5, yGameOver - 57 + 29, 8, 7);
        }


        if (actualScore > maxScore * 2 / 3)
            medal = new TextureRegion(gameOverSprite, 46, 79, 22, 22);
        else if (actualScore > maxScore / 3)
            medal = new TextureRegion(gameOverSprite, 23, 79, 22, 22);
        else
            medal = new TextureRegion(gameOverSprite, 0, 79, 22, 22);

        batch.draw(medal, (WORLD_WIDTH - 113) / 2f + 13, yGameOver - 57 - 5 + 14, 22, 22);
    }

    public void update(float deltaTime, int WORLD_HEIGHT) {
        yGameOver += 70 * deltaTime;
        if (yGameOver >= WORLD_HEIGHT / 2f)
            GameScreen.state = State.LOST_SCREEN;
    }

    public int getActualScore() {
        return actualScore;
    }

    public void setActualScore(int actualScore) {
        this.actualScore = actualScore;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public void setYGameOver(float yGameOver) {
        this.yGameOver = yGameOver;
    }
}
