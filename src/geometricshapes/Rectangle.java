package geometricshapes;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Rectangle.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Instantiates a new Rectangle.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     */
// Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Instantiates a new Rectangle.
     *
     * @param upperLeftX the upper left x
     * @param upperLeftY the upper left y
     * @param width      the width
     * @param height     the height
     */
    public Rectangle(double upperLeftX, double upperLeftY, double width, double height) {
        this.upperLeft = new Point(upperLeftX, upperLeftY);
        this.width = width;
        this.height = height;
    }

    /**
     * Intersection points java . util . list.
     *
     * @param line the line
     * @return the java . util . list
     */
// Return a (possibly empty) List of intersection points
    // with the specified line.
    public java.util.List<Point> intersectionPoints(Line line) {
        //List intersectionP;
        Point interSectionPoint;
        List<Point> intersectionList = new ArrayList<>();
        //up limit intersection.
        Line upL = new Line(upperLeft, getUpperRight());
        interSectionPoint = upL.intersectionWith(line);
        if (interSectionPoint != null) {
            intersectionList.add(interSectionPoint);
        }
        //left limit intersection
        interSectionPoint = null;
        Line leftL = new Line(upperLeft, getDownnerLeft());
        interSectionPoint = leftL.intersectionWith(line);
        if (interSectionPoint != null) {
            intersectionList.add(interSectionPoint);
        }
        interSectionPoint = null;
        //right limit intersection.
        Line rightL = new Line(getUpperRight(), getDownnerRight());
        interSectionPoint = rightL.intersectionWith(line);
        if (interSectionPoint != null) {
            intersectionList.add(interSectionPoint);
        }
        interSectionPoint = null;
        //down limit intersection.
        Line downL = new Line(getDownnerLeft(), getDownnerRight());
        interSectionPoint = downL.intersectionWith(line);
        if (interSectionPoint != null) {
            intersectionList.add(interSectionPoint);
        }
        return intersectionList;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
// Return the width and height of the rectangle
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets upper left.
     *
     * @return the upper left
     */
// Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * Gets donner left.
     *
     * @return the donner left
     */
    public Point getDownnerLeft() {
        return new Point(upperLeft.getX(), upperLeft.getY() + this.height);
    }

    /**
     * Gets downner right.
     *
     * @return the downner right
     */
    public Point getDownnerRight() {
        return new Point((upperLeft.getX() + this.getWidth()), upperLeft.getY() + this.getHeight());
    }

    /**
     * Gets upper right.
     *
     * @return the upper right
     */
    public Point getUpperRight() {
        return new Point(upperLeft.getX() + this.getWidth(), upperLeft.getY());
    }

    /**
     * Is point in rectangle boolean.
     *
     * @param p the p
     * @return the boolean
     */
    public boolean isPointInRectangle(Point p) {
        return (p.getX() > this.getUpperLeft().getX() && p.getX() < this.getUpperRight().getX()
                && p.getY() > this.getUpperRight().getY() && p.getY() < this.getDownnerLeft().getY());
    }
}
