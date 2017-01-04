package com.julien.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.julien.game.MyGame;
import com.julien.game.sprites.Beer;
import com.julien.game.sprites.Bottle;

/**
 * Created by Julien on 04/01/2017.
 */

public class PlayState extends State {
    private static final int BOTTLE_SPACING = 125;
    private static final int BOTTLE_COUNT = 4;
    private Beer beer;
    private Bottle bottle;
    private Array<Bottle> bottles;
    private Texture background;

    public PlayState(GameStateManager gsx){
        super(gsx);
        beer = new Beer(50, 500);
        cam.setToOrtho(false, MyGame.WIDTH, MyGame.HEIGHT);
        background = new Texture("barbg.png");
        bottles = new Array<Bottle>();

        for(int i = 0; i < BOTTLE_COUNT; i++){
            bottles.add(new Bottle(i * (BOTTLE_SPACING + Bottle.BOTTLE_WIDTH)));
        }
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
        cam.position.x = beer.getPosition().x + 80;

        for(Bottle bottle : bottles){
            if(cam.position.x - (cam.viewportWidth/2) > bottle.getPosBottleTop().x + bottle.getBottle_top().getWidth()) {
                bottle.reposition(bottle.getPosBottleTop().x + ((Bottle.BOTTLE_WIDTH + BOTTLE_SPACING) * BOTTLE_COUNT));
            }
        }

        cam.update();
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
        for(Bottle bottle : bottles) {
            sb.draw(bottle.getBottle_top(), bottle.getPosBottleTop().x, bottle.getPosBottleTop().y);
            sb.draw(bottle.getBottle_bottom(), bottle.getPosBottleBottom().x, bottle.getPosBottleBottom().y);
        }
        sb.end();
    }

    @Override
    protected void dispose() {

    }
}
