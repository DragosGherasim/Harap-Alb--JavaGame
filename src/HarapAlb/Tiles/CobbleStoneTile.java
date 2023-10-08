package HarapAlb.Tiles;

import HarapAlb.Graphics.Assets;

public class CobbleStoneTile extends Tile {
    public CobbleStoneTile(int idd) {
        super(Assets.cobbleStone, idd);

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
