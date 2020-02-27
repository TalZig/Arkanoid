package readlevelfromfile;

import levels.LevelInformation;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Level definitions.
 */
public class LevelDefinitions {
    private List<LevelInformation> list;

    /**
     * Instantiates a new Level definitions.
     */
    public LevelDefinitions() {
        list = new ArrayList<>();
    }

    /**
     * Add level.
     *
     * @param lvl the lvl
     */
    public void addLevel(LevelInformation lvl) {
        list.add(lvl);
    }

    /**
     * Gets list.
     *
     * @return the list
     */
    public List<LevelInformation> getList() {
        return list;
    }
}
