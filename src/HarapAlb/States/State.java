package HarapAlb.States;

import HarapAlb.Main.RefLinks;

import java.awt.*;

public abstract class State {
    private static State previousState = null;
    private static State currentState = null;

    protected RefLinks refLinks;

    public State(RefLinks rfl) {
        this.refLinks = rfl;
    }

    public static void SetState(State state) {
        previousState = currentState;
        currentState = state;
    }

    public static State GetState() {
        return currentState;
    }

    public abstract void Update();

    public abstract void Draw(Graphics g);
}
