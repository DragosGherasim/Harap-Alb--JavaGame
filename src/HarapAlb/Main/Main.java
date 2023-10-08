package HarapAlb.Main;

public class Main {
    public static void main(String[] args){
        Game paooGame = Game.GetInstance("Harap-Alb", 960, 640);
        paooGame.StartGame();
    }
}
