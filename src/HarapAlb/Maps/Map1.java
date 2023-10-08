package HarapAlb.Maps;

import HarapAlb.Entities.EntityManager;
import HarapAlb.Entities.Gate;
import HarapAlb.Entities.Hero;
import HarapAlb.Entities.ItemManager;
import HarapAlb.Main.Game;
import HarapAlb.Main.RefLinks;
import HarapAlb.States.InGameState;
import HarapAlb.Utils.*;

public class Map1 extends Map{
    public Map1(RefLinks rfl) {
        super(rfl);

        try {
            this.mapMatrix = LoadMapMatrixFromTxt.LoadMap("/Maps/Map1.txt");
        }
        catch (Exception e){
            System.err.println(e);
        }

        LoadWorld();

        InGameState.startTimer = System.currentTimeMillis();

        this.hero = new Hero(refLinks, 0, 479);

        Hero.health = 3;
        Game.SCORE = 0;

        this.entityManager = new EntityManager(refLinks, hero);
        this.itemManager = new ItemManager(refLinks);

        this.entityManager.AddEntity(EnemyFactory.CreateEntity(EnemyTypes.Rat, rfl, 1691, 479, 32, 32, 1.0f));

        this.itemManager.AddItem(ItemFactory.CreateItem(ItemTypes.Coin, rfl, 224,480, 32, 32));
        this.itemManager.AddItem(ItemFactory.CreateItem(ItemTypes.Coin, rfl, 625,285, 32, 32));
        this.itemManager.AddItem(ItemFactory.CreateItem(ItemTypes.Coin, rfl, 896,480, 32, 32));
        this.itemManager.AddItem(ItemFactory.CreateItem(ItemTypes.Coin, rfl, 1216,320, 32, 32));

        this.itemManager.AddItem(ItemFactory.CreateItem(ItemTypes.Chest, rfl, 1334, 504, 48, 48));

        this.gate = (Gate)ItemFactory.CreateItem(ItemTypes.Gate, rfl, 1884, 408, 35, 40);
        this.itemManager.AddItem(gate);

        this.background = new Background(refLinks, "/Backgrounds/map1_background.jpg");
    }

    @Override
    protected int MapMatrix(int x, int y) {
        return mapMatrix[x][y];
    }
}
