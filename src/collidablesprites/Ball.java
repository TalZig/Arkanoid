package collidablesprites;

import biuoop.DrawSurface;
import gameclasses.GameLevel;
import geometricshapes.Line;
import geometricshapes.Point;
import geometricshapes.Velocity;

import java.awt.Color;


/**
 * The type Ball.
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private Color color;
    private Velocity velocity;
    private int upLim;
    private int downLim;
    private int leftLim;
    private int rightLim;
    private GameEnvironment gameEnvironment;
    private double speed;

    /**
     * Instantiates a new Ball.
     *
     * @param center the center of the circle
     * @param r      the radius of this circle.
     * @param color  the color of the circle.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = new Point(center.getX(), center.getY());
        this.radius = r;
        this.color = color;
        gameEnvironment = new GameEnvironment();
    }

    /**
     * Instantiates a new Ball.
     *
     * @param x     the x
     * @param y     the y
     * @param r     the r
     * @param color the color
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        gameEnvironment = new GameEnvironment();
    }

    /**
     * Instantiates a new Ball.
     *
     * @param center   the center
     * @param r        the r
     * @param color    the color
     * @param upLim    the up lim
     * @param downLim  the down lim
     * @param leftLim  the left lim
     * @param rightLim the right lim
     */
    public Ball(Point center, int r, java.awt.Color color, int upLim, int downLim, int leftLim, int rightLim) {
        this.center = new Point(center.getX(), center.getY());
        this.radius = r;
        this.color = color;
        this.upLim = upLim;
        this.downLim = downLim;
        this.leftLim = leftLim;
        this.rightLim = rightLim;
        gameEnvironment = new GameEnvironment();
    }

    /**
     * Gets x.
     *
     * @return the x
     */
// accessors
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Gets game environment.
     *
     * @return the game environment
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * Sets game environment.
     *
     * @param c the c
     */
    public void makeGameEnvironment(Collidable c) {
        this.gameEnvironment.addCollidable(c);
    }

    /**
     * New game environment.
     *
     * @param newGameEnvi the new game environment
     */
    public void makeGameEnvironment(GameEnvironment newGameEnvi) {
        this.gameEnvironment = newGameEnvi;
    }

    /**
     * Draw on.
     *
     * @param surface the surface
     */
// draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface) {
        surface.setColor(getColor());
        surface.fillCircle(this.getX(), this.getY(), this.radius);
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.radius);
    }


    /**
     * Sets velocity.
     *
     * @param v the new velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Gets velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Move one step in the game.
     */
    public void moveOneStep() {
        Velocity tempA = this.getVelocity();
        if (!this.isBallCrossTheBorder()) {
            this.collisionWithObject();
        }
        if (tempA.getDx() == this.velocity.getDx() && tempA.getDy() == this.velocity.getDy()) {
            this.center = this.getVelocity().applyToPoint(this.center);
        }
    }

    /**
     * Is ball cross the border boolean.
     *
     * @return the boolean
     */
    public boolean isBallCrossTheBorder() {
        //cross left lim.
        boolean flag = false;
        if (this.getX() - this.getSize() + this.getVelocity().getDx() < this.leftLim
                && this.getVelocity().getDx() < 0) {
            this.setVelocity(-this.getVelocity().getDx(), this.getVelocity().getDy());
            flag = true;
        }
        //cross right limit.
        if (this.getX() + this.getSize() + this.getVelocity().getDx() > this.rightLim
                && this.getVelocity().getDx() > 0) {
            this.setVelocity(-this.getVelocity().getDx(), this.getVelocity().getDy());
            flag = true;
        }
        //cross up limit.
        if (this.getY() - this.getSize() + this.getVelocity().getDy() < this.upLim
                && this.getVelocity().getDy() < 0) {
            this.setVelocity(this.getVelocity().getDx(), -this.getVelocity().getDy());
            flag = true;
        }
        //cross down limit.
        if (this.getY() + this.getSize() + this.getVelocity().getDy() > this.downLim
                && this.getVelocity().getDy() > 0) {
            this.setVelocity(this.getVelocity().getDx(), -this.getVelocity().getDy());
            flag = true;
        }
        return flag;
    }

    /**
     * Collision with object.
     */
    public void collisionWithObject() {
        CollisionInfo collisionInf = null;
        Point start, end;
        Line trajectory;
        start = new Point(this.center.getX() - this.velocity.getDx(),
                this.center.getY() - this.velocity.getDy());
        end = new Point(this.center.getX() + (this.velocity.getDx()),
                this.center.getY() + (this.velocity.getDy()));
        trajectory = new Line(start, end);
        collisionInf = this.gameEnvironment.getClosestCollision(trajectory);
        if (collisionInf != null) {
            setVelocity((collisionInf.collisionObject().hit(this, collisionInf.collisionPoint(), this.velocity)));
        }
    }


    /**
     * Rand color for the ball.
     *
     * @return the color
     */
    public static Color randColor() {
        int red, blue, green;
        red = (int) (Math.random() * 256);
        blue = (int) (Math.random() * 256);
        green = (int) (Math.random() * 256);
        return new Color(red, green, blue);
    }

    /**
     * Time passed.
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Add to game.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Remove from game.
     *
     * @param g the game
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}
