package levels;

import collidablesprites.Sprite;
import collidablesprites.BackGround;
import collidablesprites.Line;
import collidablesprites.Circle;
import collidablesprites.Block;
import geometricshapes.Point;
import geometricshapes.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level 1.
 */

public class Level1 implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<Velocity>(numberOfBalls());
        velocities.add(Velocity.fromAngleAndSpeed(180, 7 - 1));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 7;
    }

    @Override
    public int paddleWidth() {
        return 120;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        Point temp1 = new Point(0, 0);
        Point temp2 = new Point(400, 40);
        BackGround lvlBackGround = new BackGround();
        lvlBackGround.addToBackGround(new Block(temp1, 800, 600, 0, Color.BLACK));
        temp1 = new Point(400, 325);
        lvlBackGround.addToBackGround(new Line(temp1, temp2, Color.BLUE));
        temp1 = new Point(275, 200);
        temp2 = new Point(525, 200);
        lvlBackGround.addToBackGround(new Line(temp1, temp2, Color.BLUE));
        lvlBackGround.addToBackGround(new Circle(new Point(400, 200), 100, Color.BLUE));
        lvlBackGround.addToBackGround(new Circle(new Point(400, 200), 70, Color.BLUE));
        lvlBackGround.addToBackGround(new Circle(new Point(400, 200), 40, Color.BLUE));
        return lvlBackGround;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        double blockHeight = 30;
        double blockWidth = 30;
        Block b1 = new Block(new Point(385, 180), blockWidth, blockHeight, 1, Color.RED);
        blocks.add(b1);
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
