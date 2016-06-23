package nl.lang2619.bagginses.helpers;

/**
 * Created by alex on 22/06/16.
 */
public class BagInfo {

    private String color;
    private String description;

    public BagInfo(String color, String desc) {
        this.color = color;
        this.description = desc;
    }

    public String getColor() {
        return color;
    }

    public String getDescription() {
        return description;
    }
}
