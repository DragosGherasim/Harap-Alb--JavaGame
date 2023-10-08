package HarapAlb.Tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    public static final int TILE_WIDTH = 32;
    public static final int TILE_HEIGHT = 32;
    private static final int NO_TILES = 10;

    public static Tile[] tiles = new Tile[NO_TILES];
    public static Tile transparentTile = new TransparentTile(0);
    public static Tile dirtWhGrassTile = new DirtWhGrassTile(1);
    public static Tile dirtTile = new DirtTile(2);
    public static Tile bedRockTile = new BedRockTile(3);
    public static Tile stoneBrickTile = new StoneBrickTile(4);
    public static Tile brickTile = new BrickTile(5);
    public static Tile cobbleStoneTile = new CobbleStoneTile(6);
    public static Tile cobbleStoneWhSnowTile = new CobbleStoneWhSnowTile(7);
    public static Tile spikesTile = new SpikesTile(8);
    public static Tile iceBlockTile = new IceBlockTile(9);
    protected  float FRICTION_X;
    protected final int id;         // id-ul unic aferent tipului de dala
    protected BufferedImage img;    // imaginea aferenta tipului de dala

    public Tile(BufferedImage image, int idd) {
        this.img = image;
        this.id = idd;

        tiles[id] = this;
    }

    public void Update() {

    }

    public void Draw(Graphics g, int x, int y) {
        g.drawImage(img, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }

    public boolean IsSolid() {
        return false;
    }

    public boolean IsDeadly(){
        return false;
    }

    public int GetId() {
        return id;
    }

    public float GetFriction(){
        return FRICTION_X;
    }
}
