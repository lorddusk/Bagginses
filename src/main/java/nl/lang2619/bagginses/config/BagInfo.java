package nl.lang2619.bagginses.config;

/**
 * Created by Alex on 29/01/2017.
 */
public class BagInfo {

    private String color;
    private String description;

    public BagInfo(String color, String desc) {
        this.color = color;
        this.description = desc;
    }

    String getColor() {
        return color;
    }

    String getDescription() {
        return description;
    }
}
