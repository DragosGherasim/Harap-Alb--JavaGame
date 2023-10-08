package HarapAlb.Entities;

import HarapAlb.Graphics.Assets;
import HarapAlb.Main.RefLinks;

import java.awt.*;

public class BigHealthPotion extends HealthPotion {
    public BigHealthPotion(RefLinks rfl, float x, float y, int width, int height) {
        super(rfl, x, y, width, height);

        HeartsToRestore = 2;
    }

    @Override
    public void HandleInteract() {
        if (bounds.intersects(refLinks.GetHero().bounds) && Hero.numOfSmallPotions <= 3){
            isCollected = true;

            ++Hero.numOfBigPotions;
        }
    }

    @Override
    public void Draw(Graphics g) {
        g.drawImage(Assets.bigHealthPotion, (int) (x - refLinks.GetGameCamera().GetXOffs()), (int) (y - refLinks.GetGameCamera().GetYOffs()), width, height, null);

        if (bounds.intersects(refLinks.GetHero().bounds) && !isCollected){
            Font font = new Font("Pixel Font", Font.PLAIN, 16);
            g.setFont(font);
            g.setColor(Color.RED);
            g.drawString("MAX", (int)x+5, (int)y-5);
        }
    }
}
