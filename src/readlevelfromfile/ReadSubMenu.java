package readlevelfromfile;

import java.io.Reader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.InputStream;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The type Read sub menu.
 */
public class ReadSubMenu {
    private Map<String, SubMenu> sub;
    private List<String> keys;

    /**
     * Instantiates a new Read sub menu.
     *
     * @param reader the reader
     */
    public ReadSubMenu(InputStream reader) {
        InputStream is = null;
        Reader targetReader = null;
        Map<String, SubMenu> subMenu = new HashMap<>();
        this.keys = new ArrayList<>();
        LineNumberReader lineNumberReader = null;
        try {
            //is = ClassLoader.getSystemClassLoader().getResourceAsStream(file.getName());
            if (reader != null) {
                targetReader = new InputStreamReader(reader);
            } else {
                System.out.println("exception - is is null");
            }
            if (targetReader != null) {
                lineNumberReader = new LineNumberReader(targetReader);
                String odd;
                String even;
                while (true) {
                    odd = lineNumberReader.readLine();
                    even = lineNumberReader.readLine();
                    if (odd == null || even == null) {
                        break;
                    }
                    keys.add(odd.substring(0, 1));
                    subMenu.put(odd.substring(0, 1), new SubMenu(odd.substring(2), even));
                }
            } else {
                System.out.println("there is problem");
            }
            this.sub = subMenu;
            if (targetReader != null) {
                targetReader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (lineNumberReader != null) {
                    lineNumberReader.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Gets keys.
     *
     * @return the keys
     */
    public List<String> getKeys() {
        return keys;
    }

    /**
     * Gets sub.
     *
     * @return the sub
     */
    public Map<String, SubMenu> getSub() {
        return sub;
    }
}