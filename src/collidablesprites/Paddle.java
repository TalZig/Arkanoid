package collidablesprites;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import gameclasses.GameLevel;
import geometricshapes.Line;
import geometricshapes.Point;
import geometricshapes.Rectangle;
import geometricshapes.Velocity;

import java.awt.Color;

/**
 * The type Paddle.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddle;
    private double rightLim;
    private double leftLim;
    private int speed;
    private double ballsSpeed;

    /**
     * Sets balls speed.
     *
     * @param ballsSpee the balls spee
     */
    public void setBallsSpeed(double ballsSpee) {
        this.ballsSpeed = ballsSpee;
    }

    /**
     * Gets balls speed.
     *
     * @return the balls speed
     */
    public double getBallsSpeed() {
        return ballsSpeed;
    }

    /**
     * Instantiates a new Paddle.
     *
     * @param r        the r
     * @param gui      the gui
     * @param rightLim the right lim
     * @param leftLim  the left lim
     * @param speed    the speed
     * @param key      the key
     */
    public Paddle(Rectangle r, GUI gui, double rightLim, double leftLim, int speed, KeyboardSensor key) {
        this.paddle = r;
        this.rightLim = rightLim;
        this.leftLim = leftLim;
        this.speed = speed;
        this.keyboard = key;
    }

    /**
     * Move left.
     */
    public void moveLeft() {
        //check if the paddle is touch the limit.
        if (this.leftLim < this.paddle.getUpperLeft().getX() - (speed + 4)) {
            this.paddle = new Rectangle(this.paddle.getUpperLeft().getX() - speed,
                    this.paddle.getUpperLeft().getY(),
                    this.paddle.getWidth(), this.paddle.getHeight());
        } else {
            this.paddle = new Rectangle(leftLim + 10, this.paddle.getUpperLeft().getY(),
                    this.paddle.getWidth(), this.paddle.getHeight());
        }
    }

    /**
     * Move right.
     */
    public void moveRight() {
        //check if the paddle is touch the limit.
        if (this.rightLim > this.paddle.getUpperLeft().getX() + this.paddle.getWidth() + (speed + 4)) {
            this.paddle = new Rectangle(this.paddle.getUpperLeft().getX() + speed,
                    this.paddle.getUpperLeft().getY(), this.paddle.getWidth(), this.paddle.getHeight());
        } else {
            this.paddle = new Rectangle(rightLim - (this.paddle.getWidth() + 10),
                    this.paddle.getUpperLeft().getY(),
                    this.paddle.getWidth(), this.paddle.getHeight());
        }
    }

    /**
     * Time passed.
     */
// Sprite
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * Draw on.
     *
     * @param surface the surface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.ORANGE);
        surface.fillRectangle((int) this.paddle.getUpperLeft().getX(), (int) this.paddle.getUpperLeft().getY(),
                (int) this.paddle.getWidth(), (int) this.paddle.getHeight());
    }

    /**
     * Gets collision rectangle.
     *
     * @return the collision rectangle
     */
// Collidable
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    /**
     * Hit velocity.
     *
     * @param hitter          the ball
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double x1 = this.paddle.getUpperLeft().getX();
        double y1 = this.paddle.getUpperLeft().getY();
        double width = this.paddle.getWidth();
        //make five lines of five region on the paddle.
        Line left2 = new Line(x1 - 5, y1, x1 + (width / 5), y1);
        Line left1 = new Line(x1 + (width / 5), y1, x1 + (2 * width / 5), y1);
        Line middle = new Line(x1 + (2 * width / 5), y1, x1 + (3 * width / 5), y1);
        Line right1 = new Line(x1 + (3 * width / 5), y1, x1 + (4 * width / 5), y1);
        Line right2 = new Line(x1 + (4 * width / 5), y1, x1 + (width) + 5, y1);
        Line downL = new Line(this.paddle.getDownnerLeft(), this.paddle.getDownnerRight());
        Line rightPadLim = new Line(this.paddle.getUpperRight(), this.paddle.getDownnerRight());
        Line leftPadLim = new Line(this.paddle.getUpperLeft(), this.paddle.getDownnerLeft());
        //if dy<0 its sign that is the opposite direction.
        if (currentVelocity.getDy() > 0) {
            //if its on the most left region.
            if (left2.isPointOnLine(collisionPoint)) {
                return currentVelocity.fromAngleAndSpeed(-60, (speed));
            }
            //if its on the middle-left region
            if (left1.isPointOnLine(collisionPoint)) {
                return currentVelocity.fromAngleAndSpeed(-30, (speed));
            }
            //if its on the middle region.
            if (middle.isPointOnLine(collisionPoint)) {
                return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            }
            //if its on the right-middle region
            if (right1.isPointOnLine(collisionPoint)) {
                return currentVelocity.fromAngleAndSpeed(30, (speed));
            }
            //if its on the most right region.
            if (right2.isPointOnLine(collisionPoint)) {
                return currentVelocity.fromAngleAndSpeed(60, (speed));
            }
        }
        //check if its hit the left/right line of the paddle. if yes its change the dx.
        if ((leftPadLim.isPointOnLine(collisionPoint) && currentVelocity.getDx() > 0)
                || (rightPadLim.isPointOnLine(collisionPoint) && currentVelocity.getDx() < 0)
                || this.paddle.isPointInRectangle(collisionPoint)) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        } else {
            return currentVelocity;
        }

    }

    /**
     * Add to game.
     *
     * @param g the g
     */
// Add this paddle to the game.
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Gets right lim.
     *
     * @return the right lim
     */
    public double getRightLim() {
        return this.rightLim;
    }

    /**
     * Gets left lim.
     *
     * @return the left lim
     */
    public double getLeftLim() {
        return this.leftLim;
    }

    /**
     * Remove from gameLevel.
     *
     * @param gameLevel the gameLevel
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * Gets keyboard.
     *
     * @return the keyboard
     */
    public KeyboardSensor getKeyboard() {
        return keyboard;
    }
}
