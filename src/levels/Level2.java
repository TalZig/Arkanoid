package levels;

import collidablesprites.Block;
import collidablesprites.Sprite;
import collidablesprites.BackGround;
import collidablesprites.BallWithoutBlack;
import collidablesprites.Line;
import geometricshapes.Point;
import geometricshapes.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level 2.
 */
public class Level2 implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<Velocity>(numberOfBalls());
        for (int i = 0; i < 5; i++) {
            velocities.add(Velocity.fromAngleAndSpeed((50 - (10 * i)), 5));
        }
        for (int i = 0; i < 5; i++) {
            velocities.add(Velocity.fromAngleAndSpeed((350 - (10 * i)), 5));
        }
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 6;
    }

    @Override
    public int paddleWidth() {
        return 600;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        Point temp1 = new Point(0, 0);
        Point temp2;
        BackGround lvlBackGround = new BackGround();
        lvlBackGround.addToBackGround(new Block(temp1, 800, 600, 0, Color.WHITE));
        temp1 = new Point(150, 150);
        for (int i = 0; i < 180; i++) {
            temp2 = new Point((10 + i * 4), 240);
            lvlBackGround.addToBackGround(new Line(temp1, temp2, new Color(0xFFFDB5)));
        }
        temp1 = new Point(150, 150);
        temp2 = new Point(400, 40);
        lvlBackGround.addToBackGround(new BallWithoutBlack(temp1, 70, new Color(0xFFFDB5)));
        lvlBackGround.addToBackGround(new BallWithoutBlack(temp1, 50, new Color(0xFFE37F)));
        lvlBackGround.addToBackGround(new BallWithoutBlack(temp1, 30, new Color(0xFFCD3F)));
        return lvlBackGround;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        double blockHeight = 20;
        double blockWidth = 49.3;
        Color[] arr = makeColorArray();
        Block b1 = null;
        for (int i = 0; i < 15; i++) {
            if (i < 2) {
                b1 = new Block(new Point(30 + (i * 49.3), 240), blockWidth, blockHeight, 1, arr[0]);
            }
            if (i >= 2 && i < 4) {
                b1 = new Block(new Point(30 + (i * 49.3), 240), blockWidth, blockHeight, 1, arr[1]);
            }
            if (i >= 4 && i < 6) {
                b1 = new Block(new Point(30 + (i * 49.3), 240), blockWidth, blockHeight, 1, arr[2]);
            }
            if (i >= 6 && i < 9) {
                b1 = new Block(new Point(30 + (i * 49.3), 240), blockWidth, blockHeight, 1, arr[3]);
            }
            if (i >= 9 && i < 11) {
                b1 = new Block(new Point(30 + (i * 49.3), 240), blockWidth, blockHeight, 1, arr[4]);
            }
            if (i >= 11 && i < 13) {
                b1 = new Block(new Point(30 + (i * 49.3), 240), blockWidth, blockHeight, 1, arr[5]);
            }
            if (i >= 13) {
                b1 = new Block(new Point(30 + (i * 49.3), 240), blockWidth, blockHeight, 1, arr[6]);
            }
            blocks.add(b1);
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
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
                    array[i] = Color.red;
                    break;
                case 1:
                    array[i] = Color.orange;
                    break;
                case 2:
                    array[i] = Color.yellow;
                    break;
                case 3:
                    array[i] = Color.green;
                    break;
                case 4:
                    array[i] = Color.blue;
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
