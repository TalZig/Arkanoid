package geometricshapes;

/**
 * The type Velocity.
 */
// Velocity specifies the change in position on the `x` and the `y` axes.
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Instantiates a new Velocity.
     *
     * @param dx the change of x.
     * @param dy the change of y.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Instantiates a new Velocity.
     *
     * @param vel the velocity value that i want to make.
     */
    public Velocity(Velocity vel) {
        this.dx = vel.getDx();
        this.dy = this.getDy();
    }

    /**
     * From angle and speed velocity.
     *
     * @param angle the angle
     * @param speed the speed
     * @return the velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radians = Math.toRadians(angle - 90);
        double dx = Math.cos(radians) * speed;
        double dy = Math.sin(radians) * speed;
        return new Velocity(dx, dy);
    }


    /**
     * Apply to point point.
     *
     * @param p the p
     * @return the point
     */
// Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)
    public Point applyToPoint(Point p) {
        return new Point((p.getX() + this.dx), (p.getY() + this.dy));

    }

    /**
     * Gets dx.
     *
     * @return the dx
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Gets dy.
     *
     * @return the dy
     */
    public double getDy() {
        return this.dy;
    }

}