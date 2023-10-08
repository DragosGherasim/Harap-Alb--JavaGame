package HarapAlb.Graphics;

import HarapAlb.Main.RefLinks;

import java.awt.image.BufferedImage;

public class Animation {
    private RefLinks refLinks;
    private final BufferedImage[] frames;
    private final int animSpeed;
    private int animIndx;
    private long previousTime;
    private long animTime;

    public Animation(RefLinks rfl, int speed, BufferedImage[] fr) {
        this.refLinks = rfl;
        this.animSpeed = speed;
        this.frames = fr;

        this.animTime = 0;
        this.animIndx = 0;
        this.previousTime = System.currentTimeMillis();
    }

    public void Update() {
        animTime += System.currentTimeMillis() - previousTime;
        previousTime = System.currentTimeMillis();

        if (animTime >= animSpeed) {
            ++animIndx;
            animTime = 0;

            if (animIndx >= frames.length) {
                animIndx = 0;
            }
        }
    }

    public BufferedImage GetCurrentFrame() {
        return frames[animIndx];
    }
    public int GetFrameLength(){
        return frames.length;
    }

    public int GetAnimIndex() {
        return animIndx;
    }
}
