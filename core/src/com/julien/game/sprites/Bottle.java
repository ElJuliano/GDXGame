package com.julien.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Julien on 04/01/2017.
 */

public class Bottle {
    private static final int FLUCTUATION = 130;
    private static final int TUBE_GAP = 100;
    private static final int LOWEST_OPENING = 120;
    private Texture bottle_top, bottle_bottom;
    private Vector2 posBottleTop, posBottleBottom;
    private Random rand;

    public Bottle(float x){
        bottle_top = new Texture("diplob.png");
        bottle_bottom = new Texture("diploh.png");

        rand = new Random();
        posBottleTop = new Vector2(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBottleBottom = new Vector2(x, posBottleTop.y - TUBE_GAP - bottle_bottom.getHeight());
    }

    public Texture getBottle_top() {
        return bottle_top;
    }

    public Texture getBottle_bottom() {
        return bottle_bottom;
    }

    public Vector2 getPosBottleTop() {
        return posBottleTop;
    }

    public Vector2 getPosBottleBottom() {
        return posBottleBottom;
    }
}
