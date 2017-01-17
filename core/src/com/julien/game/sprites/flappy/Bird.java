package com.julien.game.sprites.flappy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Julien on 04/01/2017.
 */

public class Bird {

    private static final int MOVEMENT = 100;
    private static final int GRAVITY = -15;
    private Vector3 position;
    private Vector3 velocity;

    private Texture birdTexture;

    //Collision management
    private Rectangle birdBounds;

    //Bird animation
    private BirdAnimation animation;
    private Texture animationTexture;

    public Bird(int x, int y){
        this.position = new Vector3(x, y, 0);
        this.velocity = new Vector3(0, 0, 0);
        this.birdTexture = new Texture("bird.png");
        //Animation
        animationTexture = new Texture("birdAnim.png");
        animation = new BirdAnimation(new TextureRegion(animationTexture), 3 ,0.5f );
        birdBounds = new Rectangle(x, y, animationTexture.getWidth()/3, animationTexture.getHeight());
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getBirdTexture() {
        return animation.getFrame();
    }

    public void update(float dt) {
        animation.update(dt);
        if(position.y > 0) {
            velocity.add(0, GRAVITY, 0);
        }
        velocity.scl(dt);
        position.add(MOVEMENT * dt, velocity.y, 0);
        if(position.y < 0) {
            position.y = 0;
        }
        velocity.scl(1/dt);
        // Repositionning boundsBeer or collision management
        birdBounds.setPosition(position.x, position.y);
    }

    public void jump(){
        velocity.y = 250;
    }

    public Rectangle getBounds(){
        return birdBounds;
    }

    public void dispose() {
        animationTexture.dispose();
    }
}
