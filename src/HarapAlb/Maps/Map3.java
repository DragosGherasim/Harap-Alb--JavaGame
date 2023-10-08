package HarapAlb.Maps;

import HarapAlb.Entities.EntityManager;
import HarapAlb.Entities.Gate;
import HarapAlb.Entities.Hero;
import HarapAlb.Entities.ItemManager;
import HarapAlb.Main.RefLinks;
import HarapAlb.Utils.*;

public class Map3 extends Map{
    public Map3(RefLinks rfl) {
        super(rfl);

        try {
            this.mapMatrix = LoadMapMatrixFromTxt.LoadMap("/Maps/Map3.txt");
        }
        catch (Exception e){
            System.err.println(e);
        }

        LoadWorld();

        this.hero = new Hero(refLinks, 0, 480
        ); // 0 383 -> start poz

        this.entityManager = new EntityManager(refLinks, hero);
        this.itemManager = new ItemManager(refLinks);

        this.entityManager.AddEntity(EnemyFactory.CreateEntity(EnemyTypes.Rat, rfl, 1088, 511, 32, 32, 0.8f));
        this.entityManager.AddEntity(EnemyFactory.CreateEntity(EnemyTypes.Goblin, rfl, 1408, 351, 32, 32, 0.5f));
        this.entityManager.AddEntity(EnemyFactory.CreateEntity(EnemyTypes.Goblin, rfl, 1760, 447, 32, 32, 0.0f));

        this.itemManager.AddItem(ItemFactory.CreateItem(ItemTypes.Chest, rfl, 160, 248, 48, 48));

        this.itemManager.AddItem(ItemFactory.CreateItem(ItemTypes.Coin, rfl, 544,544, 32, 32));
        this.itemManager.AddItem(ItemFactory.CreateItem(ItemTypes.Coin, rfl, 1088,256, 32, 32));

        this.gate = (Gate)ItemFactory.CreateItem(ItemTypes.Gate, rfl, 1884, 412, 35, 40);
        this.itemManager.AddItem(gate);

        this.background = new Background(refLinks, "/Backgrounds/map3_background.jpg");
    }

    @Override
    protected int MapMatrix(int x, int y) {
        return mapMatrix[x][y];
    }
}
