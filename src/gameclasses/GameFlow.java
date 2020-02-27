package gameclasses;

import animation.AnimationRunner;
import animation.KeyPressStoppableAnimation;
import animation.GameOver;
import animation.YouWin;
import animation.HighScoresAnimation;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import counters.Counter;
import highscores.HighScoresTable;
import highscores.ScoreInfo;
import levels.LevelInformation;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * The type Game flow.
 */
public class GameFlow {
    private GUI gui;
    private AnimationRunner ar;
    private Counter live;
    private Counter score;
    private KeyboardSensor keyboardSensor;

    /**
     * Instantiates a new Game flow.
     *
     * @param ar the animationRunner
     */
    public GameFlow(AnimationRunner ar) {
        this.gui = ar.getGui();
        this.ar = ar;
        this.keyboardSensor = gui.getKeyboardSensor();
    }

    /**
     * Run levels.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        live = new Counter(5);
        score = new Counter(0);
        HighScoresTable highScoresTable = new HighScoresTable(6);
        String fileName = "resources/highscores.ser";
        File file = new File(fileName);
        try {
            highScoresTable.load(file);
        } catch
        (IOException ex) {
            System.out.println("exception22");
        }
        String key = KeyboardSensor.SPACE_KEY;
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, gui, ar, this.live, score, keyboardSensor);

            level.initialize();
            level.run();
            if (live.getValue() <= 0) {
                break;
            }
        }
        DialogManager dialog = gui.getDialogManager();
        String name = dialog.showQuestionDialog("Name", "What is your name?", "");
        highScoresTable.add(new ScoreInfo(name, score.getValue()));
        if (live.getValue() == 0) {
            ar.run(new KeyPressStoppableAnimation(this.keyboardSensor, key, new GameOver(score, keyboardSensor)));
        } else {
            ar.run(new KeyPressStoppableAnimation(this.keyboardSensor, key, new YouWin(score, keyboardSensor)));
        }
        try {
            highScoresTable.save(file);
        } catch
        (IOException ex) {
            System.out.println("exception");
        }
        ar.run(new KeyPressStoppableAnimation(this.keyboardSensor, key,
                new HighScoresAnimation(highScoresTable, keyboardSensor)));
    }
}
