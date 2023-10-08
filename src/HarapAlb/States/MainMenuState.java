package HarapAlb.States;

import HarapAlb.GUI.GUIManager;
import HarapAlb.GUI.GUIMenuButton;
import HarapAlb.Graphics.Assets;
import HarapAlb.Main.RefLinks;
import HarapAlb.Maps.Background;
import HarapAlb.SQLite.JTableHighScores;

import javax.swing.*;
import java.awt.*;

public class MainMenuState extends State{
    private static GUIManager guiManager;
    private JTableHighScores highScoresTable;
    private final Background background;

    public MainMenuState(RefLinks rfl) {
        super(rfl);

        this.background = new Background(refLinks, "/Backgrounds/mainMenuState_background.png");
        guiManager = new GUIManager(refLinks);

        refLinks.GetGame().GetMouseInputs().SetGUIManager(guiManager);

        highScoresTable = JTableHighScores.GetInstance(refLinks);

        guiManager.AddGUIObject(new GUIMenuButton("NEW GAME", (float) Assets.mainMenuBanner.getWidth() / 2, 190, 200, 28, () -> {
            refLinks.GetGame().SetInGameState(new InGameState(refLinks));
            State.SetState(refLinks.GetGame().GetInGameState());
        }));

        int offsetXIfGameStateExists = 1;

        if (refLinks.GetGame().GetInGameState() != null) {
            guiManager.AddGUIObject(new GUIMenuButton("RETURN", (float) Assets.mainMenuBanner.getWidth() / 2, 230, 200, 28, () -> {
                State.SetState(refLinks.GetGame().GetInGameState());
            }));

            ++offsetXIfGameStateExists;
        }

        guiManager.AddGUIObject(new GUIMenuButton("LEADERBOARD", (float) Assets.mainMenuBanner.getWidth() / 2, 190 + 40*offsetXIfGameStateExists, 200, 28, () -> {
            SwingUtilities.invokeLater(() -> {
                highScoresTable = JTableHighScores.GetInstance(refLinks);
                highScoresTable.Open();
            });
        }));

        guiManager.AddGUIObject(new GUIMenuButton("EXIT GAME", (float) Assets.mainMenuBanner.getWidth() / 2, 190 + 80*offsetXIfGameStateExists, 200, 28, () -> {
            System.exit(0);
        }));
    }

    @Override
    public void Update() {
        guiManager.Update();
    }

    @Override
    public void Draw(Graphics g) {
        background.Draw(g);

        g.drawImage(Assets.mainMenuBanner, 100, 100, Assets.mainMenuBanner.getWidth(), Assets.mainMenuBanner.getHeight(), null);


        guiManager.Draw(g);
    }

    public JTableHighScores GetHighScoreTable(){
        return highScoresTable;
    }
}
