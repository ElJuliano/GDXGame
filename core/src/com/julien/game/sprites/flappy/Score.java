package com.julien.game.sprites.flappy;

        import com.badlogic.gdx.graphics.g2d.BitmapFont;
        import com.julien.game.states.PlayState;

/**
 * Created by Julien on 15/01/2017.
 */

public class Score {

    // Adding a score feature

    private float posToIncScore = PlayState.TUBE_POS + Tube.TUBE_WIDTH;
    private int score = 0;
    private BitmapFont font;
    private float fontPositionX;
    private float fontPositionY;

    public Score(float scorePosX, float scorePosY) {
        // Score initialization
        font = new BitmapFont();
        font.getData().setScale(2, 2);
        font.setColor(com.badlogic.gdx.graphics.Color.BLACK);
        fontPositionX = scorePosX;//cam.position.x;
        fontPositionY = scorePosY;//MyGame.HEIGHT - 200;
    }

    public void updateScore(Bird bird, float newScorePositionX) {
        //Score management
        if (bird.getPosition().x > posToIncScore) {
            // When the position of the bird is superior to the current tube position then we increment the
            // tube position and the score.
            posToIncScore += PlayState.TUBE_POS;
            score++;
            // Play the bird sucess sound
            bird.playSuccessSound();
        }
        // Updating score position
        fontPositionX = newScorePositionX;
    }

    public int getScore() {
        return score;
    }

    public String toString(){
        return String.valueOf(score);
    }

    public float getFontPositionY() {
        return fontPositionY;
    }

    public float getFontPositionX() {
        return fontPositionX;
    }

    public BitmapFont getFont() {
        return this.font;
    }

}
