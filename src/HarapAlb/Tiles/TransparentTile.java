package HarapAlb.Tiles;

import HarapAlb.Graphics.Assets;

public class TransparentTile extends Tile {
    public TransparentTile(int idd) {
        super(Assets.transparentTile, idd);

        FRICTION_X = 1.0f;
    }

    public boolean IsDeadly(){
        return super.IsDeadly();
    }

    public boolean IsSolid() {
        return super.IsSolid();
    }
}
