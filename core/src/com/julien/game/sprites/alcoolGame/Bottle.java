package com.julien.game.sprites.alcoolgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Julien on 04/01/2017.
 */

public class Bottle {
    public final static int BOTTLE_WIDTH = 101;

    private static final int FLUCTUATION = 150;
    private static final int TUBE_GAP = 150;
    private static final int LOWEST_OPENING = 480;
    private Texture bottle_top, bottle_bottom;
    private Vector2 posBottleTop, posBottleBottom;
    private Random rand;

    //Collision management
    private Rectangle boundsTop, boundsBot;

    public Bottle(float x) {
        bottle_top = new Texture("diplob.png");
        bottle_bottom = new Texture("diploh.png");

        rand = new Random();
        posBottleTop = new Vector2(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBottleBottom = new Vector2(x, posBottleTop.y - TUBE_GAP - bottle_bottom.getHeight());

        //Initializing rectangles
        boundsBot = new Rectangle(posBottleBottom.x, posBottleBottom.y, bottle_bottom.getWidth(), bottle_bottom.getHeight());
        boundsTop = new Rectangle(posBottleTop.x, posBottleTop.y, bottle_top.getWidth(), bottle_top.getHeight());
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

    public void reposition(float x) {
        posBottleTop.set(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBottleBottom.set(x, posBottleTop.y - TUBE_GAP - bottle_bottom.getHeight());

        boundsTop.setPosition(posBottleTop.x, posBottleTop.y);
        boundsBot.setPosition(posBottleBottom.x, posBottleBottom.y);
    }

    /**
     * Return true if a player overlaps a bottle
     * @param player
     * @return
     */
    public boolean collides(Rectangle player) {
        return (player.overlaps(boundsBot) || player.overlaps(boundsTop));
    }

    public void dispose() {
        bottle_bottom.dispose();
        bottle_top.dispose();
    }

}
