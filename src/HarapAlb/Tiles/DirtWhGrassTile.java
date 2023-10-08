package HarapAlb.Tiles;

import HarapAlb.Graphics.Assets;

public class DirtWhGrassTile extends Tile {
    public DirtWhGrassTile(int idd) {
        super(Assets.dirtWhGrass, idd);

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
