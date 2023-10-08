package HarapAlb.Tiles;

import HarapAlb.Graphics.Assets;

public class CobbleStoneWhSnowTile extends Tile {
    public CobbleStoneWhSnowTile(int idd) {
        super(Assets.cobbleStoneWhSnow, idd);

        FRICTION_X = 0.7f;
    }

    public boolean IsDeadly(){
        return super.IsDeadly();
    }

    @Override
    public boolean IsSolid() {
        return true;
    }
}
