package HarapAlb.Entities;

import HarapAlb.Main.RefLinks;

import java.awt.*;
import java.util.ArrayList;

public class ItemManager {
    private RefLinks refLinks;
    private final ArrayList<Item> itemList;

    public ItemManager(RefLinks rfl){
        this.refLinks = rfl;

        this.itemList = new ArrayList<>();
    }

    public void Update() {
        ArrayList<Item> itemsToRemove = new ArrayList<>();
        ArrayList<Coin> coinsToAdd = new ArrayList<>();

        boolean canAddCoins = false;
        boolean canItemsToBeRemoved = false;

        for (Item currentItem : itemList) {
            if (currentItem instanceof Coin currentCoin){
                if (!currentCoin.GetIsCollected()){
                    currentCoin.Update();
                }
                else{
                    itemsToRemove.add(currentCoin);
                    canItemsToBeRemoved = true;
                }
            }
            else if (currentItem instanceof HealthPotion currentHealthPotion){
                 if (!currentHealthPotion.GetIsCollected()){
                    currentHealthPotion.Update();
                 }
                 else{
                    itemsToRemove.add(currentHealthPotion);
                    canItemsToBeRemoved = true;
                 }
            }
            else if (currentItem instanceof Chest currentChest) {
                if (!currentChest.GetAnimIsFinished()) {
                    currentChest.Update();
                }
                else if (currentChest.GetCanCoinBeSpawned()){
                    coinsToAdd.addAll(currentChest.GetCoinsToSpawn());
                    canAddCoins = true;

                    currentChest.SetCanCoinBeSpawned(false);
                }
            }
            else if(currentItem instanceof Key currentKey){
                if (!currentKey.GetIsCollected()){
                    currentKey.Update();
                }
                else{
                    itemsToRemove.add(currentItem);
                    canItemsToBeRemoved = true;
                }
            }
            else{
                currentItem.Update();
            }
        }

        if (canAddCoins) {
            itemList.addAll(coinsToAdd);
        }

        if (canItemsToBeRemoved) {
            for (Item currentItem : itemsToRemove) {
                itemList.remove(currentItem);
            }
        }
    }

    public void Draw(Graphics g){
        for (Item currentItem : itemList) {
            currentItem.Draw(g);
        }
    }

    public void AddItem(Item item) {
        itemList.add(item);
    }
}