package levels;

import collidablesprites.Block;
import collidablesprites.Line;
import collidablesprites.BlockWithoutBlack;
import collidablesprites.BackGround;
import collidablesprites.Sprite;
import collidablesprites.BallWithoutBlack;
import geometricshapes.Point;
import geometricshapes.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level 3.
 */
public class Level3 implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<Velocity>(numberOfBalls());

        velocities.add(Velocity.fromAngleAndSpeed(-119, 6));
        velocities.add(Velocity.fromAngleAndSpeed(119, 6));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 7;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        Point temp1 = new Point(0, 0);
        Point temp2;
        BackGround lvlBackGround = new BackGround();
        lvlBackGround.addToBackGround(new Block(temp1, 800, 600, 0,
                new Color(255 - 2550000 - 180000000)));
        temp2 = new Point(60, 410);
        temp1 = new Point(60, 400);
        lvlBackGround.addToBackGround(new Block(temp2, 100, 200, 0,
                Color.white));
        for (int i = 0; i < 5; i++) {
            lvlBackGround.addToBackGround(new BlockWithoutBlack(temp1, 110, 10, 0,
                    Color.darkGray));
            temp1 = new Point(temp1.getX(), temp1.getY() + 40);
        }
        for (int i = 0; i < 6; i++) {
            lvlBackGround.addToBackGround(new BlockWithoutBlack(temp2, 10, 200, 0,
                    Color.darkGray));
            temp2 = new Point(temp2.getX() + 20, temp2.getY());
        }
        temp1 = new Point(150, 150);
        for (int i = 0; i < 300; i++) {
            temp2 = new Point((10 * i + 20 / (i + 1)), 240);
            lvlBackGround.addToBackGround(new Line(temp1, temp2, new Color(255 - 2550000 - 180000000)));
        }
        temp1 = new Point(100, 360);
        temp2 = new Point(110, 250);
        lvlBackGround.addToBackGround(new BlockWithoutBlack(temp2, 10, 130,
                0, new Color(0x334535)));
        lvlBackGround.addToBackGround(new BlockWithoutBlack(temp1, 30, 40,
                0, new Color(0x173319)));
        ;
        temp1 = new Point(115, 250);
        lvlBackGround.addToBackGround(new BallWithoutBlack(temp1, 15, new Color(0xFFBD1F)));
        lvlBackGround.addToBackGround(new BallWithoutBlack(temp1, 10, new Color(0xFF6851)));
        lvlBackGround.addToBackGround(new BallWithoutBlack(temp1, 3, Color.WHITE));
        return lvlBackGround;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        double blockHeight = 30;
        double blockWidth = 55;
        Color[] arr = makeColorArray();
        Point tempP = null;
        Block b1 = null;
        //make the blocks in 6 rows
        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < 10 - j; i++) {
                tempP = new Point(715 - i * 55, (j * 30) + 160);
                b1 = new Block(tempP, blockWidth, blockHeight, 1, arr[j]);
                blocks.add(b1);
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
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
                    array[i] = Color.gray;
                    break;
                case 1:
                    array[i] = Color.red;
                    break;
                case 2:
                    array[i] = Color.yellow;
                    break;
                case 3:
                    array[i] = Color.blue;
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
