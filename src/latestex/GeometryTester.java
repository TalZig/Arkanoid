package latestex;

import geometricshapes.Line;
import geometricshapes.Point;

/**
 * This class does some simple tessting of the Point and Line classes.
 */
public class GeometryTester {

    /**
     * The method is in charge of testing the Point class.
     *
     * @return true if not mistakes were found, false otherwise.
     */
    public boolean testPoint() {
        boolean mistake = false;
        Point p1 = new Point(12, 2);
        Point p2 = new Point(9, -2);

        if (p1.getX() != 12) {
            System.out.println("Test p1.getX() failed.");
            mistake = true;
        }
        if (p1.getY() != 2) {
            System.out.println("Test p1.getY() failed.");
            mistake = true;
        }
        if (p1.distance(p1) != 0) {
            System.out.println("Test distance to self failed.");
            mistake = true;
        }
        if (p1.distance(p2) != p2.distance(p1)) {
            System.out.println("Test distance symmetry failed.");
            mistake = true;
        }
        if (p1.distance(p2) != 5) {
            System.out.println("Test distance failed.");
            mistake = true;
        }
        if (!p1.equals(p1)) {
            System.out.println("Equality to self failed.");
            mistake = true;
        }
        if (!p1.equals(new Point(12, 2))) {
            System.out.println("Equality failed.");
            mistake = true;
        }
        if (p1.equals(p2)) {
            System.out.println("Equality failed -- should not be equal.");
            mistake = true;
        }
        Point p3 = new Point(12, 2);
        Point p4 = new Point(9, -2);
        Point p5 = new Point(12, 2);
        Point p6 = new Point(9, -2);
        Line line1 = new Line(80, 109, 400, 148);
        Line line2 = new Line(224, 46, 19, 267);
        if ((line1.isIntersecting(line2))) {
            System.out.println("America1");
        }
        line1 = new Line(0, 0, 2, 2);
        line2 = new Line(2, 2, 4, 4);
        if ((line1.isIntersecting(line2))) {
            System.out.println("America2");
        }
        line1 = new Line(3, 0, 3, 4);
        line2 = new Line(0, 2, 4, 2);
        if ((line1.isIntersecting(line2))) {
            System.out.println("America3");
        }
        line1 = new Line(3, 0, 3, 4);
        line2 = new Line(0, 2, 3, 2);
        if ((line1.isIntersecting(line2))) {
            System.out.println("America4");
        }
        line1 = new Line(3, 0, 3, 2);
        line2 = new Line(0, 2, 4, 2);
        if ((line1.isIntersecting(line2))) {
            System.out.println("America5");
        }
        line1 = new Line(3, 0, 3, 2);
        line2 = new Line(3, 0, 3, 0);
        if ((line1.isIntersecting(line2))) {
            System.out.println("America6");
        }
        line1 = new Line(3, 0, 3, 2);
        line2 = new Line(3, 1, 3, 1);
        if ((line1.isIntersecting(line2))) {
            System.out.println("America7");
        }
        line1 = new Line(3, 0, 3, 2);
        line2 = new Line(3, 2, 3, 2);
        if ((line1.isIntersecting(line2))) {
            System.out.println("America8");
        }
        line1 = new Line(0, 4, 4, 0);
        line2 = new Line(4, 0, -1, 2);
        if ((line1.isIntersecting(line2))) {
            System.out.println("America9");
        }
        line1 = new Line(0, 0, 0, 0);
        line2 = new Line(0, 0, 0, 0);
        if ((line1.isIntersecting(line2))) {
            System.out.println("America10");
        }
        line1 = new Line(0, 0, 0, 2);
        line2 = new Line(0, 2, 0, 4);
        if ((line1.isIntersecting(line2))) {
            System.out.println("America11");
        }
        line1 = new Line(0, 0, 2, 0);
        line2 = new Line(2, 0, 4, 4);
        if ((line1.isIntersecting(line2))) {
            System.out.println("America12");
        }
        line1 = new Line(0, 0, 2, 0);
        line2 = new Line(2, 0, 4, 0);
        if ((line1.isIntersecting(line2))) {
            System.out.println("America13");
        }


        return !mistake;
    }

    /**
     * The method is in charge of testing the Line class.
     *
     * @return true if not mistakes were found, false otherwise.
     */
    public boolean testLine() {
        boolean mistakes = false;
        Line l1 = new Line(12, 2, 9, -2);
        Line l2 = new Line(0, 0, 20, 0);
        Line l3 = new Line(9, 2, 12, -2);

        if (!l1.isIntersecting(l2)) {
            System.out.println("Test isIntersecting failed (1).");
            mistakes = true;
        }
        if (l1.isIntersecting(new Line(0, 0, 1, 1))) {
            System.out.println("Test isIntersecting failed (2).");
            mistakes = true;
        }
        Point intersectL1L2 = l1.intersectionWith(l2);
        if (!l1.middle().equals(intersectL1L2)) {
            System.out.println("Test intersectionWith middle failed.");
            mistakes = true;
        }

        return !mistakes;
    }

    /**
     * Main method, running tests on both the point and the line classes.
     *
     * @param args ignored.
     */
    public static void main(String[] args) {
        GeometryTester tester = new GeometryTester();
        System.out.println(tester.testPoint());
        System.out.println(tester.testLine());
    }
}
