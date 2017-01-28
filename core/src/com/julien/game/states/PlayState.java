package com.julien.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.julien.game.MyGame;
import com.julien.game.sprites.flappy.Bird;
import com.julien.game.sprites.flappy.GameOver;
import com.julien.game.sprites.flappy.Score;
import com.julien.game.sprites.flappy.Tube;

/**
 * Created by Julien on 04/01/2017.
 */

public class PlayState extends State {

    private Stage stage;

    private static enum STATE {
        PLAY,
        PAUSE,
        GAMEOVER
    }
    private STATE currentState;
    private static final int TUBE_SPACING = 200;
    public static final float TUBE_POS = TUBE_SPACING + Tube.TUBE_WIDTH;
    private static final int TUBE_COUNT = 4;
    private static final int CAM_OFFSET = 80;
    private float BIRD_INIT_POS;
    private Bird bird;
    private Array<Tube> tubes;
    private Texture background;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;
    private Rectangle ground1Bounds, ground2Bounds;
    //Score Management
    private Score score;

    //GameOver
    private GameOver gameOver;

    public PlayState(GameStateManager gsx){
        super(gsx);
        currentState = STATE.PLAY;
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

        score = new Score(cam.position.x, MyGame.HEIGHT - 150);
        gameOver = new GameOver(gsx, cam.position.x, MyGame.HEIGHT - 300);

        //

        stage = new Stage();
        stage.clear();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isTouched()){
            bird.jump();
        }

    }

    @Override
    protected void update(float dt) {
        switch(currentState) {
            case GAMEOVER:
                break;
            case PAUSE:
                break;
            case PLAY :
                handleInput();
                bird.update(dt);
                updateGroundPos();
                cam.position.x = bird.getPosition().x + CAM_OFFSET;
                //stage.getViewport().update(stage.getViewport().getScreenX() + CAM_OFFSET, stage.getViewport().getScreenY());

                for(int i = 0; i < tubes.size; i++){
                    Tube tube = tubes.get(i);
                    if(cam.position.x - (cam.viewportWidth/2) > tube.getPosBottleTop().x + tube.getBottle_top().getWidth()) {
                        tube.reposition(tube.getPosBottleTop().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
                    }

                    //Collision management
                    if(tube.collides(bird.getBounds())) {
                        gameOver = new GameOver(gsm, MyGame.WIDTH/2, MyGame.HEIGHT - 300);
                        System.out.println("position "+cam.position.x);
                        bird.setCrashed(true);
                        bird.playCrashSound();
                        stage.addActor(gameOver.getButton());
                        currentState = STATE.GAMEOVER;
                    }

                }
                //Managing ground collision
                if(ground1Bounds.overlaps(bird.getBounds()) || ground2Bounds.overlaps(bird.getBounds())){
                    gameOver = new GameOver(gsm, MyGame.WIDTH/2, MyGame.HEIGHT - 300);
                    System.out.println("position "+cam.position.x);
                    bird.setCrashed(true);
                    bird.playCrashSound();
                    stage.addActor(gameOver.getButton());
                    currentState = STATE.GAMEOVER;
                }

                score.updateScore(bird , cam.position.x);
                cam.update();
                break;
        }
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        stage.act();
        sb.begin();
        //drawing background
        sb.draw(background, cam.position.x - (cam.viewportWidth/2), 0);
        //Drawing da bird
        sb.draw(bird.getBirdTexture(), bird.getPosition().x, bird.getPosition().y);
        //Drawing tubes
        for(Tube tube : tubes) {
            sb.draw(tube.getBottle_top(), tube.getPosBottleTop().x, tube.getPosBottleTop().y);
            sb.draw(tube.getBottle_bottom(), tube.getPosBottleBottom().x, tube.getPosBottleBottom().y);
        }

        // Drawing the ground
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        score.getFont().draw(sb, score.toString(), score.getFontPositionX(), score.getFontPositionY());

        if(currentState.equals(STATE.GAMEOVER)) {

            gameOver.getFont().draw(sb, gameOver.getFontLayout(), gameOver.getPositionX()- gameOver.getFontLayout().width/2, gameOver.getPositionY());
            //gameOver.getButton().draw(sb, 1f);
            stage.draw();
        }

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
        stage.dispose();
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

    public Bird getBird() {
        return bird;
    }

    public Texture getGround() {
        return ground;
    }

    public Texture getBackground() {
        return background;
    }

    public Vector2 getGroundPos1() {
        return groundPos1;
    }

    public Vector2 getGroundPos2() {
        return groundPos2;
    }

    public Rectangle getGround1Bounds() {
        return ground1Bounds;
    }

    public Rectangle getGround2Bounds() {
        return ground2Bounds;
    }

    public Score getScore() {
        return score;
    }

    public Array<Tube> getTubes() {
        return tubes;
    }

}
