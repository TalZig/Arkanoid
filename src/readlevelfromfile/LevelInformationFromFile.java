package readlevelfromfile;

import collidablesprites.Block;
import collidablesprites.Sprite;
import collidablesprites.BackGround;
import collidablesprites.BackGroundImage;
import collidablesprites.BlockWithoutBlack;

import geometricshapes.Point;
import geometricshapes.Velocity;
import levels.LevelInformation;

import java.awt.Color;
import java.awt.Image;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level information from file.
 */
public class LevelInformationFromFile implements LevelInformation {
    private String levelName;
    private List<Velocity> velocities;
    private Image image = null;
    private int paddleSpeed;
    private int paddleWidth;
    private int blocksStartX;
    private int blocksStartY;
    private Color color = null;
    private int rowHeight;
    private int numOfBlocks;
    private List<String> blocks;
    private BlocksFromSymbolsFactory fromSymbolsFactory;
    private Color colorBackGround;
    private Image imageBackGround = null;
    private String blockdef;

    @Override
    public int numberOfBalls() {
        return this.velocities.size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return this.velocities;
    }

    /**
     * Initial ball velocities.
     *
     * @param velocitie the velocitie
     */
    public void setBallVelocities(List<Velocity> velocitie) {
        this.velocities = velocitie;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    @Override
    public Sprite getBackground() {
        if (imageBackGround == null) {
            BackGround backGround = new BackGround();
            backGround.addToBackGround(new BlockWithoutBlack(new Point(0, 0), 800, 600,
                    0, colorBackGround));
            return backGround;
        } else {
            return new BackGroundImage(imageBackGround);
        }
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList;
        blockList = this.fromSymbolsFactory.makeGameBlocks(blocks, blocksStartX, blocksStartY, rowHeight);
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }

    /**
     * From strings to level informatiom level information.
     *
     * @param strings the strings
     * @return the level information
     */
    public LevelInformation fromStringsToLevelInformatiom(List<String> strings) {
        blocks = new ArrayList<>();
        ColorParser cp = new ColorParser();
        String lvlName = null;
        this.paddleSpeed = 0;
        paddleWidth = 0;
        this.velocities = new ArrayList<>();
        int counter = 0;
        String[] stringToVelocity = null;
        String[] tempVelocity = null;
        String status = "";
        int startY = 0, startX = 0;
        for (String s : strings) {
            if (s.contains("START_BLOCKS")) {
                status = "START_BLOCKS";
            } else if (status.equals("START_BLOCKS")) {
                if (s.equals("END_BLOCKS")) {
                    status = "";
                } else {
                    blocks.add(s);
                }
            } else {
                if (s.contains(":")) {
                    String[] afterSplit = s.split(":");
                    if (afterSplit[0].startsWith("level_name")) {
                        this.levelName = afterSplit[1];
                        counter++;
                    } else if (afterSplit[0].startsWith("ball_velocities")) {
                        counter++;
                        stringToVelocity = afterSplit[1].split(" ");
                        for (int i = 0; i < stringToVelocity.length; i++) {
                            tempVelocity = stringToVelocity[i].split(",");
                            velocities.add(Velocity.fromAngleAndSpeed(Double.parseDouble(tempVelocity[0]),
                                    (Double.parseDouble(tempVelocity[1]))));
                        }
                    } else if (afterSplit[0].startsWith("paddle_speed")) {
                        paddleSpeed = Integer.parseInt(afterSplit[1]);
                        counter++;
                    } else if (afterSplit[0].startsWith("paddle_width")) {
                        paddleWidth = Integer.parseInt(afterSplit[1]);
                        counter++;
                    } else if (afterSplit[0].startsWith("blocks_start_y")) {
                        this.blocksStartY = Integer.parseInt(afterSplit[1]);
                        counter++;
                    } else if (afterSplit[0].startsWith("blocks_start_x")) {
                        this.blocksStartX = Integer.parseInt(afterSplit[1]);
                        counter++;
                    } else if (afterSplit[0].startsWith("row_height")) {
                        this.rowHeight = Integer.parseInt(afterSplit[1]);
                        counter++;
                    } else if (afterSplit[0].startsWith("num_blocks")) {
                        this.numOfBlocks = Integer.parseInt(afterSplit[1]);
                        counter++;
                    } else if (afterSplit[0].startsWith("block_definitions")) {
                        this.blockdef = (afterSplit[1]);
                        counter++;
                    } else if (afterSplit[0].startsWith("background")) {
                        counter++;
                        if (afterSplit[1].startsWith("color")) {
                            this.colorBackGround = cp.colorFromString(afterSplit[1]);
                        } else {
                            //imageBackGround = ImageParser.getImg("resources\\background_images\\" + afterSplit[1]);
                            imageBackGround = ImageParser.getImg(afterSplit[1]);
                        }
                    }
                }
            }
        }
        //check if there is all the arguments of the level
        if (counter < 10) {
            System.exit(2);
        }
        try {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(this.blockdef);
            java.io.Reader reader = new InputStreamReader(is);
            this.fromSymbolsFactory = BlocksDefinitionReader.fromReader(reader);
            reader.close();
            is.close();
        } catch (Exception ex1) {
            System.out.println("problem with blocks file");
        }
        return this;
    }

    /**
     * Gets blocks.
     *
     * @return the blocks
     */
    public List<String> getBlocks() {
        return blocks;
    }

/**
 * From strings to blocks info level information.
 *
 * @param strings the strings
 * @return the level information
 */
    /*
    public LevelInformation fromStringsToBlocksInfo(List<String> strings) {

    }
    */
    /**
     * Instantiates a new Level information from file.
     *
     * @param lvlName      the lvl name
     * @param velocitie    the velocitie
     * @param image        the image
     * @param paddleSpeed  the paddle speed
     * @param paddleWidth  the paddle width
     * @param blocksStartX the blocks start x
     * @param blocksStartY the blocks start y
     */
    /*public LevelInformationFromFile(String lvlName, List<Velocity> velocitie, Image image,
                                    int paddleSpeed, int paddleWidth, int blocksStartX, int blocksStartY) {
        this.levelName = lvlName;
        this.velocities = velocitie;
        this.image = image;
        this.paddleSpeed = paddleSpeed;
        this.paddleWidth = paddleWidth;
        this.blocksStartX = blocksStartX;
        this.blocksStartY = blocksStartY;
    }
    */

    /**
     * Instantiates a new Level information from file.
     *
     * @param lvlName      the lvl name
     * @param velocitie    the velocitie
     * @param color        the color
     * @param paddleSpeed  the paddle speed
     * @param paddleWidth  the paddle width
     * @param blocksStartX the blocks start x
     * @param blocksStartY the blocks start y
     */
    /*public LevelInformationFromFile(String lvlName, List<Velocity> velocitie, Color color,
                                    int paddleSpeed, int paddleWidth, int blocksStartX, int blocksStartY) {
        this.levelName = lvlName;
        this.velocities = velocitie;
        this.color = color;
        this.paddleSpeed = paddleSpeed;
        this.paddleWidth = paddleWidth;
        this.blocksStartX = blocksStartX;
        this.blocksStartY = blocksStartY;
    }
    */
}
