package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collidablesprites.Sprite;
import collidablesprites.BackGround;
import collidablesprites.BallWithoutBlack;
import collidablesprites.Block;
import collidablesprites.Line;
import geometricshapes.Point;
import menu.Menu;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Menu animation.
 *
 * @param <T> the type parameter
 */
public class MenuAnimation<T> implements Menu<T> {
    private String title;
    private ArrayList<String> keys;
    private ArrayList<String> messages;
    private ArrayList<T> whatToRetu;
    private boolean stop = false;
    private KeyboardSensor keyboard;
    private T status;
    private Map<String, Menu<T>> subMenus = new HashMap<>();
    private boolean isSubMenu = false;
    private AnimationRunner ar;

    /**
     * Instantiates a new Menu animation.
     *
     * @param titl the titl
     * @param ar   the ar
     */
    public MenuAnimation(String titl, AnimationRunner ar) {
        this.title = titl;
        this.keys = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.whatToRetu = new ArrayList<>();
        this.keyboard = ar.getGui().getKeyboardSensor();
        this.ar = ar;
    }

    @Override
    public void addSelection(String key, String message, T returnVal) {
        this.keys.add(key);
        this.messages.add(message);
        this.whatToRetu.add(returnVal);
        status = null;
    }

    @Override
    public T getStatus() {
        return status;
    }

    @Override
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        this.subMenus.put(key, subMenu);
        isSubMenu = false;
        this.keys.add(key);
        this.messages.add(message);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        Sprite b = getBackground();
        b.drawOn(d);
        this.status = null;
        this.stop = false;
        d.drawText(10, d.getHeight() / 6, this.title, 42);
        for (String s : keys) {
            d.drawText(10, d.getHeight() / 2 + (keys.indexOf(s) * 40), "press - " + s + " to "
                    + this.messages.get(keys.indexOf(s)), 32);
            if (this.keyboard.isPressed(s)) {
                if (this.keyboard.isPressed("s")) {
                    isSubMenu = true;
                }
                if (isSubMenu && subMenus.get(s) != null) {
                    this.ar.run(subMenus.get(s));
                    isSubMenu = false;
                    this.status = this.subMenus.get(s).getStatus();
                    stop = true;
                } else if (!isSubMenu) {
                    this.status = this.whatToRetu.get(keys.indexOf(s));
                    stop = true;
                }
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return stop;
    }

    /**
     * Gets background.
     *
     * @return the background
     */
    public Sprite getBackground() {
        Point temp1 = new Point(0, 0);
        Point temp2;
        BackGround lvlBackGround = new BackGround();
        lvlBackGround.addToBackGround(new Block(temp1, 820, 600, 0, new Color(0x31C3FF)));
        for (int i = 0; i < 10; i++) {
            temp2 = new Point((10 * i + 70), 400);
            temp1 = new Point((10 * i + 60), 600);
            lvlBackGround.addToBackGround(new Line(temp1, temp2, Color.WHITE));
            temp1 = new Point(10 * i + 600, 500);
            temp2 = new Point(10 * i + 590, 600);
            lvlBackGround.addToBackGround(new Line(temp1, temp2, Color.WHITE));
        }
        lvlBackGround.addToBackGround(new BallWithoutBlack(new Point(120, 400),
                45, new Color(204, 204, 204)));
        lvlBackGround.addToBackGround(new BallWithoutBlack(new Point(160, 410),
                30, new Color(204, 204, 204)));
        lvlBackGround.addToBackGround(new BallWithoutBlack(new Point(100, 430),
                30, new Color(153, 153, 153)));
        lvlBackGround.addToBackGround(new BallWithoutBlack(new Point(85, 420),
                33, new Color(153, 153, 153)));
        lvlBackGround.addToBackGround(new BallWithoutBlack(new Point(620, 500),
                40, new Color(204, 204, 204)));
        lvlBackGround.addToBackGround(new BallWithoutBlack(new Point(590, 485),
                30, new Color(204, 204, 204)));
        lvlBackGround.addToBackGround(new BallWithoutBlack(new Point(650, 490),
                30, new Color(153, 153, 153)));
        lvlBackGround.addToBackGround(new BallWithoutBlack(new Point(675, 510),
                33, new Color(153, 153, 153)));
        return lvlBackGround;
    }
}
