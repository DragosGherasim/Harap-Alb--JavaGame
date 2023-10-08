package HarapAlb.SQLite;

import HarapAlb.Main.RefLinks;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class JTableHighScores {
    private static JTableHighScores instance;
    private final JFrame f;
    private final DefaultTableModel model;

    private RefLinks refLinks;

    private JTableHighScores(RefLinks rfl) {
        this.refLinks = rfl;

        this.f = new JFrame();
        f.setTitle("HighScore LeaderBoard");

        String[] colHeads = {"UserName", "HighScore"};
        String[][] data = {};

        this.model = new DefaultTableModel(data, colHeads);

        refLinks.GetGame().GetGameDB().UpdateByHighScoresDesc();

        for(HighScore currentHighScore : refLinks.GetGame().GetGameDB().SelectForJTable()){
            model.addRow(new Object[]{currentHighScore.GetUserName(), currentHighScore.GetHighScore()});
        }

        JTable j = new JTable(model);
        f.add(new JScrollPane(j)); // pentru a permite derularea în cazul în care există prea multe înregistrări

        j.setBounds(30, 40, 800, 300);

        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                f.dispose(); // Close the JFrame and release all resources
            }
        });

        f.pack(); // Dimensionați fereastra în funcție de conținutul său
        f.setLocationRelativeTo(null); // Centrați fereastra pe ecran
    }

    public static JTableHighScores GetInstance(RefLinks refLinks) {
        if (instance == null) {
            instance = new JTableHighScores(refLinks);
        }
        return instance;
    }

    public void Open() {
        f.setVisible(true);
    }

    public void UpdateJTable(ArrayList<HighScore> highScoresList){
        int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }

        for(HighScore currentHighScore : highScoresList){
            model.addRow(new Object[]{currentHighScore.GetUserName(), currentHighScore.GetHighScore()});
        }
    }
}
