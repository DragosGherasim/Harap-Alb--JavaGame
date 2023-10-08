package HarapAlb.Utils;

import HarapAlb.Entities.*;
import HarapAlb.Main.RefLinks;

public class ItemFactory {
    private final RefLinks refLinks;

    public ItemFactory(RefLinks rfl){
        this.refLinks = rfl;
    }
    public static Item CreateItem(ItemTypes itemTypes, RefLinks rfl, float x, float y, int width, int height){
        switch (itemTypes) {
            case Coin -> {
                return new Coin(rfl, x, y, width, height);
            }
            case Chest -> {
                return new Chest(rfl, x, y, width, height);
            }
            case SmallHealthPotion -> {
                return new SmallHealthPotion(rfl, x, y, width, height);
            }
            case BigHealthPotion -> {
                return new BigHealthPotion(rfl, x, y, width, height);
            }
            case Gate -> {
                return new Gate(rfl, x, y, width, height);
            }
            case Key -> {
                return new Key(rfl, x, y, width, height);
            }
            default -> {
                return null;
            }
        }
    }
}
