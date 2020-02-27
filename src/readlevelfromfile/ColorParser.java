package readlevelfromfile;

import java.awt.Color;

/**
 * The type Color parser.
 */
public class ColorParser {
    /**
     * Color from string java . awt . color.
     *
     * @param s the s
     * @return the java . awt . color
     */
// parse color definition and return the specified color.
    public java.awt.Color colorFromString(String s) {
        switch (s) {
            case "color(white)":
                return Color.WHITE;
            case "color(black)":
                return Color.BLACK;
            case "color(blue)":
                return Color.BLUE;
            case "color(cyan)":
                return Color.CYAN;
            case "color(gray)":
                return Color.GRAY;
            case "color(lightGray)":
                return Color.GRAY.brighter();
            case "color(green)":
                return Color.GREEN;
            case "color(orange)":
                return Color.ORANGE;
            case "color(pink)":
                return Color.PINK;
            case "color(yellow)":
                return Color.YELLOW;
            case "color(red)":
                return Color.RED;
            default:
                return colorFromRgb(s);
        }
    }

    /**
     * R gb colors java . awt . color.
     *
     * @param s the s
     * @return the java . awt . color
     */
    private java.awt.Color colorFromRgb(String s) {
        if (s.startsWith("color(RGB")) {
            String str1 = s.substring(10);
            String[] colors = str1.split(",");
            int[] nums = new int[3];
            for (int i = 0; i < 3; i++) {
                if (colors[i].indexOf(")") > 0) {
                    colors[i] = colors[i].substring(0, colors[i].indexOf(")"));
                }
                nums[i] = Integer.parseInt(colors[i]);
            }
            return new Color(nums[0], nums[1], nums[2]);
        }
        return null;
    }
}
