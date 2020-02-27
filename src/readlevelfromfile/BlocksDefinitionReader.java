package readlevelfromfile;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Blocks definition reader.
 */
public class BlocksDefinitionReader {
    private Map<String, String> def = new HashMap<>();

    /**
     * From reader blocks from symbols factory.
     *
     * @param reader the reader
     * @return the blocks from symbols factory
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        //BlocksFromSymbolsFactory symbolsFactory = new BlocksFromSymbolsFactory();
        Map<String, BlockCreator> blockCreatorMap = null;
        blockCreatorMap = new HashMap<>();
        Map<String, String> def = new HashMap<>();
        Map<String, Integer> spacerWidth = new HashMap<>();
        String read = null;
        String key = null;
        String[] split, split2;
        java.io.BufferedReader helper = new BufferedReader(reader);
        try {
            read = helper.readLine();
            while (read != null) {
                if (read.contains(" ")) {
                    split = read.substring(8).split(" ");
                    if (read.startsWith("default")) {
                        for (int i = 0; i < split.length; i++) {
                            split2 = split[i].split(":");
                            def.put(split2[0], split2[1]);
                        }
                    }
                    if (read.startsWith("bdef")) {
                        split = read.split(":");
                        split = split[1].split(" ");
                        BlockFactory blockCreator = new BlockFactory(read, def);
                        blockCreatorMap.put(split[0], blockCreator);
                    } else if (read.startsWith("sdef")) {
                        split = read.split(":");
                        split2 = split[1].split(" ");
                        spacerWidth.put(split2[0], Integer.parseInt(split[split.length - 1]));
                    }
                }
                read = helper.readLine();
            }
            helper.close();
            reader.close();
            return new BlocksFromSymbolsFactory(blockCreatorMap, spacerWidth);
        } catch (Exception ex) {
            System.err.println("exception12");
        }
        return null;
    }
}
