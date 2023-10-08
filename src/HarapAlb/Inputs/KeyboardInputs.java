package HarapAlb.Inputs;

import HarapAlb.States.IntroState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {
    private boolean[] keys;
    public boolean right, left, up, down, space, ctrl, o, one, two, e, esc, enter, test;

    public KeyboardInputs() {
        this.keys = new boolean[256];
    }

    public void UpdateInputs() {
        right = keys[KeyEvent.VK_RIGHT];
        left = keys[KeyEvent.VK_LEFT];
        up = keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_DOWN];
        space = keys[KeyEvent.VK_SPACE];
        one = keys[KeyEvent.VK_1];
        two = keys[KeyEvent.VK_2];
        e = keys[KeyEvent.VK_E];
        esc = keys[KeyEvent.VK_ESCAPE];
        enter = keys[KeyEvent.VK_ENTER];

        ctrl = keys[KeyEvent.VK_CONTROL];
        o = keys[KeyEvent.VK_O];
    }

    public void SetKeysFalse() {
       keys = new boolean[256];
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        keys[keyCode] = false;

        if (keyCode == KeyEvent.VK_BACK_SPACE){
            if (IntroState.userName.length() > 0) {
                IntroState.userName = IntroState.userName.substring(0,  IntroState.userName.length()-1);
            }
        }
    }

    public String GetTypedText() {
        StringBuilder typedText = new StringBuilder();
        for (int i = KeyEvent.VK_A; i <= KeyEvent.VK_Z; i++) {
            if (keys[i]) {
                typedText.append((char) ('a' + (i - KeyEvent.VK_A)));
            }
        }
        if (keys[KeyEvent.VK_BACK_SPACE] && test){
            typedText.deleteCharAt(typedText.length()-1);
        }
        if (keys[KeyEvent.VK_SPACE]) {
            typedText.append(" ");
        }
        return typedText.toString();
    }
}
