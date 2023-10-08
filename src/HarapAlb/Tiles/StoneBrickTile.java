package HarapAlb.Tiles;

import HarapAlb.Graphics.Assets;

public class StoneBrickTile extends Tile {
    public StoneBrickTile(int idd) {
        super(Assets.stoneBrick, idd);

        FRICTION_X = 1.0f;
    }

    public boolean IsDeadly(){
        return super.IsDeadly();
    }

    @Override
    public boolean IsSolid() {
        return true;
    }
}
