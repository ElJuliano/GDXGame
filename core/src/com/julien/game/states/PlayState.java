package com.julien.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.julien.game.MyGame;
import com.julien.game.sprites.flappy.Bird;
import com.julien.game.sprites.flappy.Tube;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by Julien on 04/01/2017.
 */

public class PlayState extends State {
    private static final int TUBE_SPACING = 200;
    private static final int TUBE_COUNT = 4;
    private static final int CAM_OFFSET = 80;
    private float BIRD_INIT_POS;
    private float TUBE_POS = TUBE_SPACING + Tube.TUBE_WIDTH;
    private Bird bird;
    private Array<Tube> tubes;
    private Texture background;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;
    private Rectangle ground1Bounds, ground2Bounds;
    private float posToIncScore = TUBE_POS;

    // Adding a score feature
    private int score = 0;

    public PlayState(GameStateManager gsx){
        super(gsx);
        bird = new Bird(50, 500);
        BIRD_INIT_POS = bird.getPosition().x + 80;
        cam.setToOrtho(false, MyGame.WIDTH, MyGame.HEIGHT);
        background = new Texture("background.png");
        // Setting ground texture and position
        ground = new Texture("ground.png");

        groundPos1 = new Vector2((cam.position.x - cam.viewportWidth/2 ) - BIRD_INIT_POS , 0);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth/2) - BIRD_INIT_POS + ground.getWidth(), 0);
        ground1Bounds = new Rectangle(groundPos1.x, groundPos1.y, ground.getWidth(), ground.getHeight());
        ground2Bounds = new Rectangle(groundPos2.x, groundPos2.y, ground.getWidth(), ground.getHeight());
        tubes = new Array<Tube>();

        for(int i = 1; i <= TUBE_COUNT; i++){
            tubes.add(new Tube(i * (TUBE_POS)));
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
        updateGroundPos();
        cam.position.x = bird.getPosition().x + CAM_OFFSET;

        for(int i = 0; i < tubes.size; i++){
            Tube tube = tubes.get(i);
            if(cam.position.x - (cam.viewportWidth/2) > tube.getPosBottleTop().x + tube.getBottle_top().getWidth()) {
                tube.reposition(tube.getPosBottleTop().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }

            //Collision management
            if(tube.collides(bird.getBounds())) {
                gsm.set(new PlayState(gsm));
            }

        }
        //Managing ground collision
        if(ground1Bounds.overlaps(bird.getBounds()) || ground2Bounds.overlaps(bird.getBounds())){
            gsm.set(new PlayState(gsm));
        }

        //Score management
        if(bird.getPosition().x > posToIncScore) {
            // When the position of the bird is superior to the current tube position then we increment the
            // tube position and the score.
            posToIncScore += TUBE_POS;
            score ++;
        }

        System.out.println(score);
        cam.update();
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        //drawing background
        sb.draw(background, cam.position.x - (cam.viewportWidth/2), 0);
        //Drawing da bird
        sb.draw(bird.getBeerTexture(), bird.getPosition().x, bird.getPosition().y);
        //Drawing tubes
        for(Tube tube : tubes) {
            sb.draw(tube.getBottle_top(), tube.getPosBottleTop().x, tube.getPosBottleTop().y);
            sb.draw(tube.getBottle_bottom(), tube.getPosBottleBottom().x, tube.getPosBottleBottom().y);
        }

        // Drawing the ground
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        sb.end();
    }

    @Override
    protected void dispose() {
        background.dispose();
        bird.dispose();
        ground.dispose();
        for(Tube tube : tubes) {
            tube.dispose();
        }
        System.out.println("Play State disposed");

    }

    private void updateGroundPos(){
        if((groundPos1.x + ground.getWidth()) < ((cam.position.x - cam.viewportWidth/2 ))){
            groundPos1.add(2 * ground.getWidth(), 0);
            ground1Bounds.setPosition(groundPos1.x, groundPos1.y);
        }
        if((groundPos2.x + ground.getWidth()) < ((cam.position.x - cam.viewportWidth/2 ))){
            groundPos2.add(2 * ground.getWidth(), 0);
            ground2Bounds.setPosition(groundPos2.x, groundPos2.y);
        }
    }
}
