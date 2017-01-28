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
        cam.setToOrtho(false, MyGame.WIDTH, MyGame.HEIGHT);
        background = new Texture("background.png");
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
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        sb.draw(playBtn, cam.position.x -(playBtn.getWidth()/2), cam.position.y -(playBtn.getHeight()/2));
        sb.end();
    }

    @Override
    protected void dispose() {
        background.dispose();
        playBtn.dispose();
        System.out.println("Menu State disposed");
    }
}
