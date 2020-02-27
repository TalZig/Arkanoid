package collidablesprites;

import biuoop.DrawSurface;
import gameclasses.GameLevel;
import geometricshapes.Line;
import geometricshapes.Point;
import geometricshapes.Rectangle;
import geometricshapes.Velocity;
import listeners.HitListener;
import listeners.HitNotifier;
import readlevelfromfile.ImageParser;
import java.awt.Image;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The type Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle block;
    private int counter;
    private Color color;
    private Color colorScope;
    private List<HitListener> hitListeners = new ArrayList<>();
    private Color stroke = null;
    private Color fill = null;
    private Map<Integer, Color> saveHitMapColor;
    private Map<Integer, String> saveHitMapImage;
    private Map<Integer, Image> integerImageMap;
    private boolean isTouchBef = false;

    /**
     * Instantiates a new Block.
     *
     * @param block the block
     * @param count the count
     */
    public Block(Rectangle block, int count) {
        this.block = block;
        this.counter = count;
        this.color = Color.black;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Instantiates a new Block.
     *
     * @param block the block
     * @param count the count
     * @param phl   the phl
     */
    public Block(Rectangle block, int count, ArrayList<HitListener> phl) {
        this.block = block;
        this.counter = count;
        this.color = Color.black;
        this.hitListeners = new ArrayList<HitListener>();
        this.hitListeners = phl;
    }

    /**
     * Instantiates a new Block.
     *
     * @param block the block
     */
    public Block(Rectangle block) {
        this.block = block;
        this.counter = 0;
        this.color = Color.black;
    }

    /**
     * Instantiates a new Block.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     */
    public Block(Point upperLeft, double width, double height) {
        this.block = new Rectangle(upperLeft, width, height);
        this.counter = 0;
        this.color = Color.black;
    }

    /**
     * Instantiates a new Block.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     * @param count     the count
     */
    public Block(Point upperLeft, double width, double height, int count) {
        this.block = new Rectangle(upperLeft, width, height);
        this.counter = count;
    }

    /**
     * Instantiates a new Block.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     * @param count     the count
     * @param color     the color
     */
    public Block(Point upperLeft, double width, double height, int count, Color color) {
        this.block = new Rectangle(upperLeft, width, height);
        this.counter = count;
        this.color = color;
    }

    /**
     * Instantiates a new Block.
     *
     * @param phl       list of listeners
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     * @param count     the count
     * @param color     the color
     */
    public Block(List<HitListener> phl, Point upperLeft, double width, double height, int count, Color color) {
        this.block = new Rectangle(upperLeft, width, height);
        this.counter = count;
        this.color = color;
        this.hitListeners = new ArrayList<>();
        this.hitListeners.addAll(phl);
    }

    /**
     * Instantiates a new Block.
     *
     * @param width   the width
     * @param height  the height
     * @param counter the counter
     * @param map     the map
     */
    public Block(double width, double height, int counter, Map<Integer, Color> map) {

    }

    /**
     * Instantiates a new Block.
     *
     * @param upperLeft   the upper left
     * @param height      the height
     * @param width       the width
     * @param colorStroke the color stroke
     * @param hitNum      the hit num
     */
    public Block(Point upperLeft, double height, double width, Color colorStroke, int hitNum) {
        this.block = new Rectangle(upperLeft, width, height);
        this.stroke = colorStroke;
        this.counter = hitNum;

    }

    /**
     * Gets collision rectangle.
     *
     * @return the collision rectangle
     */
// Return the "collision shape" of the object.
    public Rectangle getCollisionRectangle() {
        return this.block;

    }

    /**
     * Hit velocity.
     *
     * @param hitter          the hitter
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the velocity
     */
// Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Line leftLine, rightLine, upLine, downLine;
        //decrease the number on the block
        if (!isTouchBef) {
            this.counter--;
            isTouchBef = true;
        } else {
            isTouchBef = false;
        }
        double newDx, newDy;
        newDx = currentVelocity.getDx();
        newDy = currentVelocity.getDy();
        leftLine = new Line(this.block.getUpperLeft(), this.block.getDownnerLeft());
        rightLine = new Line(this.block.getUpperRight(), this.block.getDownnerRight());
        upLine = new Line(this.block.getUpperLeft(), this.block.getUpperRight());
        downLine = new Line(this.block.getDownnerLeft(), this.block.getDownnerRight());
        //check if the collision point is on the left/right line
        if ((rightLine.isPointOnLine(collisionPoint) && currentVelocity.getDx() < 0)
                || (currentVelocity.getDx() > 0 && leftLine.isPointOnLine(collisionPoint))) {
            newDx = -currentVelocity.getDx();
        }
        //check if the collision point is on the up/down line.
        if ((upLine.isPointOnLine(collisionPoint) && currentVelocity.getDy() > 0)
                || (currentVelocity.getDy() < 0 && downLine.isPointOnLine(collisionPoint))) {
            newDy = -currentVelocity.getDy();
        }
        this.notifyHit(hitter);
        return new Velocity(newDx, newDy);
    }

    /**
     * Draw the blocks on the surface.
     *
     * @param surface the surface
     */
    public void drawOn(DrawSurface surface) {
        if (saveHitMapImage == null && saveHitMapColor == null) {
            surface.setColor(this.color);
            surface.fillRectangle((int) this.block.getUpperLeft().getX(), (int) this.block.getUpperLeft().getY(),
                    (int) this.block.getWidth(), (int) this.block.getHeight());
            surface.setColor(Color.black);
            surface.drawRectangle((int) this.block.getUpperLeft().getX(), (int) this.block.getUpperLeft().getY(),
                    (int) this.block.getWidth(), (int) this.block.getHeight());
        } else {
            drawOn2(surface);
        }
    }


    /**
     * Draw the blocks.
     *
     * @param surface surface to draw on.
     */
    public void drawOn2(DrawSurface surface) {
        int x = (int) this.getBlock().getUpperLeft().getX();
        int y = (int) this.getBlock().getUpperLeft().getY();
        int hitPoint = counter;
        if (hitPoint >= 1) {
            surface.setColor(stroke);
            surface.drawRectangle(x, y, (int) getBlock().getWidth(), (int) getBlock().getHeight());
            if (this.saveHitMapImage.size() > 1 && this.saveHitMapImage.get(hitPoint - 1) != null) {
                surface.drawImage(x, y, ImageParser.getImg(this.saveHitMapImage.get(hitPoint - 1)));
            } else if (this.saveHitMapColor.size() > 1 && this.saveHitMapColor.get(hitPoint - 1) != null) {
                surface.setColor(this.saveHitMapColor.get(hitPoint - 1));
                surface.fillRectangle(x, y, (int) getBlock().getWidth(), (int) getBlock().getHeight());
            } else if (saveHitMapColor.size() == 1 && saveHitMapColor.get(1) != null) {
                surface.setColor(this.saveHitMapColor.get(1));
                surface.fillRectangle(x, y, (int) getBlock().getWidth(), (int) getBlock().getHeight());
            } else if (saveHitMapColor.size() == 1 && saveHitMapColor.get(1) != null) {
                surface.drawImage(x, y, ImageParser.getImg(this.saveHitMapImage.get(1)));
            } else {
                surface.setColor(Color.MAGENTA);
                surface.fillRectangle(x, y, (int) getBlock().getWidth(), (int) getBlock().getHeight());
            }
        }
    }

    /**
     * Time passed.
     */
    public void timePassed() {
    }

    /**
     * Add to game.
     *
     * @param g the g
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Write number of "life" to the block. if there isn't life write "X"
     *
     * @param surface the surface
     */
    public void writeNum(DrawSurface surface) {
        double width, height, x, y;
        String string;
        string = "" + counter;
        x = this.block.getUpperLeft().getX();
        y = this.block.getUpperLeft().getY();
        width = this.block.getWidth();
        height = this.block.getHeight();
        surface.setColor(Color.WHITE);
        if (this.counter > 0) {
            surface.drawText((int) (x + (width / 2)), (int) (y + (height / 2)), string, 12);
        } else {
            surface.drawText((int) (x + (width / 2)), (int) (y + (height / 2)), "", 12);
        }
    }

    /**
     * Remove from gameLevel.
     *
     * @param gameLevel the gameLevel
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * Notify hit.
     *
     * @param hitter the hitter
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        if (this.hitListeners != null) {
            List<HitListener> listeners = new ArrayList<>(this.hitListeners);
            // Notify all listeners about a hit event:
            for (HitListener hl : listeners) {
                hl.hitEvent(this, hitter);
            }
        }
    }

    /**
     * Add hit listener.
     *
     * @param h the hit listener
     */
// Add hl as a listener to hit events.
    public void addHitListener(HitListener h) {
        this.hitListeners.add(h);
    }

    /**
     * Remove hit listener.
     *
     * @param h the hl
     */
// Remove hl from the list of listeners to hit events.
    public void removeHitListener(HitListener h) {
        this.hitListeners.remove(h);
    }

    /**
     * Gets hit points.
     *
     * @return the hit points
     */
    public String getHitPoints() {
        String hitPointString = "";
        for (HitListener h : this.hitListeners) {
            hitPointString = hitPointString + h.toString();
        }
        return hitPointString;
    }

    /**
     * Gets block.
     *
     * @return the block
     */
    public Rectangle getBlock() {
        return block;
    }

    /**
     * Gets counter.
     *
     * @return the counter
     */
    public int getCounter() {
        return this.counter;
    }

    /**
     * Sets counter.
     *
     * @param counter1 the counter
     */
    public void setCounter(int counter1) {
        this.counter = counter;
    }

    /**
     * Sets hit listeners.
     *
     * @param hitListen the hit listen
     */
    public void setHitListeners(List<HitListener> hitListen) {
        this.hitListeners.addAll(hitListen);
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets color scope.
     *
     * @param colorin the colorin
     */
    public void setColorScope(Color colorin) {

        this.colorScope = colorin;
    }

    /**
     * Gets color scope.
     *
     * @return the color scope
     */
    public Color getColorScope() {
        return colorScope;
    }

    /**
     * Sets hit map image.
     *
     * @param hitMapImage the hit map image
     */
    public void setHitMapImage(Map<Integer, String> hitMapImage) {
        saveHitMapImage = hitMapImage;
        if (saveHitMapImage.size() > 0) {
            this.fromStringToImage();
        }
    }

    /**
     * Sets stroke.
     *
     * @param colorStroke the color stroke
     */
    public void setStroke(Color colorStroke) {
        stroke = colorStroke;
    }

    /**
     * Sets image.
     *
     * @param name the name
     */
    //public void setImage(String name) {
    //  image = name;
    //}

    /**
     * Sets hit map color.
     *
     * @param hitMapColor the hit map color
     */
    public void setHitMapColor(Map<Integer, Color> hitMapColor) {
        saveHitMapColor = hitMapColor;
    }

    /**
     * From string to image.
     */
    public void fromStringToImage() {
        for (Integer key : saveHitMapImage.keySet()) {
            integerImageMap.put(key, ImageParser.getImg(saveHitMapImage.get(key)));
        }
    }
}
