package com.julien.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.julien.game.MyGame;
import com.julien.game.sprites.Beer;
import com.julien.game.sprites.Bottle;

/**
 * Created by Julien on 04/01/2017.
 */

public class PlayState extends State {

    private Beer beer;
    private Bottle bottle;
    private Texture background;

    public PlayState(GameStateManager gsx){
        super(gsx);
        beer = new Beer(50, 300);
        cam.setToOrtho(false, MyGame.WIDTH, MyGame.HEIGHT);
        background = new Texture("barbg.png");
        bottle = new Bottle(100);
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isTouched()){
            beer.jump();
        }

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
        //drawing background
        sb.draw(background, cam.position.x - (cam.viewportWidth/2), 0);
        //Drawing da beer
        sb.draw(beer.getBeerTexture(), beer.getPosition().x, beer.getPosition().y);
        //Drawing bottles
        sb.draw(bottle.getBottle_top(), bottle.getPosBottleTop().x, bottle.getPosBottleTop().y);
        sb.draw(bottle.getBottle_bottom(), bottle.getPosBottleBottom().x, bottle.getPosBottleBottom().y);
        sb.end();
    }

    @Override
    protected void dispose() {

    }
}
