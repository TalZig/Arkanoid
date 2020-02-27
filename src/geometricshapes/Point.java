package geometricshapes;
/**
 * The class Point.
 */
public class Point {
    private double x;
    private double y;

    /**
     * Instantiates a new Point.
     *
     * @param x the x
     * @param y the y Constructor
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Distance double.
     *
     * @param other the other
     * @return the distance
     */
// distance -- return the distance of this point to the other point
    public double distance(Point other) {
        double xDis, yDis;
        xDis = this.x - other.getX();
        yDis = this.y - other.getY();
        return Math.sqrt(Math.pow(xDis, 2) + Math.pow(yDis, 2));
    }

    /**
     * Equals boolean.
     *
     * @param other the other
     * @return the boolean
     */
// equals -- return true is the points are equal, false otherwise
    public boolean equals(Point other) {
        return (other.getY() == this.y && other.getX() == this.x);
    }

    /**
     * Gets x.
     *
     * @return the x
     */
// Return the x and y values of this point
    public double getX() {
        return this.x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public double getY() {
        return this.y;
    }
}