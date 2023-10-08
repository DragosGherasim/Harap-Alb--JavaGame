package HarapAlb.Graphics;

import java.awt.image.BufferedImage;

public class Assets {
    public static BufferedImage dirtWhGrass;
    public static BufferedImage dirt;
    public static BufferedImage bedRock;
    public static BufferedImage stoneBrick;
    public static BufferedImage brick;
    public static BufferedImage cobbleStone;
    public static BufferedImage cobbleStoneWhSnow;
    public static BufferedImage spikes;
    public static BufferedImage iceBlock;
    public static BufferedImage transparentTile;
    public static BufferedImage[] playerIdleRight;
    public static BufferedImage[] playerIdleLeft;
    public static BufferedImage[] playerRunRight;
    public static BufferedImage[] playerRunLeft;
    public static BufferedImage[] playerJumpRight;
    public static BufferedImage[] playerJumpLeft;
    public static BufferedImage[] playerAttackRight;
    public static BufferedImage[] playerAttackLeft;
    public static BufferedImage[] playerDamageRight;
    public static BufferedImage[] playerDamageLeft;
    public static BufferedImage[] playerDeadRight;
    public static BufferedImage[] playerDeadLeft;
    public static BufferedImage playerHeart;

    public static BufferedImage[] ratIdleRight;
    public static BufferedImage[] ratIdleLeft;
    public static BufferedImage[] ratRunRight;
    public static BufferedImage[] ratRunLeft;
    public static BufferedImage[] ratAttackRight;
    public static BufferedImage[] ratAttackLeft;
    public static BufferedImage[] ratDamageRight;
    public static BufferedImage[] ratDamageLeft;
    public static BufferedImage[] ratDeadRight;
    public static BufferedImage[] ratDeadLeft;

    public static BufferedImage[] goblinIdleRight;
    public static BufferedImage[] goblinIdleLeft;
    public static BufferedImage[] goblinRunRight;
    public static BufferedImage[] goblinRunLeft;
    public static BufferedImage[] goblinAttackRight;
    public static BufferedImage[] goblinAttackLeft;
    public static BufferedImage[] goblinDamageRight;
    public static BufferedImage[] goblinDamageLeft;
    public static BufferedImage[] goblinDeadRight;
    public static BufferedImage[] goblinDeadLeft;

    public static BufferedImage[] coin;
    public static BufferedImage coinCounter;

    public static BufferedImage[] chest;

    public static BufferedImage smallHealthPotion;
    public static BufferedImage bigHealthPotion;

    public static BufferedImage map1Gate;
    public static BufferedImage map2Gate;
    public static BufferedImage map3Gate;

    public static BufferedImage[] silverKeyAnim;
    public static BufferedImage[] goldKeyAnim;
    public static BufferedImage[] mythrilKeyAnim;
    public static BufferedImage silverKey;
    public static BufferedImage goldKey;
    public static BufferedImage mythrilKey;

    public static BufferedImage introBanner;
    public static BufferedImage mainMenuBanner;
    public static BufferedImage gameOverBanner;
    public static BufferedImage gameIsFinishedBanner;
    public static BufferedImage[] gameTimer;

    public static void Init() {
        /// Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader
        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/Levels/TileSheet.png"));

        /// Se obtin subimaginile corespunzatoare elementelor necesare.
        dirtWhGrass = sheet.crop(0, 0);
        dirt = sheet.crop(1, 0);
        bedRock = sheet.crop(2, 0);
        stoneBrick = sheet.crop(3, 0);
        brick = sheet.crop(4, 0);
        cobbleStone = sheet.crop(5, 0);
        cobbleStoneWhSnow = sheet.crop(6, 0);
        spikes = sheet.crop(7, 0);
        iceBlock = sheet.crop(0, 1);
        transparentTile = sheet.crop(1, 1);

        sheet = new SpriteSheet(ImageLoader.LoadImage("/Player/PlayerSheet.png"));

        playerIdleRight = new BufferedImage[8];
        for (int i = 0; i < 8; ++i) {
            playerIdleRight[i] = sheet.crop(i, 0);
        }
        playerIdleLeft = new BufferedImage[8];
        for (int i = 0; i < 8; ++i) {
            playerIdleLeft[i] = sheet.crop(i, 1);
        }

        playerRunRight = new BufferedImage[5];
        for (int i = 0; i < 5; ++i) {
            playerRunRight[i] = sheet.crop(i, 2);
        }
        playerRunLeft = new BufferedImage[5];
        for (int i = 0; i < 5; ++i) {
            playerRunLeft[i] = sheet.crop(i, 3);
        }

        playerJumpRight = new BufferedImage[4];
        for (int i = 0; i < 4; ++i) {
            playerJumpRight[i] = sheet.crop(i, 4);
        }
        playerJumpLeft = new BufferedImage[4];
        for (int i = 0; i < 4; ++i) {
            playerJumpLeft[i] = sheet.crop(i, 5);
        }

        playerAttackRight = new BufferedImage[5];
        for (int i = 0; i < 5; ++i) {
            playerAttackRight[i] = sheet.crop(i, 6);
        }
        playerAttackLeft = new BufferedImage[5];
        for (int i = 0; i < 5; ++i) {
            playerAttackLeft[i] = sheet.crop(i, 7);
        }

        playerDamageRight = new BufferedImage[3];
        for (int i = 0; i < 3; ++i) {
            playerDamageRight[i] = sheet.crop(i, 8);
        }
        playerDamageLeft = new BufferedImage[3];
        for (int i = 0; i < 3; ++i) {
            playerDamageLeft[i] = sheet.crop(i, 9);
        }

        playerDeadRight = new BufferedImage[8];
        for (int i = 0; i < 8; ++i) {
            playerDeadRight[i] = sheet.crop(i, 10);
        }
        playerDeadLeft = new BufferedImage[8];
        for (int i = 0; i < 8; ++i) {
            playerDeadLeft[i] = sheet.crop(i, 11);
        }

        sheet = new SpriteSheet(ImageLoader.LoadImage("/Enemy/GoblinSheet.png"));

        goblinIdleRight = new BufferedImage[8];
        for (int i = 0; i < 8; ++i) {
            goblinIdleRight[i] = sheet.crop(i, 0);
        }
        goblinIdleLeft = new BufferedImage[8];
        for (int i = 0; i < 8; ++i) {
            goblinIdleLeft[i] = sheet.crop(i, 1);
        }

        goblinRunRight = new BufferedImage[6];
        for (int i = 0; i < 6; ++i) {
            goblinRunRight[i] = sheet.crop(i, 2);
        }
        goblinRunLeft = new BufferedImage[6];
        for (int i = 0; i < 6; ++i) {
            goblinRunLeft[i] = sheet.crop(i, 3);
        }

        goblinAttackRight = new BufferedImage[7];
        for (int i = 0; i < 7; ++i) {
            goblinAttackRight[i] = sheet.crop(i, 6);
        }
        goblinAttackLeft = new BufferedImage[7];
        for (int i = 0; i < 7; ++i) {
            goblinAttackLeft[i] = sheet.crop(i, 7);
        }

        goblinDamageRight = new BufferedImage[3];
        for (int i = 0; i < 3; ++i) {
            goblinDamageRight[i] = sheet.crop(i, 8);
        }
        goblinDamageLeft = new BufferedImage[3];
        for (int i = 0; i < 3; ++i) {
            goblinDamageLeft[i] = sheet.crop(i, 9);
        }

        goblinDeadRight = new BufferedImage[9];
        for (int i = 0; i < 9; ++i) {
            goblinDeadRight[i] = sheet.crop(i, 10);
        }
        goblinDeadLeft = new BufferedImage[9];
        for (int i = 0; i < 9; ++i) {
            goblinDeadLeft[i] = sheet.crop(i, 11);
        }

        sheet = new SpriteSheet(ImageLoader.LoadImage("/Enemy/RatSheet.png"));

        ratIdleRight = new BufferedImage[7];
        for (int i = 0; i < 7; ++i) {
            ratIdleRight[i] = sheet.crop(i, 0);
        }
        ratIdleLeft = new BufferedImage[7];
        for (int i = 0; i < 7; ++i) {
            ratIdleLeft[i] = sheet.crop(i, 1);
        }

        ratRunRight = new BufferedImage[5];
        for (int i = 0; i < 5; ++i) {
            ratRunRight[i] = sheet.crop(i, 2);
        }
        ratRunLeft = new BufferedImage[5];
        for (int i = 0; i < 5; ++i) {
            ratRunLeft[i] = sheet.crop(i, 3);
        }

        ratAttackRight = new BufferedImage[6];
        for (int i = 0; i < 6; ++i) {
            ratAttackRight[i] = sheet.crop(i, 6);
        }
        ratAttackLeft = new BufferedImage[6];
        for (int i = 0; i < 6; ++i) {
            ratAttackLeft[i] = sheet.crop(i, 7);
        }

        ratDamageRight = new BufferedImage[3];
        for (int i = 0; i < 3; ++i) {
            ratDamageRight[i] = sheet.crop(i, 8);
        }
        ratDamageLeft = new BufferedImage[3];
        for (int i = 0; i < 3; ++i) {
            ratDamageLeft[i] = sheet.crop(i, 9);
        }

        ratDeadRight = new BufferedImage[9];
        for (int i = 0; i < 9; ++i) {
            ratDeadRight[i] = sheet.crop(i, 10);
        }
        ratDeadLeft = new BufferedImage[9];
        for (int i = 0; i < 9; ++i) {
            ratDeadLeft[i] = sheet.crop(i, 11);
        }

        sheet = new SpriteSheet(ImageLoader.LoadImage("/Player/Heart.png"));

        playerHeart = sheet.crop(0,0);

        sheet = new SpriteSheet(ImageLoader.LoadImage("/Items/CoinSheet.png"));

        coin = new BufferedImage[6];
        for (int i = 0; i < 6; ++i){
            coin[i] = sheet.crop(i, 0);
        }

        coinCounter = sheet.crop(0,0);

        SpriteSheet.tileHeight = 48;
        SpriteSheet.tileWidth = 48;
        sheet = new SpriteSheet(ImageLoader.LoadImage("/Items/ChestSheet.png"));

        chest = new BufferedImage[8];
        for (int i = 0; i < 8; ++i){
            chest[i] = sheet.crop(i, 0);
        }

        SpriteSheet.tileHeight = 32;
        SpriteSheet.tileWidth = 32;
        sheet = new SpriteSheet(ImageLoader.LoadImage("/Items/SmallHealthPotion.png"));

        smallHealthPotion = sheet.crop(0, 0);

        sheet = new SpriteSheet(ImageLoader.LoadImage("/Items/BigHealthPotion.png"));

        bigHealthPotion = sheet.crop(0, 0);

        SpriteSheet.tileHeight = 16;
        SpriteSheet.tileWidth = 16;
        sheet = new SpriteSheet(ImageLoader.LoadImage("/Items/Gates.png"));

        map1Gate = sheet.crop(1, 0);
        map2Gate = sheet.crop(2, 0);
        map3Gate = sheet.crop(3, 0);

        SpriteSheet.tileHeight = 16;
        SpriteSheet.tileWidth = 16;
        sheet = new SpriteSheet(ImageLoader.LoadImage("/Items/SilverKeySheet.png"));

        silverKeyAnim = new BufferedImage[8];
        int j = 0;
        for (int z = 0; z < 2; ++z){
            for (int i = 0; i < 4; ++i){
                silverKeyAnim[j] = sheet.crop(i, z);
                ++j;
            }
        }

        silverKey = sheet.crop(0, 0);

        sheet = new SpriteSheet(ImageLoader.LoadImage("/Items/GoldKeySheet.png"));

        goldKeyAnim = new BufferedImage[8];
        j = 0;
        for (int z = 0; z < 2; ++z){
            for (int i = 0; i < 4; ++i){
                goldKeyAnim[j] = sheet.crop(i, z);
                ++j;
            }
        }

        goldKey = sheet.crop(0, 0);

        sheet = new SpriteSheet(ImageLoader.LoadImage("/Items/MythrilKeySheet.png"));

        mythrilKeyAnim = new BufferedImage[8];
        j = 0;
        for (int z = 0; z < 2; ++z){
            for (int i = 0; i < 4; ++i){
                mythrilKeyAnim[j] = sheet.crop(i, z);
                ++j;
            }
        }

        mythrilKey = sheet.crop(0, 0);

        SpriteSheet.tileHeight = 89;
        SpriteSheet.tileWidth = 462;
        sheet = new SpriteSheet(ImageLoader.LoadImage("/States/intro_banner.png"));

        introBanner = sheet.crop(0, 0);

        SpriteSheet.tileHeight = 71;
        SpriteSheet.tileWidth = 276;
        sheet = new SpriteSheet(ImageLoader.LoadImage("/States/mainMenu_banner.png"));

        mainMenuBanner = sheet.crop(0, 0);

        SpriteSheet.tileHeight = 69;
        SpriteSheet.tileWidth = 410;
        sheet = new SpriteSheet(ImageLoader.LoadImage("/States/gameOver_banner.png"));
        gameOverBanner = sheet.crop(0, 0);

        SpriteSheet.tileHeight = 135;
        SpriteSheet.tileWidth = 563;
        sheet = new SpriteSheet(ImageLoader.LoadImage("/States/gameIsFinished_banner.png"));
        gameIsFinishedBanner = sheet.crop(0, 0);

        SpriteSheet.tileHeight = 16;
        SpriteSheet.tileWidth = 16;
        sheet = new SpriteSheet(ImageLoader.LoadImage("/Items/GameTimerSheet.png"));

        gameTimer = new BufferedImage[8];
        for (int i = 0; i < 8; ++i) {
            gameTimer[i] = sheet.crop(i, 0);
        }
    }
}
