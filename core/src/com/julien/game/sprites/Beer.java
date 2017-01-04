package com.julien.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Julien on 04/01/2017.
 */

public class Beer {

    private static final int MOVEMENT = 100;
    private static final int GRAVITY = -15;
    private Vector3 position;
    private Vector3 velocity;

    private Texture beerTexture;

    public Beer(int x, int y){
        this.position = new Vector3(x, y, 0);
        this.velocity = new Vector3(0, 0, 0);
        this.beerTexture = new Texture("beer.png");
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
    }

    public void jump(){
        velocity.y = 250;
    }
}
