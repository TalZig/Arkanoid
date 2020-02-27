package menu;

import animation.AnimationRunner;
import gameclasses.GameFlow;
import levels.LevelInformation;

import java.util.List;

/**
 * The type Start game task.
 */
public class StartGameTask implements Task<Void> {
    private AnimationRunner runner;
    private GameFlow gameflow;
    private List<LevelInformation> levels;

    /**
     * Instantiates a new Show hi scores task.
     *
     * @param gameFlow the game flow
     * @param levels   the levels
     */
    public StartGameTask(GameFlow gameFlow, List<LevelInformation> levels) {
        this.gameflow = gameFlow;
        this.levels = levels;
    }
    @Override
    public Void run() {
        this.gameflow.runLevels(this.levels);
        return null;
    }
}
