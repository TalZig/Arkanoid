package readlevelfromfile;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Image parser.
 */
public class ImageParser {

    /**
     * Instantiates a new Image parser.
     */
    public ImageParser() {
    }

    /**
     * Gets img.
     *
     * @param s the s
     * @return the img
     */
    public static Image getImg(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        s = s.substring(6, s.length() - 1);
        if (s.endsWith("3.png")) {
            s = s.substring(0, s.length() - 4);
            s = s + ".jpg";
        }
        s = "resources/" + s;
        Image img = null;
        try {
            img = ImageIO.read(new File(s));
        } catch (IOException e) {
            System.out.println("Error in reading image file");
        }
        return img;
    }

    /**
     * Gets images.
     *
     * @param l the l
     * @return the images
     */
    public java.util.List<Image> getImages(java.util.List<String> l) {
        List<Image> imageList = new ArrayList<>();
        for (String s : l) {
            imageList.add(getImg(s));
        }
        return imageList;
    }
}
