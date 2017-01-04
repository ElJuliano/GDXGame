package com.julien.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.julien.game.MyGame;

/**
 * Created by Julien on 04/01/2017.
 */

public class PlayState extends State {

    private Texture beer;

    public PlayState(GameStateManager gsx){
        super(gsx);
        beer = new Texture("beer.png");
        cam.setToOrtho(false, MyGame.WIDTH, MyGame.HEIGHT);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    protected void update(float dt) {

    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(beer, 50,50);
        sb.end();
    }

    @Override
    protected void dispose() {

    }
}
