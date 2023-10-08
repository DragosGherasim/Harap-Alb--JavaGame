package HarapAlb.Tiles;

import HarapAlb.Graphics.Assets;

public class BrickTile extends Tile {
    public BrickTile(int idd) {
        super(Assets.brick, idd);

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
