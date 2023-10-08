package HarapAlb.Maps;

import HarapAlb.Graphics.ImageLoader;
import HarapAlb.Main.RefLinks;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Background extends JPanel {
    private final RefLinks refLinks;
    private final Image img;

    public Background(RefLinks rfl, String path) {
        this.refLinks = rfl;

        ImageIcon obj = new ImageIcon(Objects.requireNonNull(ImageLoader.class.getResource(path)));
        this.img = obj.getImage();
    }

    public void Update() {

    }

    public void Draw(Graphics g) {
        super.paintComponent(g);

        g.drawImage(img, 0, 0, 960, 640, null);
    }
}
