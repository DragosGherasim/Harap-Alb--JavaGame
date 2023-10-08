package HarapAlb.SQLite;

public class HighScore implements Comparable<HighScore> {
    private String userName;
    private int highScore;

    public HighScore(String userName, int highScore){
        this.userName = userName;
        this.highScore = highScore;
    }

    public String GetUserName(){
        return userName;
    }

    public int GetHighScore(){
        return highScore;
    }

    @Override
    public int compareTo(HighScore o) {
        return highScore - o.highScore;
    }
}
