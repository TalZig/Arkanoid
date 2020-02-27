package readlevelfromfile;

/**
 * The type Sub menu.
 */
public class SubMenu {
    private String path;
    private String name;

    /**
     * Instantiates a new Sub menu path.
     *
     * @param name the name
     * @param path the path
     */
    public SubMenu(String name, String path) {
        this.path = path;
        this.name = name;
    }

    /**
     * Gets path.
     *
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }
}
