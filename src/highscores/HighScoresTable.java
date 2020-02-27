package highscores;

import java.io.InputStream;
import java.io.IOException;
import java.io.Serializable;
import java.io.File;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * The type High scores table.
 */
public class HighScoresTable implements Serializable {
    private List<ScoreInfo> table;
    private int size;

    /**
     * Instantiates a new High scores table.
     *
     * @param size the size
     */
// Create an empty high-scores table with the specified size.
    // The size means that the table holds up to size top scores.
    public HighScoresTable(int size) {
        this.size = size;
        this.table = new ArrayList<>(size);
    }

    /**
     * Instantiates a new High scores table.
     */
    public HighScoresTable() {
        this.size = 10;
        this.table = new ArrayList<>();
    }

    /**
     * Instantiates a new High scores table.
     *
     * @param size the size
     * @param list the list
     */
    public HighScoresTable(int size, List<ScoreInfo> list) {
        this.size = size;
        this.table = list;
    }

    /**
     * Add.
     *
     * @param score the score
     */
// Add a high-score.
    public void add(ScoreInfo score) {
        if (this.table.size() < size) {
            this.table.add(score);
            //table.sort(ScoreInfo::compareTo);
            sortTable();
        } else {
            if (this.getRank(score.getScore()) < size) {
                table.remove(size - 1);
                table.add(score);
                //table.sort(ScoreInfo::compareTo);
                sortTable();
            }
        }
    }

    /**
     * Size int.
     *
     * @return the int
     */
// Return table size.
    public int size() {
        return this.table.size();
    }

    /**
     * Gets high scores.
     *
     * @return the high scores
     */
// Return the current high scores.
    // The list is sorted such that the highest
    // scores come first.
    public List<ScoreInfo> getHighScores() {
        return this.table;
    }

    /**
     * Gets rank.
     *
     * @param score the score
     * @return the rank
     */
// return the rank of the current score: where will it
    // be on the list if added?
    // Rank 1 means the score will be highest on the list.
    // Rank `size` means the score will be lowest.
    // Rank > `size` means the score is too low and will not
    //      be added to the list.
    public int getRank(int score) {
        for (ScoreInfo s : table) {
            if (s.getScore() <= score) {
                return table.indexOf(s);
            }
        }
        return table.size();
    }

    /**
     * Clear.
     */
// Clears the table
    public void clear() {
        int s = table.size();
        for (int i = s - 1; i >= 0; i--) {
            table.remove(i);
        }
    }

    /**
     * Load.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
// Load table data from file.
    // Current table data is cleared.
    public void load(File filename) throws IOException {
        HighScoresTable highScoresTable = new HighScoresTable();
        //try {
        // Reading the object from a file.
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
        try {
            if (filename.exists()) {
                highScoresTable = (HighScoresTable) in.readObject();
                this.table = highScoresTable.table;
                this.size = highScoresTable.size;
            }
            //}
        } catch (ClassNotFoundException e) {
            throw new IOException(e);
            //} catch (IOException ex) {
            //    System.out.println("IOException is caught");
        }
        in.close();
    }

    /**
     * Save.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
// Save table data to the specified file.
    public void save(File filename) throws IOException {
        //Saving of object in a file.
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
        try {
            // Method for serialization of object.
            //for (ScoreInfo s : table) {
            out.writeObject(this);
        } catch (Exception ex) {
            System.err.println("not good");
            //}
            out.close();
        }
    }

    /**
     * Load from file high scores table.
     *
     * @param filename the filename
     * @return the high scores table
     * @throws IOException the io exception
     */
// Read a table from file and return it.
    // If the file does not exist, or there is a problem with
    // reading it, an empty table is returned.
    public static HighScoresTable loadFromFile(File filename) throws IOException {
        if (filename == null) {
            return null;
        }
        //InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(filename.getName());
        HighScoresTable highScoresTable = new HighScoresTable();
        ObjectInputStream in = null;
        // Reading the object from a file.
        //in = new ObjectInputStream(new FileInputStream(filename));
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(filename.getName());
        // Reading the object from a file.
        in = new ObjectInputStream(is);
        try {
            if (filename.exists()) {
                highScoresTable = (HighScoresTable) in.readObject();
            }
        } catch (ClassNotFoundException e) {
            throw new IOException(e);
        }
        in.close();
        return highScoresTable;
    }

    /**
     * Sort table.
     */
    public void sortTable() {
        ScoreInfo temp;
        for (int i = 0; i < this.table.size() - 1; i++) {
            for (int j = i + 1; j < this.table.size(); j++) {
                if (table.get(j).getScore() > table.get(i).getScore()) {
                    temp = table.get(j);
                    table.remove(j);
                    table.add(j, table.get(i));
                    table.remove(i);
                    table.add(i, temp);
                }
            }
        }
    }
}
