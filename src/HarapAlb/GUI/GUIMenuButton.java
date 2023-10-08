package HarapAlb.GUI;

import java.awt.*;

public class GUIMenuButton extends GUIObject{
    private String text;
    private Point textOffset;
    private final ClickListener clickListener;


    public GUIMenuButton(String text, float x, float y, int width, int height, ClickListener clickListener) {
        super(x, y, width, height);

        this.text = text;
        this.clickListener = clickListener;
        this.textOffset = new Point(58, 19);
    }

    @Override
    public void Update() {

    }

    @Override
    public void Draw(Graphics g) {
        g.setColor(new Color(122, 1, 1, 255));
        if (isHovering){
            ((Graphics2D) g).setStroke(new BasicStroke(5));
            g.drawRect((int)x, (int)y, width, height);
            g.fillRect((int)x, (int)y, width, height);
        }
        else{
            g.fillRect((int)x, (int)y, width, height);
        }

        g.setFont(new Font("Arial", Font.BOLD, 15));
        if (isHovering){
            g.setColor(new Color(107, 105, 105, 255));
        }
        else{
            g.setColor(Color.BLACK);
        }

        g.drawString(text, (int)(x + textOffset.x), (int)(y + textOffset.y));
    }

    @Override
    public void OnClick() {
        clickListener.OnClick();
    }
}
