package levels;

import collidablesprites.Block;
import collidablesprites.Sprite;
import collidablesprites.BallWithoutBlack;
import collidablesprites.BackGround;
import collidablesprites.Line;
import geometricshapes.Point;
import geometricshapes.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level 4.
 */
public class Level4 implements LevelInformation {
    private int blockCounter = 0;

    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<Velocity>(numberOfBalls());
        for (int i = 0; i < 3; i++) {
            velocities.add(Velocity.fromAngleAndSpeed((220 - (40 * i)), 5));
        }
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 6;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        Point temp1 = new Point(0, 0);
        Point temp2;
        BackGround lvlBackGround = new BackGround();
        lvlBackGround.addToBackGround(new Block(temp1, 800, 600, 0, new Color(0x31C3FF)));
        for (int i = 0; i < 10; i++) {
            temp2 = new Point((10 * i + 70), 400);
            temp1 = new Point((10 * i + 60), 600);
            lvlBackGround.addToBackGround(new Line(temp1, temp2, Color.WHITE));
            temp1 = new Point(10 * i + 600, 500);
            temp2 = new Point(10 * i + 590, 600);
            lvlBackGround.addToBackGround(new Line(temp1, temp2, Color.WHITE));
        }
        lvlBackGround.addToBackGround(new BallWithoutBlack(new Point(120, 400),
                45, new Color(204, 204, 204)));
        lvlBackGround.addToBackGround(new BallWithoutBlack(new Point(160, 410),
                30, new Color(204, 204, 204)));
        lvlBackGround.addToBackGround(new BallWithoutBlack(new Point(100, 430),
                30, new Color(153, 153, 153)));
        lvlBackGround.addToBackGround(new BallWithoutBlack(new Point(85, 420),
                33, new Color(153, 153, 153)));
        lvlBackGround.addToBackGround(new BallWithoutBlack(new Point(620, 500),
                40, new Color(204, 204, 204)));
        lvlBackGround.addToBackGround(new BallWithoutBlack(new Point(590, 485),
                30, new Color(204, 204, 204)));
        lvlBackGround.addToBackGround(new BallWithoutBlack(new Point(650, 490),
                30, new Color(153, 153, 153)));
        lvlBackGround.addToBackGround(new BallWithoutBlack(new Point(675, 510),
                33, new Color(153, 153, 153)));
        return lvlBackGround;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        double blockHeight = 25;
        double blockWidth = 49.2;
        Color[] arr = makeColorArray();
        Block b1 = null;
        for (int j = 0; j < 7; j++) {
            for (int i = 0; i < 15; i++) {
                b1 = new Block(new Point(30 + (i * 49.3), 140 + (j * blockHeight)),
                        blockWidth, blockHeight, 1, arr[j]);
                blocks.add(b1);
                this.blockCounter++;
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 105;
    }

    /**
     * Make color array [ ].
     *
     * @return the color array [ ]
     */
    public Color[] makeColorArray() {
        Color[] array = new Color[7];
        for (int i = 0; i < 7; i++) {
            switch (i) {
                case 0:
                    array[i] = Color.DARK_GRAY;
                    break;
                case 1:
                    array[i] = Color.red;
                    break;
                case 2:
                    array[i] = Color.yellow;
                    break;
                case 3:
                    array[i] = Color.green;
                    break;
                case 4:
                    array[i] = Color.white;
                    break;
                case 5:
                    array[i] = Color.pink;
                    break;
                case 6:
                    array[i] = Color.cyan;
                default:
                    break;
            }
        }
        return array;
    }
}
