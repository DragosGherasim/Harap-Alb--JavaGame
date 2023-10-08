package HarapAlb.Tiles;

import HarapAlb.Graphics.Assets;

public class SpikesTile extends Tile {
    public SpikesTile(int idd) {
        super(Assets.spikes, idd);

        FRICTION_X = 0.0f;
    }

    public boolean IsDeadly(){
        return true;
    }

    @Override
    public boolean IsSolid() {
        return true;
    }
}
