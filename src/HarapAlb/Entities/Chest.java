package HarapAlb.Entities;

import HarapAlb.Graphics.Animation;
import HarapAlb.Graphics.Assets;
import HarapAlb.Main.RefLinks;
import HarapAlb.Utils.ItemFactory;
import HarapAlb.Utils.ItemTypes;

import java.awt.*;
import java.util.ArrayList;

public class Chest extends Item {
    private final Animation animChest;
    private boolean isOpened;
    private boolean animChestFinished;
    private boolean canCoinsToBeSpawned;
    private ArrayList<Coin> coinsToSpawn;
    public Chest(RefLinks rfl, float x, float y, int width, int height){
        super(rfl, x, y, width, height);

        this.animChest = new Animation(rfl, 150, Assets.chest);

        this.isOpened = false;

        this.animChestFinished = false;

        this.canCoinsToBeSpawned = false;

        this.coinsToSpawn = new ArrayList<>();
    }

    public void HandleInteract() {
        if (bounds.intersects(refLinks.GetHero().bounds)){
            isOpened = true;
        }
    }

    @Override
    public void Update() {
        if (isOpened){
            if (animChest.GetAnimIndex() != animChest.GetFrameLength() - 1){
                animChest.Update();
            }
            else{
                animChestFinished = true;

                coinsToSpawn.add((Coin)ItemFactory.CreateItem(ItemTypes.Coin, refLinks, x+3, y-20, 32, 32));
                coinsToSpawn.add((Coin)ItemFactory.CreateItem(ItemTypes.Coin, refLinks, x+13,y-20, 32, 32));

                canCoinsToBeSpawned = true;
            }

            return;
        }

        HandleInteract();
    }

    @Override
    public void Draw(Graphics g) {
        g.drawImage(animChest.GetCurrentFrame(), (int) (x - refLinks.GetGameCamera().GetXOffs()), (int) (y - refLinks.GetGameCamera().GetYOffs()), width, height, null);
    }

    public boolean GetAnimIsFinished() {
        return animChestFinished;
    }

    public ArrayList<Coin> GetCoinsToSpawn(){
        return coinsToSpawn;
    }

    public void SetCanCoinBeSpawned(boolean value){
        canCoinsToBeSpawned = value;
    }

    public boolean GetCanCoinBeSpawned(){
        return canCoinsToBeSpawned;
    }
}
