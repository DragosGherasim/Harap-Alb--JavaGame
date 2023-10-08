package HarapAlb.SQLite;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GameDB {
    public static Connection conn = null;
    public static Statement stmt = null;

    public GameDB(){
        try{
            String dbname = "org.sqlite.JDBC";
            Class.forName(dbname);

            String dbPath = "jdbc:sqlite:HarapAlb.db";
            conn = DriverManager.getConnection(dbPath);
            stmt = conn.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS HighScores" +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "UserName TEXT NOT NULL," +
                    "HighScore INT NOT NULL)";

            stmt.execute(sql);
        }
        catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        System.out.println("GameDB objected created successfully");
    }

    public void InsertNewHighScore(String userName, int highScore) {
        try{
            conn.setAutoCommit(false);
            String sql = "INSERT OR IGNORE INTO HighScores (UserName, HighScore) VALUES (?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userName);
            pstmt.setInt(2, highScore);

            pstmt.executeUpdate();
            pstmt.close();
            conn.commit();
        }
        catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        System.out.println("Insertion in HighScores table was successfully");
    }

    public ArrayList<HighScore> SelectForJTable(){
        ArrayList<HighScore> list = new ArrayList<>();

        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM HighScores");

            while (rs.next()){
                String userName = rs.getString("UserName");
                int highScore = rs.getInt("HighScore");

                list.add(new HighScore(userName, highScore));
            }

            rs.close();
            stmt.close();
        }
        catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        System.out.println("SelectForJTable operation successfully");

        return list;
    }

    public void UpdateByHighScoresDesc(){
        List<HighScore> highScoresList = new ArrayList<>();

        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM HighScores");

            while (rs.next()){
                highScoresList.add(new HighScore(rs.getString("UserName"), rs.getInt("HighScore")));
            }
            rs.close();
            stmt.close();

            highScoresList.sort(Comparator.reverseOrder());

            String sql = "UPDATE HighScores SET UserName = ?, HighScore = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            int id = 1;
            for (HighScore currentHighScore : highScoresList){
                pstmt.setString(1, currentHighScore.GetUserName());
                pstmt.setInt(2,currentHighScore.GetHighScore());

                pstmt.setInt(3, id++);
                pstmt.executeUpdate();
            }
            pstmt.close();
            conn.commit();
        }
        catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("UpdateByHighScoresDesc operation successfully");
    }
}
