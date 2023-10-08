package HarapAlb.Main;

import HarapAlb.Entities.Hero;
import HarapAlb.Graphics.GameCamera;
import HarapAlb.Inputs.KeyboardInputs;
import HarapAlb.Maps.Map;
public class RefLinks {
    private final Game game;
    private Map map;
    private Hero hero;

    public RefLinks(Game game) {
        this.game = game;
    }

    public KeyboardInputs GetKBInputs() {
        return game.GetKBInputs();
    }

    public Game GetGame() {
        return game;
    }

    public Map GetMap() {
        return map;
    }

    public Hero GetHero(){
        return hero;
    }

    public GameCamera GetGameCamera() {
        return game.GetGameCamera();
    }

    public void SetMap(Map map) {
        this.map = map;
    }

    public void SetHero(Hero hero){
        this.hero = hero;
    }
}
