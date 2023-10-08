package HarapAlb.Tiles;

import HarapAlb.Graphics.Assets;

public class DirtTile extends Tile {
    public DirtTile(int idd) {
        super(Assets.dirt, idd);

        FRICTION_X = 0.6f;
    }

    public boolean IsDeadly(){
        return super.IsDeadly();
    }

    @Override
    public boolean IsSolid() {
        return true;
    }
}
