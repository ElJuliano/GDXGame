package com.julien.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.julien.game.MyGame;
import com.julien.game.sprites.Beer;

/**
 * Created by Julien on 04/01/2017.
 */

public class PlayState extends State {

    private Beer beer;

    public PlayState(GameStateManager gsx){
        super(gsx);
        beer = new Beer(50, 300);
        cam.setToOrtho(false, MyGame.WIDTH, MyGame.HEIGHT);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    protected void update(float dt) {
        handleInput();
        beer.update(dt);

    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(beer.getBeerTexture(), beer.getPosition().x, beer.getPosition().y);
        sb.end();
    }

    @Override
    protected void dispose() {

    }
}
