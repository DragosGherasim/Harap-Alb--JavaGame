package HarapAlb.Tiles;

import HarapAlb.Graphics.Assets;

public class IceBlockTile extends Tile {
    public IceBlockTile(int idd) {
        super(Assets.iceBlock, idd);

        FRICTION_X = 0.85f;
    }

    public boolean IsDeadly(){
        return super.IsDeadly();
    }

    @Override
    public boolean IsSolid() {
        return true;
    }
}
