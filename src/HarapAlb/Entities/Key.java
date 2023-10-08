package HarapAlb.Entities;

import HarapAlb.Graphics.Animation;
import HarapAlb.Graphics.Assets;
import HarapAlb.Main.RefLinks;
import HarapAlb.Maps.Map1;
import HarapAlb.Maps.Map2;

import java.awt.*;

public class Key extends Item{
    private Animation animKey;
    private boolean isCollected;

    public  Key(RefLinks rfl, float x, float y, int width, int height) {
        super(rfl, x, y, width, height);

        this.isCollected = false;

        if (refLinks.GetMap() instanceof Map1) {
            this.animKey = new Animation(rfl, 120, Assets.silverKeyAnim);
        }
        else if (refLinks.GetMap() instanceof Map2){
            this.animKey = new Animation(rfl, 120, Assets.goldKeyAnim);
        }
        else{
            this.animKey = new Animation(rfl, 120, Assets.mythrilKeyAnim);
        }
    }

    public void HandleInteract() {
        if (bounds.intersects(refLinks.GetHero().bounds)){
            isCollected = true;
        }
    }

    @Override
    public void Update() {
        HandleInteract();

        animKey.Update();
    }

    @Override
    public void Draw(Graphics g) {
        g.drawImage(animKey.GetCurrentFrame(), (int) (x - refLinks.GetGameCamera().GetXOffs()), (int) (y - refLinks.GetGameCamera().GetYOffs()), width, height, null);
    }

    public boolean GetIsCollected(){
        return isCollected;
    }

    public void SetIsCollected(boolean value){
        isCollected = value;
    }
}
