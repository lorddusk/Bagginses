package nl.lang2619.bagginses.references;

/**
 * Created by alex on 19/06/16.
 */
public enum BagMode {

    DEFAULT("DEFAULT"),
    PICKUP("PICKUP");

    private static BagMode[] vals = values();
    public BagMode next() {
        return vals[(this.ordinal()+1) % vals.length];
    }

    private String name;

    BagMode(String name) {
        this.name = name;
    }

    public BagMode getMode(String name) {
        for (BagMode bagMode : BagMode.values()) {
            if (name.equals(bagMode.name))
                return bagMode;
        }
        return DEFAULT;
    }

    public String getName() {
        return this.name;
    }
}
