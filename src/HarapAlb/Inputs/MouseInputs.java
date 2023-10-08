package HarapAlb.Inputs;

import HarapAlb.GUI.GUIManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseListener, MouseMotionListener {
    private boolean leftClickPressed;
    private int mouseX, mouseY;
    private GUIManager guiManager;

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1){
            leftClickPressed = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1){
            leftClickPressed = false;
        }

        if (guiManager != null){
            guiManager.OnMouseRelease(e);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

        if (guiManager != null){
            guiManager.OnMouseMove(e);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    public void SetGUIManager(GUIManager guiManager){
        this.guiManager = guiManager;
    }
}
