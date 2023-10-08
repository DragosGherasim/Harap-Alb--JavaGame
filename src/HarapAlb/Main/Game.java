package HarapAlb.Main;

import HarapAlb.Entities.Hero;
import HarapAlb.GameWindow.GameWindow;
import HarapAlb.Graphics.Assets;
import HarapAlb.Graphics.GameCamera;
import HarapAlb.Inputs.KeyboardInputs;
import HarapAlb.Inputs.MouseInputs;
import HarapAlb.SQLite.GameDB;
import HarapAlb.States.InGameState;
import HarapAlb.States.IntroState;
import HarapAlb.States.MainMenuState;
import HarapAlb.States.State;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferStrategy;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game implements Runnable {
    // Singleton
    private static Game gameInstance = null;
    private final KeyboardInputs keyboardIn;
    private final MouseInputs mouseInputs;
    private final GameWindow wnd;
    private boolean runState;   // flag pentru starea firului de executie
    private Thread gameThread;  // referinta catre thread-ul de update si draw al ferestrei
    private BufferStrategy bs;  // referinta catre un mecanism cu care se organizeaza memoria pentru canvas
    private Graphics g;         // referinta catre un context grafic
    private RefLinks refLinks;
    private GameCamera gameCamera;
    private InGameState inGameState;
    private MainMenuState menuState;
    public static GameDB gameDB;
    public static boolean OverlayDisplay;
    public static boolean GAMEWIN;
    public static int SCORE;

    private Game(String title, int width, int height) {
        this.wnd = new GameWindow(title, width, height);

        // Resetarea flagului (started/stopped)
        this.runState = false;

        OverlayDisplay = false;

        this.keyboardIn = new KeyboardInputs();
        this.mouseInputs = new MouseInputs();

        this.inGameState = null;
        this.menuState = null;

        SCORE = 0;

        GAMEWIN = false;
    }

    public static Game GetInstance(String title, int width, int height) {
        if (gameInstance == null) {
            gameInstance = new Game(title, width, height);
        }

        return gameInstance;
    }

    private void InitGame(){
        wnd.BuildGameWindow();

        wnd.GetCanvas().addKeyListener(keyboardIn);

        wnd.GetCanvas().addMouseListener(mouseInputs);
        wnd.GetCanvas().addMouseMotionListener(mouseInputs);

        wnd.GetCanvas().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                keyboardIn.SetKeysFalse();
            }
        });

        Assets.Init();

        refLinks = new RefLinks(this);
        gameCamera = new GameCamera(refLinks, 0, 0);

        State.SetState(new IntroState(refLinks));

        gameDB = new GameDB();
    }

    /// Functia va rula thread-ul creat.
    /// Aceasta functie va actualiza starea jocului si va redesena tabla de joc (va actualiza fereastra grafica)
    @Override
    public void run() {
        InitGame();

        long previousTime = System.nanoTime();                       // retine timpul in nanosecunde aferent frame-ului anterior

        // Apelul functiilor Update() & Draw() trebuie sa fie realizat la fiecare 16.7 ms (60 ori pe sec)
        final int framesPerSecond = 60;
        final int updatesPerSecond = 60;
        final double timePerFrame = (double) 1000000000 / framesPerSecond;  // durata unui frame in nanosecunde
        final double timePerUpdate = (double) 1000000000 / updatesPerSecond;  // durata unui update in nanosecunde

        double deltaU = 0;
        double deltaF = 0;

        // Atat timp cat thread-ul este pornit : Update() & Draw()
        while (runState) {
            long currentTime = System.nanoTime();
            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                Update();
                deltaU--;
            }

            if (deltaF >= 1) {
                Draw();
                deltaF--;
            }
        }
    }

    /// Creeaza si starteaza firul separat de executie (thread).
    /// Metoda trebuie sa fie declarata synchronized pentru ca apelul acesteia sa fie semaforizat.
    public synchronized void StartGame() {
        if (!runState) {
            runState = true;

            // Thread-ul creat va executa functia run() suprascrisa in clasa Game.
            gameThread = new Thread(this);
            gameThread.start();
        }
        else {
            // Thread-ul este creat si deja pornit
        }
    }

    // Actualizeaza store elementelor din joc
    private void Update() {
        keyboardIn.UpdateInputs();

        if (keyboardIn.o && keyboardIn.ctrl){
            OverlayDisplay = !OverlayDisplay;
            keyboardIn.SetKeysFalse();
        }

        if (State.GetState() != null) {
            State.GetState().Update();
        }

        if (GAMEWIN) {
            String regex = "(\\d{2}):(\\d{2})";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(InGameState.timerText);

            if (matcher.find()) {
                String minutesStr = matcher.group(1);
                String secondsStr = matcher.group(2);

                if (Integer.parseInt(secondsStr)+Integer.parseInt(minutesStr)*60 <= 90) {
                    SCORE = 100 + Hero.numOfCoins * 100;
                } else if (Integer.parseInt(secondsStr) <= 150) {
                    SCORE = 50 + Hero.numOfCoins * 100;
                } else {
                    SCORE = 25 + Hero.numOfCoins * 100;
                }
            }
        }
    }

    // Deseneaza elementele grafice in fereastra coresponzator starilor actualizate ale elementelor
    private void Draw() {
        // Returnez bufferStrategy pentru canvasul existent
        bs = wnd.GetCanvas().getBufferStrategy();

        // Verific daca buffer strategy a fost construit sau nu
        if (bs == null) {
            // Se executa doar la primul apel al metodei Draw()
            try {
                // Se construieste triplul buffer
                wnd.GetCanvas().createBufferStrategy(3);
                return;
            } catch (Exception e) {
                // Afisez informatii despre problema aparuta pentru depanare.
                e.printStackTrace();
            }
        }
        // Se obtine contextul grafic curent in care se poate desena
        g = bs.getDrawGraphics();
        // Se sterge ce era
        g.clearRect(0, 0, wnd.GetWndWidth(), wnd.GetWndHeight());

        if (State.GetState() != null) {
            State.GetState().Draw(g);
        }

        // Dupa operatiile de desenare, pentru afisarea pe ecran
        bs.show();

        // Elibereaza resursele de memorie aferente contextului grafic curent
        g.dispose();
    }

    public KeyboardInputs GetKBInputs() {
        return keyboardIn;
    }

    public int GetHeight() {
        return wnd.GetWndHeight();
    }

    public int GetWidth() {
        return wnd.GetWndWidth();
    }

    public GameCamera GetGameCamera() {
        return gameCamera;
    }

    public MouseInputs GetMouseInputs(){
        return mouseInputs;
    }

    public void SetInGameState(InGameState inGameState){
        this.inGameState = inGameState;
    }

    public InGameState GetInGameState(){
        return inGameState;
    }

    public void SetMenuState(MainMenuState menuState){
        this.menuState = menuState;
    }

    public MainMenuState GetMenuState(){
        return menuState;
    }

    public GameDB GetGameDB(){
        return gameDB;
    }
}
