package HarapAlb.Entities;

import HarapAlb.Graphics.Animation;
import HarapAlb.Graphics.Assets;
import HarapAlb.Main.RefLinks;

import java.awt.*;

public class Coin extends Item {
    private final Animation animCoin;
    private boolean isCollected;
    public Coin(RefLinks rfl, float x, float y, int width, int height){
        super(rfl, x, y, width, height);

        this.animCoin = new Animation(rfl, 120, Assets.coin);

        this.isCollected = false;
    }

    public void HandleInteract() {
        if (bounds.intersects(refLinks.GetHero().bounds)){
            isCollected = true;

            ++Hero.numOfCoins;
        }
    }

    @Override
    public void Update() {
        HandleInteract();

        animCoin.Update();
    }

    @Override
    public void Draw(Graphics g) {
        g.drawImage(animCoin.GetCurrentFrame(), (int) (x - refLinks.GetGameCamera().GetXOffs()), (int) (y - refLinks.GetGameCamera().GetYOffs()), width, height, null);
    }

    public boolean GetIsCollected(){
        return isCollected;
    }
}
