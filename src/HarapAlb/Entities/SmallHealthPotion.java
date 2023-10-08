package HarapAlb.Entities;

import HarapAlb.Graphics.Assets;
import HarapAlb.Main.RefLinks;

import java.awt.*;

public class SmallHealthPotion extends HealthPotion {
    public SmallHealthPotion(RefLinks rfl, float x, float y, int width, int height) {
        super(rfl, x, y, width, height);

        HeartsToRestore = 1;
    }

    @Override
    public void HandleInteract() {
        if (bounds.intersects(refLinks.GetHero().bounds) && Hero.numOfSmallPotions < 3){
            isCollected = true;

            ++Hero.numOfSmallPotions;
        }
    }

    @Override
    public void Draw(Graphics g) {
        g.drawImage(Assets.smallHealthPotion, (int) (x - refLinks.GetGameCamera().GetXOffs()), (int) (y - refLinks.GetGameCamera().GetYOffs()), width, height, null);

        if (bounds.intersects(refLinks.GetHero().bounds) && !isCollected){
            Font font = new Font("Pixel Font", Font.PLAIN, 10);
            g.setFont(font);
            g.setColor(Color.RED);
            g.drawString("MAX", (int)(x - refLinks.GetGameCamera().GetXOffs() + 3), (int) (y - refLinks.GetGameCamera().GetYOffs()-2));
        }
    }
}
