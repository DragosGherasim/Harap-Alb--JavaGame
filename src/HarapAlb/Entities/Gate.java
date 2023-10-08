package HarapAlb.Entities;

import HarapAlb.Graphics.Assets;
import HarapAlb.Main.RefLinks;
import HarapAlb.Maps.Map1;
import HarapAlb.Maps.Map2;

import java.awt.*;

public class Gate extends Item{
    private boolean isOpened;
    public Gate(RefLinks rfl, float x, float y, int width, int height) {
        super(rfl, x, y, width, height);

        this.isOpened = false;
    }

    public void HandleInteract() {
        if (bounds.intersects(refLinks.GetHero().bounds) && refLinks.GetKBInputs().e &&
                refLinks.GetMap().GetKey() != null && refLinks.GetMap().GetKey().GetIsCollected()){

            isOpened = true;
        }
    }

    @Override
    public void Update() {
        HandleInteract();
    }

    @Override
    public void Draw(Graphics g) {
        if (refLinks.GetMap() instanceof Map1) {
            g.drawImage(Assets.map1Gate, (int) (x - refLinks.GetGameCamera().GetXOffs()), (int) (y - refLinks.GetGameCamera().GetYOffs()), width, height, null);
        }
        else if (refLinks.GetMap() instanceof Map2){
            g.drawImage(Assets.map2Gate, (int) (x - refLinks.GetGameCamera().GetXOffs()), (int) (y - refLinks.GetGameCamera().GetYOffs()), width, height, null);
        }
        else{
            g.drawImage(Assets.map3Gate, (int) (x - refLinks.GetGameCamera().GetXOffs()), (int) (y - refLinks.GetGameCamera().GetYOffs()), width, height, null);
        }
    }

    public boolean GetIsOpened(){
        return isOpened;
    }

    public void SetIsOpened(boolean value){
        isOpened = value;
    }
}
