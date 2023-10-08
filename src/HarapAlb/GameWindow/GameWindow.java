package HarapAlb.GameWindow;

import javax.swing.*;
import java.awt.*;

public class GameWindow {
    private final String wndTitle;
    private final int wndWidth;
    private final int wndHeight;
    private JFrame wndFrame;    // fereastra principala a jocului
    private Canvas canvas;      // panel in care se poate desena

    public GameWindow(String title, int width, int height) {
        this.wndTitle = title;
        this.wndWidth = width;
        this.wndHeight = height;

        this.wndFrame = null;
    }

    public void BuildGameWindow() {
        if (wndFrame != null) {
            return;
        }

        wndFrame = new JFrame(wndTitle);
        wndFrame.setSize(wndWidth, wndHeight);
        /// Operatia de inchidere (fereastra sa poata fi inchisa atunci cand
        /// este apasat butonul x din dreapta sus al ferestrei). Totodata acest
        /// lucru garanteaza ca nu doar fereastra va fi inchisa ci intregul
        /// program
        wndFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wndFrame.setResizable(false);
        // Fereastra apare pe centrul ecranului
        wndFrame.setLocationRelativeTo(null);
        /// Implicit o fereastra cand este creata nu este vizibila motiv pentru
        /// care trebuie setata aceasta proprietate
        wndFrame.setVisible(true);

        canvas = new Canvas();

        canvas.requestFocus();

        /// In aceeasi maniera trebuiesc setate proprietatile pentru acest obiect
        /// canvas (panza): dimensiuni preferabile, minime, maxime etc.
        //canvas.setPreferredSize(new Dimension(wndWidth, wndHeight));
        canvas.setPreferredSize(new Dimension(wndWidth, wndHeight));
        canvas.setMaximumSize(new Dimension(wndWidth, wndHeight));
        canvas.setMinimumSize(new Dimension(wndWidth, wndHeight));
        /// Avand in vedere ca obiectul de tip canvas, proaspat creat, nu este automat
        /// adaugat in fereastra trebuie apelata metoda add a obiectul wndFrame
        wndFrame.add(canvas);
        /// Urmatorul apel de functie are ca scop eventuala redimensionare a ferestrei
        /// ca tot ce contine sa poate fi afisat complet
        wndFrame.pack();
    }

    public int GetWndWidth() {
        return wndWidth;
    }

    public int GetWndHeight() {
        return wndHeight;
    }

    public Canvas GetCanvas() {
        return canvas;
    }
}
