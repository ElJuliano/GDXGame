package com.julien.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.julien.game.MyGame;
import com.julien.game.sprites.alcoolgame.Beer;
import com.julien.game.sprites.alcoolgame.Bottle;
import com.julien.game.sprites.flappy.Bird;
import com.julien.game.sprites.flappy.Tube;

/**
 * Created by Julien on 04/01/2017.
 */

public class PlayState extends State {
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private Bird bird;
    private Array<Tube> tubes;
    private Texture background;

    public PlayState(GameStateManager gsx){
        super(gsx);
        bird = new Bird(50, 500);
        cam.setToOrtho(false, MyGame.WIDTH, MyGame.HEIGHT);
        background = new Texture("background.png");
        tubes = new Array<Tube>();

        for(int i = 1; i <= TUBE_COUNT; i++){
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isTouched()){
            bird.jump();
        }

    }

    @Override
    protected void update(float dt) {
        handleInput();
        bird.update(dt);
        cam.position.x = bird.getPosition().x + 80;

        for(int i = 0; i < tubes.size; i++){
            Tube tube = tubes.get(i);
            if(cam.position.x - (cam.viewportWidth/2) > tube.getPosBottleTop().x + tube.getBottle_top().getWidth()) {
                tube.reposition(tube.getPosBottleTop().x + ((Bottle.BOTTLE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }

            //Collision management
            if(tube.collides(bird.getBounds())) {
                gsm.set(new PlayState(gsm));
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
        sb.draw(bird.getBeerTexture(), bird.getPosition().x, bird.getPosition().y);
        //Drawing bottles
        for(Tube tube : tubes) {
            sb.draw(tube.getBottle_top(), tube.getPosBottleTop().x, tube.getPosBottleTop().y);
            sb.draw(tube.getBottle_bottom(), tube.getPosBottleBottom().x, tube.getPosBottleBottom().y);
        }
        sb.end();
    }

    @Override
    protected void dispose() {
        background.dispose();
        bird.dispose();
        for(Tube tube : tubes) {
            tube.dispose();
        }
        System.out.println("Play State disposed");

    }
}
