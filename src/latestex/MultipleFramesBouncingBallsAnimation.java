package latestex;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import collidablesprites.Ball;
import geometricshapes.Velocity;
import geometricshapes.Point;

import java.awt.Color;

/**
 * The type Multiple frames bouncing balls animation.
 */
public class MultipleFramesBouncingBallsAnimation {
    static final int WIDTH = 1000;
    static final int HEIGHT = 1000;
    static final int GREY_RIGHT = 500;
    static final int GREY_DOWN = 500;
    static final int GREY_LEFT = 50;
    static final int GREY_UP = 50;
    static final int YELL_RIGHT = 600;
    static final int YELL_DOWN = 600;
    static final int YELL_LEFT = 450;
    static final int YELL_UP = 450;
    static final int MAX_RAD = 50;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Color color;
        double x = 0, y = 0;
        int greyRUP = 500, greyRDown = 50;
        int yellRUP = 600, yellRDown = 450;
        GUI gui = new GUI("title", WIDTH, HEIGHT);
        Sleeper sleeper = new Sleeper();
        Velocity v;
        Ball[] balls = new Ball[args.length];
        int[] sizeArr = new int[args.length];
        //loop that make new Balls that the limits for them is to the first rectangle.
        for (int i = 0; i < (balls.length / 2); i++) {
            sizeArr[i] = Integer.parseInt(args[i]);
            x = Math.random() * 450 + 50;
            y = Math.random() * 450 + 50;
            color = Ball.randColor();
            Point center = new Point(x, y);
            balls[i] = new Ball(center, sizeArr[i], color, GREY_UP, GREY_DOWN, GREY_LEFT, GREY_RIGHT);
        }
        //loop  that make new Balls that the limits for them is to the second rectangle.
        for (int i = (balls.length / 2); i < balls.length; i++) {
            sizeArr[i] = Integer.parseInt(args[i]);
            //random numbers
            x = Math.random() * 150 + 450;
            y = Math.random() * 150 + 450;
            color = Ball.randColor();
            Point center = new Point(x, y);
            balls[i] = new Ball(center, sizeArr[i], color, YELL_UP, YELL_DOWN, YELL_LEFT, YELL_RIGHT);
        }
        for (int i = 0; i < balls.length; i++) {
            if (sizeArr[i] >= 50) {
                v = Velocity.fromAngleAndSpeed(Math.random() * 360, 1);
            } else {
                v = Velocity.fromAngleAndSpeed((Math.random() * 360), (80 - sizeArr[i]) / 10.0);
            }
            balls[i].setVelocity(v);
        }
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.lightGray);
            d.fillRectangle(50, 50, 450, 450);
            d.setColor(Color.YELLOW);
            d.fillRectangle(450, 450, 150, 150);
            /*
             */
            for (int i = 0; i < balls.length; i++) {
                balls[i].moveOneStep();
                balls[i].drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(50);
        }
    }
}