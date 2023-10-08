package HarapAlb.GUI;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class GUIObject {
    protected float x, y;
    protected int width, height;
    protected Rectangle bounds;
    protected boolean isHovering;

    public GUIObject(float x, float y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        bounds = new Rectangle((int) x, (int) y, width, height);
    }

    public abstract void Update();
    public abstract void Draw(Graphics g);
    public abstract void OnClick();

    public void OnMouseMove(MouseEvent e){
        isHovering = bounds.contains(e.getX(), e.getY());
    }

    public void OnMouseRelease(MouseEvent e){
        if(isHovering){
            OnClick();
        }
    }
}
