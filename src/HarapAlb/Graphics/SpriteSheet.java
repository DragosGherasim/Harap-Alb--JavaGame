package HarapAlb.Graphics;

import java.awt.image.BufferedImage;

public class SpriteSheet {
    public static  int tileWidth = 32;
    public static int tileHeight = 32;
    private final BufferedImage spriteSheet;

    public SpriteSheet(BufferedImage buffImg) {
        this.spriteSheet = buffImg;
    }

    public BufferedImage crop(int x, int y) {
        return spriteSheet.getSubimage(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
    }
}
