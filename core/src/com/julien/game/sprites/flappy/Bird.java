package com.julien.game.sprites.flappy;

import com.badlogic.gdx.graphics.Texture;
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

    private Texture beerTexture;

    //Collision management
    private Rectangle beerBounds;

    public Bird(int x, int y){
        this.position = new Vector3(x, y, 0);
        this.velocity = new Vector3(0, 0, 0);
        this.beerTexture = new Texture("bird.png");
        beerBounds = new Rectangle(position.x, position.y, beerTexture.getWidth(), beerTexture.getHeight());
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getBeerTexture() {
        return beerTexture;
    }

    public void update(float dt) {
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
        beerBounds.setPosition(position.x, position.y);
    }

    public void jump(){
        velocity.y = 250;
    }

    public Rectangle getBounds(){
        return beerBounds;
    }

    public void dispose() {
        beerTexture.dispose();
    }
}
