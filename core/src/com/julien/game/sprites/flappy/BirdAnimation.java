package com.julien.game.sprites.flappy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Julien on 17/01/2017.
 */

public class BirdAnimation {

    private Array<TextureRegion> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;
    private int frame;

    public BirdAnimation ( TextureRegion region, int frameCount, float cycleTime) {
        frames = new Array<TextureRegion>();
        int frameWidth = region.getRegionWidth() / frameCount;
        for(int i = 0; i < frameCount; i++) {
            frames.add(new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight()));
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }

    public void update(float dt) {
        currentFrameTime += dt;
        if(currentFrameTime > maxFrameTime) {
            frame++;
            currentFrameTime = 0;
        }
        if(frame >= frameCount) {
            frame = 0;
        }
    }

    public TextureRegion getFrame() {
        return frames.get(frame);
    }
}
