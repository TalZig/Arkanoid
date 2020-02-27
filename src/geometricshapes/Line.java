package geometricshapes;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Line.
 */
public class Line {
    private Point start;
    private Point end;
    // constructors

    /**
     * Instantiates a new Line.
     *
     * @param start is the start point
     * @param end   is the end point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Instantiates a new Line.
     *
     * @param x1 the x of the first point.
     * @param y1 the y of the first point.
     * @param x2 the x of the second point.
     * @param y2 the y of the second point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Length double.
     *
     * @return double the length of the line
     */
    public double length() {
        return this.start.distance(end);
    }

    /**
     * Middle point is function that find the middle point of two points.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        double xMid, yMid;
        xMid = (this.start.getX() + this.end.getX()) / 2;
        yMid = (this.start.getY() + this.end.getY()) / 2;
        return new Point(xMid, yMid);
    }

    /**
     * Start point.
     *
     * @return the start point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * End point.
     *
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * Is intersecting boolean.
     *
     * @param other is other line that i compare with my line
     * @return the boolean-true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        return (this.intersectionWith(other) != null);

    }

    /**
     * check if there Intersection between two lines and return the intersection point.
     *
     * @param other other line that i compare with this line.
     * @return the intersection point.
     */
// Returns the intersection point if the lines intersect,
    // and null otherwise.
    public Point intersectionWith(Line other) {
        double x1, y1, x2, y2, x3, y3, x4, y4, m1, m2, b1, b2, x0, y0;
        //give new names to variables to save on the order.
        x1 = this.start.getX();
        x2 = this.end.getX();
        y1 = this.start.getY();
        y2 = this.end.getY();
        x3 = other.start.getX();
        y3 = other.start.getY();
        x4 = other.end.getX();
        y4 = other.end.getY();
        //if x1==x2 can be a problem in the calculate of m1 so its special case.
        if (x1 == x2 && x3 != x4) {
            return parallelToYLine(this, other);
        } else {
            if (x1 == x2 && x3 == x4) {
                return checkSpecialCases(other);
            }
            m1 = (y2 - y1) / (x2 - x1);
        }
        if (x3 == x4 && x1 != x2) {
            return parallelToYLine(other, this);
        } else {
            m2 = (y4 - y3) / (x4 - x3);
        }

        //equations, b1 is for this, b2 is for other.
        b1 = y1 - (m1 * x1);
        b2 = y3 - (m2 * x3);

        // parallel lines.
        if (m1 == m2) {
            return checkSpecialCases(other);
        }
        //value of the x of the intersecting point.
        x0 = -(b1 - b2) / (m1 - m2);

        //check if its in the range.
        if (Math.min(x1, x2) <= x0 && x0 <= Math.max(x1, x2) && Math.min(x3, x4) <= x0 && x0 <= Math.max(x3, x4)) {
            y0 = m1 * x0 + b1;
            return new Point(x0, y0);
        }
        return null;
    }

    /**
     * Parallel to y Line, special case of intersection lines.
     *
     * @param parallel the parallel
     * @param other    the other
     * @return the point
     */
    public Point parallelToYLine(Line parallel, Line other) {
        double m2, y0;
        m2 = other.findSlope();
        //check if the x is on the range or not.
        if (Math.min(other.start().getX(), other.end().getX()) > parallel.start().getX()
                || Math.max(other.start().getX(), other.end().getX()) < parallel.start().getX()) {
            return null;
        }
        //if the x is on the range, the function build an equation to find y of the intersection.
        y0 = m2 * parallel.start().getX() + other.start.getY() - (m2 * other.start.getX());
        //check if the intersection is on the line range or not.
        if (Math.min(parallel.start().getY(), parallel.end().getY()) <= y0
                && Math.max(parallel.start().getY(), parallel.end().getY()) >= y0) {
            return new Point(parallel.start().getX(), y0);
        } else {
            return null;
        }
    }

    /**
     * Find incline double.
     *
     * @return the slope of the line
     */
    public double findSlope() {
        return ((this.end().getY() - this.start.getY()) / (this.end.getX()
                - this.start.getX()));
    }

    /**
     * Equals boolean.
     *
     * @param other the other
     * @return the boolean
     */
// equals -- return true is the lines are equal, false otherwise
    public boolean equals(Line other) {
        return (this.start.equals(other.start) && this.end.equals(other.end))
                || (this.start.equals(other.end) && this.end.equals(other.start));
    }

    /**
     * Check special cases of intersection.
     *
     * @param other the other line
     * @return the intersection point or null if there isnt intersection
     */
    public Point checkSpecialCases(Line other) {
        // check case that the slopes are equals and there is only one intersecting point.
        if (this.start.equals(other.start) || this.start.equals(other.end)) {
            return this.start;
        }
        if (this.end.equals(other.start) || this.end.equals(other.end)) {
            return this.end;
        }
        /* case of line that include start point that equal to end point. check if the distance from the two points
         on the other line is equal to the distance from the first line point to start point + first line point to
         point end. if its true its sign that the point is on the line and there is intersecting.
         */
        if (this.end.equals(this.start)) {
            if (this.start.distance(other.start) + this.start.distance(other.end) == other.start.distance(other.end)) {
                return this.start;
            }
        }
        if (other.end.equals(other.start)) {
            if (other.start.distance(this.start) + other.start.distance(this.end) == this.start.distance(this.end)) {
                return other.start;
            }
        }
        return null;
    }

    /**
     * Closest intersection to start of line point.
     *
     * @param rect the rectangle
     * @return the closest intersection point
     */
// If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionP = new ArrayList<>();
        intersectionP = rect.intersectionPoints(this);
        //check how many intersections there is. (the maximum is 2)
        //if there is 0 its returns null.
        if (intersectionP.size() == 0) {
            return null;
        }
        //if there is only one is the closest intersection point and i return that point.
        if (intersectionP.size() == 1) {
            return intersectionP.get(0);
            //else, return the closer one.
        } else {
            if (intersectionP.get(0).distance(this.start) > intersectionP.get(1).distance(this.start)) {
                return intersectionP.get(1);
            } else {
                return intersectionP.get(0);
            }
        }
    }

    /**
     * checks if the point is on line boolean.
     *
     * @param point the point
     * @return the boolean if point on line or not.
     */
    public boolean isPointOnLine(Point point) {
        return (this.start().distance(this.end()) == (point.distance(this.start()) + point.distance(this.end())));
    }
}

