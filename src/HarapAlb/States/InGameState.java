package HarapAlb.States;

import HarapAlb.Entities.Hero;
import HarapAlb.Graphics.Animation;
import HarapAlb.Graphics.Assets;
import HarapAlb.Main.Game;
import HarapAlb.Main.RefLinks;
import HarapAlb.Maps.Map;
import HarapAlb.Maps.Map1;
import HarapAlb.Maps.Map2;
import HarapAlb.Maps.Map3;

import java.awt.*;

public class InGameState extends State {
    private Map map;
    public static long startTimeGameOver;
    public static long startTimer;
    private boolean isFinished;
    private final Animation animTimer;
    public static String timerText;

    public InGameState(RefLinks rfl) {
        super(rfl);

        this.map = new Map1(refLinks);
        this.refLinks.SetMap(map);
        this.refLinks.SetHero(map.GetHero());

        this.isFinished = false;
        startTimeGameOver = 0;

        this.animTimer = new Animation(rfl, 180, Assets.gameTimer);
    }

    @Override
    public void Update() {
        if (!refLinks.GetMap().GetGate().GetIsOpened() && !Game.GAMEWIN) {
            if (!Hero.GAMEOVER) {
                if (refLinks.GetKBInputs().esc) {
                    State.SetState(new MainMenuState(refLinks));
                }

                map.Update();

                animTimer.Update();

                long currentTime = System.currentTimeMillis();
                long elapsedTime = currentTime - startTimer;

                long seconds = elapsedTime / 1000;

                long minutes = seconds / 60;
                seconds = seconds % 60;
                timerText = String.format("%02d:%02d", minutes, seconds);

            } else {
                Hero.numOfCoins = 0;
                Hero.numOfSmallPotions = 0;
                Hero.numOfBigPotions = 0;

                long currentTime = System.currentTimeMillis();
                long elapsedTime = currentTime - startTimeGameOver;

                if (elapsedTime >= 3000) {
                    isFinished = true;
                }
            }

            if (isFinished) {
                Hero.GAMEOVER = false;
                refLinks.GetGame().SetInGameState(null);
                State.SetState(refLinks.GetGame().GetMenuState());
            }
        }
        else if (map instanceof Map1){
            this.map = new Map2(refLinks);
            this.refLinks.SetMap(map);
            this.refLinks.SetHero(map.GetHero());
        }
        else if (map instanceof Map2){
            this.map = new Map3(refLinks);
            this.refLinks.SetMap(map);
            this.refLinks.SetHero(map.GetHero());
        }
        else if (map instanceof Map3 && refLinks.GetMap().GetGate().GetIsOpened()){
            Game.GAMEWIN = true;

            refLinks.GetMap().GetGate().SetIsOpened(false);
        }
        else if (refLinks.GetKBInputs().esc){
            State.SetState(refLinks.GetGame().GetMenuState());

            Game.gameDB.InsertNewHighScore(IntroState.userName, Game.SCORE);
            Game.gameDB.UpdateByHighScoresDesc();
            refLinks.GetGame().GetMenuState().GetHighScoreTable().UpdateJTable(Game.gameDB.SelectForJTable());

            Game.GAMEWIN = false;
        }
    }

    @Override
    public void Draw(Graphics g) {
        map.Draw(g);

        for (int i = 0; i < Hero.health; ++i){
            g.drawImage(Assets.playerHeart, 14+48*i, 14, 36, 36, null);
        }

        g.drawImage(Assets.coinCounter, 830, 7, 48, 48, null);

        Font font = new Font("Pixel Font", Font.PLAIN, 30);
        g.setFont(font);
        g.setColor(Color.YELLOW);
        g.drawString("x", 890, 41);
        g.drawString(Integer.toString(Hero.numOfCoins), 920, 43);

        for (int i = 0; i < Hero.numOfSmallPotions; ++i){
            g.drawImage(Assets.smallHealthPotion, 832-16*i, 62, 42, 42, null);
        }

        for (int i = 0; i < Hero.numOfBigPotions; ++i){
            g.drawImage(Assets.bigHealthPotion, 902-16*i, 62, 42, 42, null);
        }

        g.drawImage(animTimer.GetCurrentFrame(), refLinks.GetGame().GetWidth()/2 - 80, 12, 40, 40, null);

        font = new Font("Arial", Font.BOLD, 24);
        g.setFont(font);
        g.setColor(Color.WHITE);

        if (timerText != null) {
            g.drawString(timerText, refLinks.GetGame().GetWidth() / 2 - 20, 40);
        }

        if (Game.GAMEWIN){
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(new Color(0, 0, 0, 128)); // Negru cu transparență 128
            g2d.fillRect(0, 0, refLinks.GetGame().GetWidth(), refLinks.GetGame().GetHeight()); // Umplem întreaga fereastră cu dreptunghiul transparent
            g2d.dispose();

            g.drawImage(Assets.gameIsFinishedBanner, refLinks.GetGame().GetWidth()/2 - Assets.gameIsFinishedBanner.getWidth()/2,
                    100, Assets.gameIsFinishedBanner.getWidth(), Assets.gameIsFinishedBanner.getHeight(),null);

            // Setăm fontul și culoarea textului
            font = new Font("Arial", Font.BOLD, 24);
            g.setFont(font);
            g.setColor(Color.WHITE);

            // Calculăm poziția textului în funcție de dimensiunea imaginii bannerului
            int bannerX = refLinks.GetGame().GetWidth() / 2 - Assets.gameIsFinishedBanner.getWidth() / 2;
            int bannerY = 200 + Assets.gameIsFinishedBanner.getHeight(); // Y-ul textului este sub banner cu o decalare de 50 de pixeli

            // Desenăm textul pentru timp și scor
            g.drawString("Your Time : " + timerText, bannerX + 40, bannerY);
            g.drawString("Number of Coins Collected : " + Hero.numOfCoins, bannerX + 40, bannerY+60);
            g.drawString("Your Score : " + Game.SCORE, bannerX + 40, bannerY + 120);

            font = new Font("Pixel Font", Font.PLAIN, 15);
            g.setFont(font);
            g.setColor(Color.WHITE);
            g.drawString("PRESS ESC TO CONTINUE TO MAIN MENU", Assets.gameIsFinishedBanner.getWidth()/2 + 40, refLinks.GetGame().GetHeight() - 50);
        }

        if (Hero.GAMEOVER){
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(new Color(0, 0, 0, 128)); // Negru cu transparență 128
            g2d.fillRect(0, 0, refLinks.GetGame().GetWidth(), refLinks.GetGame().GetHeight()); // Umplem întreaga fereastră cu dreptunghiul transparent
            g2d.dispose();

            g.drawImage(Assets.gameOverBanner, refLinks.GetGame().GetWidth()/2 - Assets.gameOverBanner.getWidth()/2,
                    refLinks.GetGame().GetHeight()/2 - Assets.gameOverBanner.getHeight()/2, Assets.gameOverBanner.getWidth(),
                    Assets.gameOverBanner.getHeight(),null);
        }
    }
}
