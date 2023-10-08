package HarapAlb.GUI;

import HarapAlb.Main.RefLinks;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GUIManager {
    private RefLinks refLinks;
    private ArrayList<GUIObject> guiObjects;

    public GUIManager(RefLinks refLinks){
        this.refLinks = refLinks;

        this.guiObjects = new ArrayList<>();
    }

    public void Update(){
        for(GUIObject currentGUIObj : guiObjects){
            currentGUIObj.Update();
        }
    }

    public void Draw(Graphics g){
        for(GUIObject currentGUIObj : guiObjects){
            currentGUIObj.Draw(g);
        }
    }

    public void OnMouseMove(MouseEvent e){
        for(GUIObject currentGUIObj : guiObjects){
            currentGUIObj.OnMouseMove(e);
        }
    }

    public void OnMouseRelease(MouseEvent e){
        for(GUIObject currentGUIObj : guiObjects){
            currentGUIObj.OnMouseRelease(e);
        }
    }

    public void AddGUIObject(GUIObject guiObject){
        guiObjects.add(guiObject);
    }
}
