package finalproject.cpsc471_dbms;

/**
 * Created by wj-hong on 24/03/17.
 */

public class Item {

    private final String image;
    private final String name;

    public Item(String image, String name) {
        this.image = image;
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "Item: " + image + " called " + name;
    }
}
