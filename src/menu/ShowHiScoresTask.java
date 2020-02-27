package menu;

import animation.Animation;
import animation.AnimationRunner;
import animation.HighScoresAnimation;

/**
 * The type Show hi scores task.
 */
public class ShowHiScoresTask implements Task<Void> {
    private AnimationRunner runner;
    private Animation highScoresAnimation;

    /**
     * Instantiates a new Show hi scores task.
     *
     * @param runner              the runner
     * @param highScoresAnimation the high scores animation
     */
    public ShowHiScoresTask(AnimationRunner runner, HighScoresAnimation highScoresAnimation) {
        this.runner = runner;
        this.highScoresAnimation = highScoresAnimation;
    }
    @Override
    public Void run() {
        this.runner.run(this.highScoresAnimation);
        return null;
    }
}
