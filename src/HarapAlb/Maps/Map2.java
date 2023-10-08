package HarapAlb.Maps;

import HarapAlb.Entities.EntityManager;
import HarapAlb.Entities.Gate;
import HarapAlb.Entities.Hero;
import HarapAlb.Entities.ItemManager;
import HarapAlb.Main.RefLinks;
import HarapAlb.Utils.*;

public class Map2 extends Map{
    public Map2(RefLinks rfl) {
        super(rfl);

        try {
            this.mapMatrix = LoadMapMatrixFromTxt.LoadMap("/Maps/Map2.txt");
        }
        catch (Exception e){
            System.err.println(e);
        }

        LoadWorld();

        this.hero = new Hero(refLinks, 0, 383); // 0 383 -> start poz

        this.entityManager = new EntityManager(refLinks, hero);
        this.itemManager = new ItemManager(refLinks);

        this.entityManager.AddEntity(EnemyFactory.CreateEntity(EnemyTypes.Rat, rfl, 224, 479, 32, 32, 0.7f));
        this.entityManager.AddEntity(EnemyFactory.CreateEntity(EnemyTypes.Rat, rfl, 544, 320, 32, 32, 07f));
        this.entityManager.AddEntity(EnemyFactory.CreateEntity(EnemyTypes.Rat, rfl, 834, 287, 32, 32, 0.6f));
        this.entityManager.AddEntity(EnemyFactory.CreateEntity(EnemyTypes.Goblin, rfl, 1664, 415, 32, 32, 0.8f));

        this.itemManager.AddItem(ItemFactory.CreateItem(ItemTypes.Chest, rfl, 416, 535, 48, 48));
        this.itemManager.AddItem(ItemFactory.CreateItem(ItemTypes.Chest, rfl, 1240, 472, 48, 48));

        this.itemManager.AddItem(ItemFactory.CreateItem(ItemTypes.Coin, rfl, 640,288, 32, 32));
        this.itemManager.AddItem(ItemFactory.CreateItem(ItemTypes.Coin, rfl, 1230,288, 32, 32));

        this.gate = (Gate)ItemFactory.CreateItem(ItemTypes.Gate, rfl, 1884, 344, 35, 40);
        this.itemManager.AddItem(gate);

        this.background = new Background(refLinks, "/Backgrounds/map2_background.png");
    }

    @Override
    protected int MapMatrix(int x, int y) {
        return mapMatrix[x][y];
    }
}