package HarapAlb.Entities;

import HarapAlb.Main.RefLinks;
import HarapAlb.Utils.ItemFactory;
import HarapAlb.Utils.ItemTypes;

import java.awt.*;
import java.util.ArrayList;

public class EntityManager {
    private RefLinks refLinks;
    private final Hero hero;
    private final ArrayList<Entity> entitiesList;
    private Entity lastEnemyAlive;

    public EntityManager(RefLinks rfl, Hero hero){
        this.refLinks = rfl;
        this.hero = hero;

        this.entitiesList = new ArrayList<>();

        this.lastEnemyAlive = null;
    }

    public void Update(){
        hero.Update();

        if (entitiesList.size() != 0) {
            ArrayList<Entity> deadEntities = new ArrayList<>();


            for (Entity currentEntity : entitiesList) {
                if (!currentEntity.GetIsDead()) {
                    currentEntity.Update();
                } else {
                    if (currentEntity instanceof Rat currentRat) {
                        if (currentRat.GetHealthPotion() != null) {
                            refLinks.GetMap().GetItemManager().AddItem(currentRat.GetHealthPotion());
                        }

                        refLinks.GetMap().GetItemManager().AddItem(currentRat.GetCoinDropped());
                        deadEntities.add(currentEntity);
                    }
                    else if (currentEntity instanceof Goblin currentGoblin) {
                        if (currentGoblin.GetHealthPotion() != null) {
                            refLinks.GetMap().GetItemManager().AddItem(currentGoblin.GetHealthPotion());
                        }

                        refLinks.GetMap().GetItemManager().AddItem(currentGoblin.GetCoinDropped());
                        deadEntities.add(currentEntity);
                    }

                    lastEnemyAlive = currentEntity;
                }
            }

            for (Entity deadEntity : deadEntities) {
                entitiesList.remove(deadEntity);
            }
        }
        else{
            if (lastEnemyAlive != null && refLinks.GetMap().GetKey() == null) {
                refLinks.GetMap().SetKey((Key)ItemFactory.CreateItem(ItemTypes.Key, refLinks, lastEnemyAlive.x+36, lastEnemyAlive.y+6, 16, 25));
                refLinks.GetMap().GetItemManager().AddItem(refLinks.GetMap().GetKey());
            }
        }
    }

    public void Draw(Graphics g){
        hero.Draw(g);

        for(Entity currentEntity : entitiesList){
            currentEntity.Draw(g);
        }
    }

    public void AddEntity(Entity entity) {
        entitiesList.add(entity);
    }

    public ArrayList<Entity> GetEntityList(){
        return this.entitiesList;
    }
}

