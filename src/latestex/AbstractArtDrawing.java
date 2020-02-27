package latestex;

import biuoop.DrawSurface;
import biuoop.GUI;
import geometricshapes.Line;
import geometricshapes.Point;

import java.awt.Color;

/**
 * The type Abstract art drawing.
 */
public class AbstractArtDrawing {
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    static final int SIZE_ARRAY = 30;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        //make new gui.
        GUI gui = new GUI("", WIDTH, HEIGHT);
        DrawSurface d = gui.getDrawSurface();
        Line[] arr = new Line[SIZE_ARRAY];
        generateRandomLine(arr, d);
        drawMidOfLines(arr, d);
        drawLine(arr, d);
        gui.show(d);

    }

    /**
     * Generate random line.
     *
     * @param arr the arr
     * @param d   the draw surface
     */
    public static void generateRandomLine(Line[] arr, DrawSurface d) {
        int x1, y1, x2, y2;
        Point p1, p2;
        //random x and y to the points
        arr[0] = new Line(200, 300, 200, 800);
        for (int i = 0; i < SIZE_ARRAY; i++) {
            x1 = (int) (Math.random() * HEIGHT); // get integer in range 0-100
            y1 = (int) (Math.random() * HEIGHT); // get integer in range 0-100
            x2 = (int) (Math.random() * HEIGHT); // get integer in range 0-100
            y2 = (int) (Math.random() * HEIGHT); // get integer in range 0-100
            p1 = new Point(x1, y1);
            p2 = new Point(x2, y2);
            arr[i] = new Line(p1, p2);
            if (i == 0) {
                x1 = 100;
                y1 = 50;
                x2 = 100;
                y2 = 500;
                arr[0] = new Line(100, 50, 100, 500);
            }
            d.drawLine(x1, y1, x2, y2);
        }
    }

    /**
     * Draw a new line.
     *
     * @param arr the arr of lines
     * @param d   the draw surface
     */
    public static void drawLine(Line[] arr, DrawSurface d) {
        Point tempP;
        d.setColor(Color.RED);
        //checks if there is intersecting lines and compare between them.
        for (int i = 0; i < SIZE_ARRAY; i++) {
            for (int j = (i + 1); j < SIZE_ARRAY; j++) {
                tempP = arr[i].intersectionWith(arr[j]);
                //sign that there is intersection.
                if (tempP != null) {
                    d.fillCircle((int) tempP.getX(), (int) tempP.getY(), 3);
                }
            }
        }
    }


    /**
     * Draw mid of lines draw surface.
     *
     * @param arr the arr
     * @param d   the d
     */
    public static void drawMidOfLines(Line[] arr, DrawSurface d) {
        Point p1;
        d.setColor(Color.BLUE);
        //do circle
        for (int i = 0; i < SIZE_ARRAY; i++) {
            p1 = arr[i].middle();
            d.fillCircle((int) p1.getX(), (int) p1.getY(), 3);
        }

    }
}

