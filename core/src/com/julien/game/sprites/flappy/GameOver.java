package com.julien.game.sprites.flappy;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.julien.game.states.GameStateManager;
import com.julien.game.states.PlayState;

/**
 * Created by Julien on 22/01/2017.
 */

public class GameOver {

    private static String GAME_OVER = "Game Over";
    private static String PLAY_AGAIN = "Play Again";

    private BitmapFont font;
    private float fontPositionX;
    private float fontPositionY;

    private TextButton button;
    private TextButton.TextButtonStyle buttonStyle;
    private float buttonX;
    private float buttonY;

    private GlyphLayout fontLayout;
    private Button btn;

    private GameStateManager gsm;

        public GameOver(GameStateManager stateMngr, float goX, float goY) {
            font = new BitmapFont();
            buttonX = goX;//cam.position.x;
            buttonY = goY;//MyGame.HEIGHT - 200;
            font.getData().setScale(3, 3);
            font.setColor(com.badlogic.gdx.graphics.Color.BLACK);
            fontLayout = new GlyphLayout(font, GAME_OVER);

            buttonStyle = new TextButton.TextButtonStyle();
            buttonStyle.fontColor = Color.BROWN;
            buttonStyle.font = new BitmapFont();
            button = new TextButton(PLAY_AGAIN, buttonStyle);
            button.setScale(3, 3);
            button.getLabel().setFontScale(2);
            button.setHeight(200);
            button.setWidth(300);
            button.setColor(Color.BLUE);
            button.setBounds(buttonX, buttonY, 300, 200);
            button.setPosition(buttonX - (button.getWidth()/2), buttonY);

            this.gsm = stateMngr;
            prepareButton();
        }

    public GlyphLayout getFontLayout() {
        return this.fontLayout;
    }
    private void prepareButton() {
        button.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Button changed");
                gsm.set(new PlayState(gsm));
            }
        } );

    }

    public BitmapFont getFont() {
        return this.font;
    }

    public TextButton getPlayAgain() {
        return this.button;
    }

    public float getPositionY() {
            return buttonY;
        }

    public float getPositionX() {
            return buttonX;
        }

    public TextButton getButton() {
        return button;
    }

}
