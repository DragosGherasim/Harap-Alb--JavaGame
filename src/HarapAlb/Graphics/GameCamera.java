package HarapAlb.Graphics;

import HarapAlb.Entities.Entity;
import HarapAlb.Main.RefLinks;
import HarapAlb.Tiles.Tile;

public class GameCamera {
    private final RefLinks refLinks;
    private float xOffs, yOffs;

    public GameCamera(RefLinks rfl, float x, float y){
        this.refLinks = rfl;
        this.xOffs = x;
        this.yOffs = y;
    }

    public void CenterOn(Entity e){
        xOffs = e.GetX()- (float) refLinks.GetGame().GetWidth() /2;
        yOffs = e.GetY()- (float) refLinks.GetGame().GetHeight() /2;

        removeBlackSpace();
    }

    public void removeBlackSpace() {
        if (xOffs < 0){
            xOffs = 0;
        }
        else if (xOffs > refLinks.GetMap().GetWidth()* Tile.TILE_WIDTH-refLinks.GetGame().GetWidth()){
            xOffs = refLinks.GetMap().GetWidth()*Tile.TILE_WIDTH-refLinks.GetGame().GetWidth();
        }

        if (yOffs < 0){
            yOffs = 0;
        }
        else if(yOffs > refLinks.GetMap().GetHeight()*Tile.TILE_HEIGHT-refLinks.GetGame().GetHeight()){
            yOffs = refLinks.GetMap().GetHeight()*Tile.TILE_HEIGHT-refLinks.GetGame().GetHeight();
        }
    }

    public float GetXOffs() {
        return xOffs;
    }

    public float GetYOffs() {
        return yOffs;
    }
}
