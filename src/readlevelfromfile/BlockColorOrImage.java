package readlevelfromfile;

import biuoop.DrawSurface;
import collidablesprites.Block;
import geometricshapes.Point;

import java.awt.Color;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Block color or image.
 */
public class BlockColorOrImage extends Block implements BlockCreator {
    private Map<Integer, Color> colorMap;
    private Map<Integer, String> imageMap;
    private int height;
    private int width;
    private int hitP;
    private Color stroke;
    private Map<Integer, Image> integerImageMap;

    /**
     * Instantiates a new Block color or image.
     *
     * @param p      the p
     * @param h      the h
     * @param w      the w
     * @param hitP   the hit p
     * @param c      the c
     * @param images the images
     * @param stroke the stroke
     */
    public BlockColorOrImage(Point p, int h, int w, int hitP, Map<Integer, Color> c,
                             Map<Integer, String> images, Color stroke) {
        super(p, (double) w, (double) h, hitP);
        this.height = h;
        this.width = w;
        this.stroke = stroke;
        this.hitP = hitP;
        this.colorMap = c;
        this.imageMap = images;
        //super.setCounter(hitP);
        if (stroke != null) {
            super.setColorScope(stroke);
        }
        integerImageMap = new HashMap<>();
        if (imageMap.size() > 0) {
            fromStringToImage();
        }
    }

    /**
     * Gets color list.
     *
     * @return the color list
     */
    public Map<Integer, Color> getColorMap() {
        return colorMap;
    }

    /**
     * Draw the blocks.
     *
     * @param surface surface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        int x = (int) this.getBlock().getUpperLeft().getX();
        int y = (int) this.getBlock().getUpperLeft().getY();
        int hitPoint = super.getCounter();
        if (hitPoint >= 1) {
            if (stroke != null) {
                surface.setColor(stroke);
            }
            if (this.imageMap.size() > 1 && this.imageMap.get(hitPoint) != null) {
                surface.drawImage(x, y, this.integerImageMap.get(hitPoint));
            } else if (this.colorMap.size() > 1 && this.colorMap.get(hitPoint) != null) {
                if (stroke != null) {
                    surface.drawRectangle(x, y, width, height);
                }
                surface.setColor(this.colorMap.get(hitPoint));
                surface.fillRectangle(x, y, width, height);
            } else if (colorMap.size() == 1 && colorMap.get(1) != null) {
                if (stroke != null) {
                    surface.drawRectangle(x, y, width, height);
                }
                surface.setColor(this.colorMap.get(1));
                surface.fillRectangle(x, y, width, height);
            } else if (imageMap.size() == 1 && imageMap.get(1) != null) {
                surface.drawImage(x, y, this.integerImageMap.get(1));
            } else {
                surface.setColor(Color.cyan);
                surface.fillRectangle(x, y, width, height);
            }
        }
    }

    /**
     * Gets image list.
     *
     * @return the image list
     */
    public Map<Integer, String> getImageMap() {
        return imageMap;
    }

    /**
     * Gets h.
     *
     * @return the h
     */
    public int getH() {
        return height;
    }

    /**
     * Gets w.
     *
     * @return the w
     */
    public int getW() {
        return width;
    }

    /**
     * Gets stroke.
     *
     * @return the stroke
     */
    public Color getStroke() {
        return stroke;
    }

    /**
     * From string to image.
     */
    public void fromStringToImage() {
        for (Integer key : imageMap.keySet()) {
            integerImageMap.put(key, ImageParser.getImg(imageMap.get(key)));
        }
    }

    /**
     * Create block.
     *
     * @param xpos the xpos
     * @param ypos the ypos
     * @return the block
     */
    @Override
    public Block create(int xpos, int ypos) {
        //Block b = new Block(new Point(xpos, ypos), getH(), getW(), stroke, hitP);
        //b.setHitMapColor(getColorMap());
        //b.setHitMapImage(getImageMap());
        //return b;
        BlockColorOrImage b = new BlockColorOrImage(new Point(xpos, ypos), getH(), getW(), this.hitP,
                getColorMap(), getImageMap(), getStroke());
        b.fromStringToImage();
        return b;
    }
}
