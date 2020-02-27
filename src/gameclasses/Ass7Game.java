package gameclasses;

import animation.AnimationRunner;
import animation.HighScoresAnimation;
import animation.MenuAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import highscores.HighScoresTable;
import levels.LevelInformation;
import levels.Level4;
import levels.Level3;
import levels.Level2;
import levels.Level1;
import menu.QuitTask;
import menu.Menu;
import menu.StartGameTask;
import menu.Task;
import menu.ShowHiScoresTask;
import readlevelfromfile.LevelSpecificationReader;
import readlevelfromfile.ReadSubMenu;
import readlevelfromfile.SubMenu;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The type Ass 5 game.
 *
 * @author Tal Zigdon address talz2347@gmail.com
 * @version 1.6 (current version number of program)
 * @since 2019 -03-28.
 */
public class Ass7Game {
    /**
     * run the game.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        String key = KeyboardSensor.SPACE_KEY;
        LevelSpecificationReader levelReader = new LevelSpecificationReader();
        File file1 = new File("resources/highscores.ser");
        String title = "Arkanoid";
        List<LevelInformation> levels;
        GUI gui = new GUI("", 815, 600);
        AnimationRunner ar = new AnimationRunner(gui, 60);
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(title, ar);
        //Task<Void> result = menuResult(ar, menu);
        GameFlow gameFlow = new GameFlow(ar);
        HighScoresAnimation highScores = new HighScoresAnimation(makeHighScoresTable(),
                ar.getGui().getKeyboardSensor());
        // the parameters to addSelection are:
        // key to wait for, line to print, what to return
        //menu.addSelection("s", "Start Game", new StartGameTask(gameFlow, levels));
        menu.addSelection("h", "High Scores", new ShowHiScoresTask(ar,
                highScores));
        menu.addSelection("q", "Quit", new QuitTask(gui));

        //subMenu--
        try {
            File file;
            InputStream fileInp;
            if (args.length > 0) {
                fileInp = ClassLoader.getSystemClassLoader().getResourceAsStream(args[0] + ".txt");
            } else {
                fileInp = ClassLoader.getSystemClassLoader().getResourceAsStream("level_sets.txt");
            }
            //fileInp = ClassLoader.getSystemClassLoader().getResourceAsStream();
            ReadSubMenu readSubMenu = new ReadSubMenu(fileInp);
            Map<String, SubMenu> subMenuMap = readSubMenu.getSub();
            Menu<Task<Void>> subMenu = new MenuAnimation<>("Set Level", ar);
            for (String s : subMenuMap.keySet()) {
                InputStream tempR;
                tempR = ClassLoader.getSystemClassLoader().getResourceAsStream(subMenuMap.get(s).getPath());
                java.io.Reader reader = new InputStreamReader(tempR);
                levels = levelReader.fromReader(reader);
                subMenu.addSelection(s, subMenuMap.get(s).getName(), new StartGameTask(gameFlow, levels));
            }
            menu.addSubMenu("s", "Start Game", subMenu);
        } catch (Exception ex) {
            System.out.println("wrong source file");
        }
        highScores.setScores(file1);
        while (true) {
            ar.run(menu);
            menu.getStatus().run();
            highScores.setScores(file1);
        }
    }

    /**
     * Make levels list.
     *
     * @param args the args
     * @return the list
     */
    public static List<LevelInformation> makeLevels(String[] args) {
        List<LevelInformation> arr = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "1":
                    arr.add(new Level1());
                    break;
                case "2":
                    arr.add(new Level2());
                    break;
                case "3":
                    arr.add(new Level3());
                    break;
                case "4":
                    arr.add(new Level4());
                    break;
                default:
                    break;
            }
        }
        if (arr.size() == 0) {
            arr.add(new Level1());
            arr.add(new Level2());
            arr.add(new Level3());
            arr.add(new Level4());
        }
        return arr;
    }

    /**
     * Make high scores table high scores table.
     *
     * @return the high scores table
     */
    public static HighScoresTable makeHighScoresTable() {
        String fileName = "resources/highscores.ser";
        File file = new File(fileName);
        try {
            return HighScoresTable.loadFromFile(file);
        } catch
        (IOException ex) {
            System.out.println("exception29");
        }
        return null;
    }
}
