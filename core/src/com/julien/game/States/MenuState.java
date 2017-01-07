package com.julien.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.julien.game.MyGame;

/**
 * Created by Julien on 04/01/2017.
 */

public class MenuState extends State {

    private Texture background;
    private Texture playBtn;

    public MenuState(GameStateManager currentGsm) {
        super(currentGsm);
        background = new Texture("barbg.png");
        playBtn = new Texture("Button-Play-icon.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    protected void update(float dt) {
        handleInput();
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, MyGame.WIDTH, MyGame.HEIGHT);
        sb.draw(playBtn, (MyGame.WIDTH)/2 -(playBtn.getWidth()/4), MyGame.HEIGHT/2 - playBtn.getHeight()/4, playBtn.getWidth()/2, playBtn.getHeight()/2);
        sb.end();
    }

    @Override
    protected void dispose() {
        background.dispose();
        playBtn.dispose();
        System.out.println("Menu State disposed");
    }
}
