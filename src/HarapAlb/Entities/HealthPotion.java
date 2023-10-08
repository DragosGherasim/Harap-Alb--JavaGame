package HarapAlb.Entities;

import HarapAlb.Main.RefLinks;

import java.awt.*;

public abstract class HealthPotion extends Item{
    protected boolean isCollected;
    protected static int HeartsToRestore;
    protected int moveY;
    protected int SPEED = 1;
    protected int maxY;
    protected int minY;

    public HealthPotion(RefLinks rfl, float x, float y, int width, int height) {
        super(rfl, x, y, width, height);

        this.isCollected = false;

        this.moveY = 0;
        this.maxY = (int)(y - 20);
        this.minY = (int)(y+6);
    }

    public abstract void HandleInteract();
    public void SmallAnimation(){
        moveY = 0;

        if (moveY + (int)y > minY || moveY + (int)y < maxY) {
            SPEED *= -1;
        }
        moveY = SPEED;
        y += moveY;
    }
    public void Update(){
        HandleInteract();

        SmallAnimation();
    }

    public abstract void Draw(Graphics g);

    public boolean GetIsCollected(){
        return isCollected;
    }
}