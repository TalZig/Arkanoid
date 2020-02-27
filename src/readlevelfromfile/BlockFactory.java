package readlevelfromfile;

import collidablesprites.Block;
import geometricshapes.Point;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Block factory.
 */
public class BlockFactory implements BlockCreator {
    private Map<String, Integer> spacerWidths;
    private Map<Integer, Color> colors = new HashMap<>();
    private Map<Integer, String> images = new HashMap<>();
    private Map<String, String> fields = new HashMap<>();
    private Map<String, String> def = new HashMap<>();
    private Block block;

    /**
     * Instantiates a new Block factory.
     *
     * @param string the string
     * @param defMap the def map
     */
    public BlockFactory(String string, Map<String, String> defMap) {
        def = defMap;
        String[] str = string.split(" ");
        String[] tempStr;
        String key = null;
        for (int i = 1; i < str.length; i++) {
            tempStr = str[i].split(":");
            if (str[i].startsWith("symbol")) {
                fields.put("symbol", str[i].substring(7));
            } else if (str[i].startsWith("width")) {
                fields.put("width", str[i].substring(6));
            } else if (str[i].startsWith("hit_points")) {
                fields.put("hit_points", str[i].substring(11));
            } else if (str[i].startsWith("fill")) {
                fields.put(tempStr[0], tempStr[1]);
            } else if (str[i].startsWith("stroke")) {
                fields.put(tempStr[0], tempStr[1]);
            } else if (str[i].startsWith("height")) {
                fields.put(tempStr[0], tempStr[1]);
            }
        }
        if (!fields.containsKey("hit_points")) {
            if (def.containsKey("hit_points")) {
                fields.put("hit_points", def.get("hit_points"));
            } else {
                System.exit(20);
            }
        }
        if (!fields.containsKey("stroke")) {
            if (def.containsKey("stroke")) {
                fields.put("stroke", def.get("stroke"));
            } else {
                fields.put("stroke", null);
            }
        }
        if (!fields.containsKey("width")) {
            if (def.containsKey("width")) {
                fields.put("width", def.get("width"));
            } else {
                System.exit(22);
            }
        }
        if (!fields.containsKey("height")) {
            if (def.containsKey("height")) {
                fields.put("height", def.get("height"));
            } else {
                System.exit(24);
            }
        }
        if (!fields.containsKey("symbol")) {
            if (def.containsKey("symbol")) {
                fields.put("symbol", def.get("symbol"));
            } else {
                System.exit(23);
            }
        }
        if (!fields.containsKey("fill") && !fields.containsKey("fill-1")) {
            if (def.containsKey("fill")) {
                fields.put("fill", def.get("fill"));
            } else {
                if (def.containsKey("fill-1")) {
                    fields.put("fill-1", def.get("fill-1"));
                    //System.exit(21);
                }
            }


        }
    }

    @Override
    public Block create(int xpos, int ypos) {
        Color stroke;
        ColorParser cp = new ColorParser();
        int widt = Integer.valueOf(fields.get("width"));
        int height = Integer.valueOf(fields.get("height"));
        int hitP = Integer.valueOf(fields.get("hit_points"));
        if (fields.get("stroke") == null) {
            stroke = null;
        } else {
            stroke = cp.colorFromString(fields.get("stroke"));
        }
        if (fields.containsKey("fill")) {
            if (fields.get("fill").contains("color")) {
                colors.put(1, cp.colorFromString(fields.get("fill")));

            } else {
                images.put(1, fields.get("fill"));
            }
        }
        for (int i = 1; i <= hitP; i++) {
            String colorString = "fill-" + i;
            if (fields.containsKey(colorString)) {
                if (fields.get(colorString).contains("color")) {
                    colors.put(i, cp.colorFromString(fields.get(colorString)));

                } else {
                    images.put(i, fields.get(colorString));
                }
            }
        }
        return new BlockColorOrImage(new Point(xpos, ypos), height, widt, hitP, colors, images, stroke);
    }
}
