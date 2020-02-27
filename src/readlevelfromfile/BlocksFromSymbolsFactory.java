package readlevelfromfile;

import collidablesprites.Block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Blocks from symbols factory.
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * Instantiates a new Blocks from symbols factory.
     */
    public BlocksFromSymbolsFactory() {
        this.spacerWidths = new HashMap<>();
        this.blockCreators = new HashMap<>();
    }

    /**
     * Instantiates a new Blocks from symbols factory.
     *
     * @param blockCreators the block creators
     * @param spacerWidths  the spacer widths
     */
    public BlocksFromSymbolsFactory(Map<String, BlockCreator> blockCreators, Map<String, Integer> spacerWidths) {
        this.spacerWidths = spacerWidths;
        this.blockCreators = blockCreators;
    }

    /**
     * Is space symbol boolean.
     *
     * @param s the s
     * @return the boolean
     */
// returns true if 's' is a valid space symbol.
    public boolean isSpaceSymbol(String s) {
        return spacerWidths.containsKey(s);
    }

    /**
     * Is block symbol boolean.
     *
     * @param s the s
     * @return the boolean
     */
// returns true if 's' is a valid block symbol.
    public boolean isBlockSymbol(String s) {
        return blockCreators.containsKey(s);
    }

    /**
     * Gets block.
     *
     * @param s the s
     * @param x the x
     * @param y the y
     * @return the block
     */
// Return a block according to the definitions associated
    // with symbol s. The block will be located at position (xpos, ypos).
    public Block getBlock(String s, int x, int y) {
        return this.blockCreators.get(s).create(x, y);
    }

    /**
     * Gets space width.
     *
     * @param s the s
     * @return the space width
     */
// Returns the width in pixels associated with the given spacer-symbol.
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }

    /**
     * Make game blocks list.
     *
     * @param stringsOfBlocks the strings of blocks
     * @param x               the x
     * @param y               the y
     * @param rowHeight       the row height
     * @return the list
     */
    public List<Block> makeGameBlocks(List<String> stringsOfBlocks, int x, int y, int rowHeight) {
        List<Block> blockList = new ArrayList<>();
        for (int i = 0; i < stringsOfBlocks.size(); i++) {
            blockList.addAll(makeRowBlocks(stringsOfBlocks.get(i), x, y++));
            y = y + rowHeight;
        }
        return blockList;
    }

    /**
     * Make row blocks list.
     *
     * @param signs the signs
     * @param x     the x
     * @param y     the y
     * @return the list
     */
    public List<Block> makeRowBlocks(String signs, int x, int y) {
        List<Block> blockList = new ArrayList<>();
        for (int i = 0; i < signs.length(); i++) {
            String s = signs.substring(i, i + 1);
            if (isBlockSymbol(s)) {
                Block b = getBlock(s, x, y);
                if (b.getBlock().getWidth() < 80) {
                    x = x + (int) b.getBlock().getWidth() + 1;
                } else {
                    x = x + (int) b.getBlock().getWidth();
                }
                blockList.add(b);
            } else if (isSpaceSymbol(s)) {
                x = x + getSpaceWidth(s);
            }
        }
        return blockList;
    }
}
