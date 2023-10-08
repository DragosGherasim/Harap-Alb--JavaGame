package HarapAlb.Tiles;

import HarapAlb.Graphics.Assets;


public class BedRockTile extends Tile {
    public BedRockTile(int idd) {
        super(Assets.bedRock, idd);

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
