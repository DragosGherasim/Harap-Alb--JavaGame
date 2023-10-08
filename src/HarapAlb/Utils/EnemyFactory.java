package HarapAlb.Utils;

import HarapAlb.Entities.Entity;
import HarapAlb.Entities.Goblin;
import HarapAlb.Entities.Rat;
import HarapAlb.Main.RefLinks;

public class EnemyFactory {
    private final RefLinks refLinks;

    public EnemyFactory(RefLinks rfl){
        this.refLinks = rfl;
    }
    public static Entity CreateEntity(EnemyTypes enemyType, RefLinks rfl, float x, float y, int width, int height, float dropRatePotion){
        switch (enemyType) {
            case Rat -> {
                return new Rat(rfl, x, y, width, height, dropRatePotion);
            }
            case Goblin -> {
                return new Goblin(rfl, x, y, width, height, dropRatePotion);
            }
            default -> {
                return null;
            }
        }
    }
}
