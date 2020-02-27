package readlevelfromfile;

import levels.LevelInformation;

import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level specification reader.
 */
public class LevelSpecificationReader {

    /**
     * From reader list.
     *
     * @param reader the reader
     * @return the list
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        if (reader == null) {
            System.out.println("Error6!");
            return null;
        }
        List<String> strings = null;
        List<LevelInformation> levels = new ArrayList<>();
        LineNumberReader ln = new LineNumberReader(reader);
        strings = fromFileToStrings(ln);
        while (strings != null) {
            LevelInformationFromFile lvl = new LevelInformationFromFile();
            levels.add(lvl.fromStringsToLevelInformatiom(strings));
            strings = fromFileToStrings(ln);
        }
        try {
            ln.close();
        } catch (Exception ex) {
            System.out.println("problem with close");
        }
        return levels;
    }

    /**
     * From file to strings list.
     *
     * @param ln the ln
     * @return the list
     */
    public List<String> fromFileToStrings(LineNumberReader ln) {
        List<String> list = new ArrayList<>();
        String tempString = null;
        //LineNumberReader lN = new LineNumberReader(reader);
        try {
            if (ln != null) {
                tempString = ln.readLine();
            } else {
                return null;
            }
            while (tempString != null && !tempString.equals("END_LEVEL")) {
                list.add(tempString);
                tempString = ln.readLine();
            }
            //ln.close();
        } catch (
                Exception ex) {
            System.err.println("exception");
        }
        if (tempString != null) {
            return list;
        }
        return null;
    }
}

/**
 * From strings to level informatiom level information.
 *
 * @param strings the strings
 * @return the level information
 */
    /*
    public LevelInformation fromStringsToLevelInformatiom(List<String> strings) {
        LevelInformation lvl = null;
        String lvlName = null;
        int paddleWidth = 0, paddleSpeed = 0;
        List<Velocity> velocities = new ArrayList<>();
        String[] stringToVelocity = null;
        String[] tempVelocity = null;
        int startY = 0, startX = 0;
        for (String s : strings) {
            if (s.contains(":")) {
                String[] afterSplit = s.split(":");
                if (afterSplit[0].startsWith("level_name")) {
                    lvlName = afterSplit[1];
                } else if (afterSplit[0].startsWith("ball_velocities")) {
                    stringToVelocity = afterSplit[1].split(" ");
                    for (int i = 1; i < stringToVelocity.length; i++) {
                        tempVelocity = afterSplit[1].split(",");
                        velocities.add(Velocity.fromAngleAndSpeed(Double.parseDouble(tempVelocity[0]),
                                Double.parseDouble(tempVelocity[1])));
                    }
                } else if (afterSplit[0].startsWith("paddle_speed")) {
                    paddleSpeed = Integer.parseInt(afterSplit[1]);
                } else if (afterSplit[0].startsWith("paddle_width")) {
                    paddleWidth = Integer.parseInt(afterSplit[1]);
                } else if (afterSplit[0].startsWith("blocks_start_y")) {
                    startY = Integer.parseInt(afterSplit[1]);
                } else if (afterSplit[0].startsWith("blocks_start_x")) {
                    startX = Integer.parseInt(afterSplit[1]);
                }
            }
        }
        return new LevelInformationFromFile(lvlName, velocities, Color.BLUE,
                paddleSpeed, paddleWidth, startX, startY);
    }
    */
