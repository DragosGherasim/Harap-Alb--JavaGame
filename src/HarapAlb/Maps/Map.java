package HarapAlb.Maps;

import HarapAlb.Entities.*;
import HarapAlb.Main.RefLinks;
import HarapAlb.Tiles.Tile;

import java.awt.*;

public abstract class Map {
    protected Background background;

    protected final RefLinks refLinks;
    protected EntityManager entityManager;
    protected ItemManager itemManager;

    protected Hero hero;
    protected Key key;
    protected Gate gate;

    protected  int width;
    protected int height;

    protected int[][] tiles;
    protected int[][] mapMatrix;

    public Map(RefLinks rfl){
        this.refLinks = rfl;

        this.width = 60;
        this.height = 20;
    }

    public void Update() {
        entityManager.Update();

        itemManager.Update();
    }

    public void Draw(Graphics g) {
        background.Draw(g);

        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                GetTile(x, y).Draw(g, (int) (x * Tile.TILE_WIDTH - refLinks.GetGameCamera().GetXOffs()), (int) (y * Tile.TILE_HEIGHT - refLinks.GetGameCamera().GetYOffs()));
                //g.drawString(x + " " + y, (int) (x * Tile.TILE_WIDTH - refLinks.GetGameCamera().GetXOffs()), (int) (y * Tile.TILE_HEIGHT - refLinks.GetGameCamera().GetYOffs()));
            }
        }

        itemManager.Draw(g);

        entityManager.Draw(g);
    }

    public Tile GetTile(int x, int y) {
        if (x >= width || y >= height) {
            return Tile.transparentTile;
        }

        return Tile.tiles[tiles[x][y]];
    }

    protected void LoadWorld() {
        tiles = new int[width][height];

        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                tiles[x][y] = MapMatrix(y, x);
            }
        }
    }

    protected abstract int MapMatrix(int x, int y);

    public float GetWidth() {
        return width;
    }

    public float GetHeight() {
        return height;
    }

    public Hero GetHero(){
        return this.hero;
    }

    public EntityManager GetEntityManager(){
        return this.entityManager;
    }

    public ItemManager GetItemManager() { return this.itemManager;}

    public Key GetKey(){
        return key;
    }

    public Gate GetGate(){
        return gate;
    }

    public void SetKey(Key newKey){
        key = newKey;
    }
}
