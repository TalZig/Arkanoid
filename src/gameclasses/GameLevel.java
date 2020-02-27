package gameclasses;

import animation.Animation;
import animation.PauseScreen;
import animation.AnimationRunner;
import animation.KeyPressStoppableAnimation;
import animation.CountDownAnimation;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import collidablesprites.Sprite;
import collidablesprites.Block;
import collidablesprites.Ball;
import collidablesprites.LevelName;
import collidablesprites.SpriteCollection;
import collidablesprites.GameEnvironment;
import collidablesprites.Collidable;
import collidablesprites.Paddle;
import counters.Counter;
import counters.LivesIndicator;
import counters.ScoreIndicator;
import geometricshapes.Point;
import geometricshapes.Rectangle;
import geometricshapes.Velocity;
import levels.LevelInformation;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.HitListener;
import listeners.ScoreTrackingListener;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type GameLevel.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter blocksCounter;
    private BlockRemover blockRemover;
    private List<HitListener> listeners = new ArrayList<>();
    private Counter ballsCounter;
    private Counter score;
    private Counter lives;
    private Paddle paddle;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation lvl;
    private KeyboardSensor keyboardSensor;

    /**
     * The Width.
     */
    static final int WIDTH = 800;
    /**
     * The Height.
     */
    static final int HEIGHT = 600;

    /**
     * Instantiates a new GameLevel.
     *
     * @param lvl   the lvl
     * @param gui   the gui
     * @param an    the animation runner
     * @param live  the live
     * @param score the score
     * @param key   the key
     */
    public GameLevel(LevelInformation lvl, GUI gui, AnimationRunner an, Counter live,
                     Counter score, KeyboardSensor key) {
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        this.lvl = lvl;
        this.gui = gui;
        this.runner = an;
        this.lives = live;
        this.score = score;
        this.keyboardSensor = key;
    }

    /**
     * Add collidablesprites.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Initialize.
     */
// Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    public void initialize() {
        this.blocksCounter = new Counter(lvl.numberOfBlocksToRemove());
        this.sprites.addSprite(new Block(new Point(0, 0), 800, 20));
        //this.gui = new GUI("", WIDTH, HEIGHT);
        this.blockRemover = new BlockRemover(this, this.blocksCounter);
        this.ballsCounter = new Counter(0);
        this.sprites.addSprite(lvl.getBackground());
        //this.runner = new AnimationRunner(this.gui, 60);
        makeBlocks();
        LevelName name = new LevelName(lvl.levelName());
        name.addToGame(this);
    }

    /**
     * Remove collidablesprites.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);

    }

    /**
     * Remove sprite.
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Run.
     */
    public void run() {
        while (this.lives.getValue() > 0 && this.blocksCounter.getValue() > 0) {
            this.makePaddleAndBalls();
            this.ballsCounter.increase(lvl.numberOfBalls());
            playOneTurn();
            removePaddle();
            if (this.blocksCounter.getValue() != 0) {
                this.lives.decrease(1);
            }
        }
        if (this.blocksCounter.getValue() == 0) {
            this.score.increase(100);
        }
        //gui.close();
    }

    /**
     * playOneTurn.
     */
// playOneTurn the game -- start the animation loop.
    public void playOneTurn() {
        this.runner.run(new CountDownAnimation(2, 3, this.sprites));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    /**
     * Make blocks and ball.
     */
    public void makeBlocks() {
        ScoreTrackingListener scoreListener = new ScoreTrackingListener(score);
        ScoreIndicator newScoreInd = new ScoreIndicator(score);
        LivesIndicator livesIndicator = new LivesIndicator(this.lives);
        listeners.add(scoreListener);
        listeners.add(blockRemover);
        //function that makes and return the blocks of the limits.
        Block[] limits = makeLimits(0, WIDTH, 35, 640);
        for (int i = 1; i < 5; i++) {
            limits[i].addToGame(this);
        }
        livesIndicator.addToGame(this);
        newScoreInd.addToGame(this);
        this.addCollidable(limits[0]);
        //make array of colors that each index have different color.
        //Color[] colorArray = makeColorArray();
        for (Block b : lvl.blocks()) {
            b.setHitListeners(listeners);
            b.addToGame(this);
        }
    }


    /**
     * Make paddle and balls.
     */
    public void makePaddleAndBalls() {
        //make new paddle.
        Point center = new Point(400, 530);
        geometricshapes.Rectangle rectOfPaddle = new Rectangle(400 - (lvl.paddleWidth() / 2),
                545, lvl.paddleWidth(), 25);
        this.paddle = new Paddle(rectOfPaddle, this.gui, 800, 15, lvl.paddleSpeed(), keyboardSensor);
        paddle.addToGame(this);
        //make balls
        for (Velocity v : lvl.initialBallVelocities()) {
            Ball ball = new Ball(center, 6, Color.WHITE, 40, 640, 0, 800);
            ball.setVelocity(v);
            ball.makeGameEnvironment(environment);
            ball.addToGame(this);
        }
    }

    /**
     * Remove paddle.
     */
    public void removePaddle() {
        this.paddle.removeFromGame(this);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.notifyAllTimePassed();
        this.sprites.drawAllOn(d);
        if (this.ballsCounter.getValue() == 0) {
            this.running = false;
        }
        if (this.blocksCounter.getValue() == 0) {
            this.running = false;
        }
        if (this.paddle.getKeyboard().isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                    new PauseScreen(this.paddle.getKeyboard())));
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Make limits block [ ].
     *
     * @param leftLim  the left lim
     * @param rightLim the right lim
     * @param upLim    the up lim
     * @param downLim  the down lim
     * @return the block [ ]
     */
    public Block[] makeLimits(int leftLim, int rightLim, int upLim, int downLim) {
        Point tempP;
        List<HitListener> tempL = new ArrayList<>();
        Block[] blocks = new Block[5];
        //down limit
        tempP = new Point(leftLim - 30, downLim - 30);
        //make special block
        tempL.add(new BallRemover(this, this.ballsCounter));
        blocks[0] = new Block(tempL, tempP, rightLim + 40, 30, 0, Color.blue);
        //left limit
        tempP = new Point(leftLim, upLim);
        blocks[1] = new Block(tempP, 25, HEIGHT + 30, 0, Color.GRAY);
        //right limit
        tempP = new Point(rightLim - 10, upLim);
        blocks[2] = new Block(tempP, 25, HEIGHT + 30, 0, Color.GRAY);
        //up limit
        tempP = new Point(leftLim - 30, upLim);
        blocks[3] = new Block(tempP, rightLim + 50, 30, 0, Color.GRAY);
        tempP = new Point(0, 0);
        blocks[4] = new Block(tempP, rightLim + 50, 35, 0, Color.lightGray);
        //return array
        return blocks;
    }

    /**
     * Gets blocks counter.
     *
     * @return the blocks counter
     */
    public Counter getBlocksCounter() {
        return blocksCounter;
    }

    /**
     * Gets lives.
     *
     * @return the lives
     */
    public Counter getLives() {
        return lives;
    }

}
