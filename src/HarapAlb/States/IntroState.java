package HarapAlb.States;

import HarapAlb.Graphics.Assets;
import HarapAlb.Main.RefLinks;
import HarapAlb.Maps.Background;

import java.awt.*;
public class IntroState extends State{
    private final int startX;
    private int startY;
    private final Background background;
    private long startTime;
    private boolean isFinished;
    public static String userName = "";
    private boolean isUsernameEntered = false;
    public IntroState(RefLinks rfl) {
        super(rfl);

        this.startX = refLinks.GetGame().GetWidth() / 2 - Assets.introBanner.getWidth() / 2;
        this.startY = 0;

        this.background = new Background(refLinks, "/Backgrounds/introState_background.jpg");

        this.startTime = System.currentTimeMillis();

        this.isFinished = false;
    }

    private void SimpleAnimation(){
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - startTime;

        if (elapsedTime <= 3000) { // Animăm bannerul timp de 3 secunde
            float progress = (float) elapsedTime / 3000*0.25f; // Progresul animației
            startY = (int) (progress * refLinks.GetGame().GetHeight()); // Actualizăm poziția pe baza progresului
        }
        else{
            isFinished = true;
        }
    }

    @Override
    public void Update() {
        SimpleAnimation();

        if (isFinished){
            refLinks.GetKBInputs().UpdateInputs();

            if (!isUsernameEntered) {
                String input = refLinks.GetKBInputs().GetTypedText();
                if (input != null) {
                    userName += input;
                }

                refLinks.GetKBInputs().SetKeysFalse();

                if (refLinks.GetKBInputs().enter) {
                    isUsernameEntered = true;
                    // Aici puteți adăuga logica pentru a înregistra username-ul introdus de utilizator
                }
            }

            if (refLinks.GetKBInputs().space) {
                refLinks.GetGame().SetMenuState(new MainMenuState(refLinks));
                State.SetState(refLinks.GetGame().GetMenuState());
            }
        }
    }

    @Override
    public void Draw(Graphics g) {
        background.Draw(g);

        g.drawImage(Assets.introBanner, startX, startY, Assets.introBanner.getWidth(), Assets.introBanner.getHeight(), null);

        if (isFinished){
            // Desenarea câmpului de introducere a textului
            Font font = new Font("Pixel Font", Font.PLAIN, 20);
            g.setFont(font);
            g.setColor(Color.WHITE);
            g.drawString("Enter Username: " + userName, startX + Assets.introBanner.getWidth() / 2 - 120, startY + Assets.introBanner.getHeight() + 50);

            font = new Font("Pixel Font", Font.PLAIN, 17);
            g.setFont(font);
            g.setColor(Color.WHITE);

            if (!isUsernameEntered) {
                g.drawString("Press Enter To Submit", startX+Assets.introBanner.getWidth()/2-90, startY+Assets.introBanner.getHeight()+210);
            } else {
                g.drawString("Username Entered: " + userName + " -> PRESS SPACE TO CONTINUE", startX+Assets.introBanner.getWidth()/2-220, startY+Assets.introBanner.getHeight()+210);
            }
        }
    }
}
