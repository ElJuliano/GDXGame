package com.julien.game.sprites.flappy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
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

    private TextButton button;
    private TextButton.TextButtonStyle buttonStyle;
    private float buttonX;
    private float buttonY;

    private GlyphLayout fontLayout;
    private Skin skin;

    private GameStateManager gsm;

        public GameOver(GameStateManager stateMngr, float goX, float goY) {
            font = new BitmapFont();
            buttonX = goX;//cam.position.x;
            buttonY = goY;//MyGame.HEIGHT - 200;
            font.getData().setScale(3, 3);
            font.setColor(com.badlogic.gdx.graphics.Color.BLACK);
            fontLayout = new GlyphLayout(font, GAME_OVER);

            buttonStyle = new TextButton.TextButtonStyle();
            buttonStyle.font = new BitmapFont();
            buttonStyle.fontColor = Color.WHITE;
            buttonStyle.overFontColor = Color.BLACK;

            createBasicSkin();

            button = new TextButton(PLAY_AGAIN, skin);
            button.setScale(3, 3);
            button.getLabel().setFontScale(4);
            button.setHeight(200);
            button.setWidth(300);
            button.setBounds(buttonX, buttonY, 300, 200);
            button.setPosition(buttonX , buttonY);

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

    private void createBasicSkin(){
        //Create a font
        BitmapFont font = new BitmapFont();
        skin = new Skin();
        skin.add("default", font);

        //Create a texture
        Pixmap pixmap = new Pixmap((int) Gdx.graphics.getWidth()/4,(int)Gdx.graphics.getHeight()/10, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("background",new Texture(pixmap));

        //Create a button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("background", Color.GRAY);
        textButtonStyle.down = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.over = skin.newDrawable("background", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);

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
