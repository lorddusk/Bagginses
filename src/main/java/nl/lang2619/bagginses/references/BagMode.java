package nl.lang2619.bagginses.references;

import net.minecraft.item.ItemStack;

/**
 * Created by alex on 19/06/16.
 */
public enum BagMode {

    DEFAULT("Default"),
    PICKUP("Pickup");

    private static BagMode[] vals = values();
    public BagMode next() {
        return vals[(this.ordinal()+1) % vals.length];
    }

    private String name;

    BagMode(String name) {
        this.name = name;
    }

    public static BagMode getMode(String name) {
        for (BagMode bagMode : BagMode.values()) {
            if (name.equals(bagMode.name))
                return bagMode;
        }
        return DEFAULT;
    }

    public String getName() {
        return this.name;
    }

    public static BagMode getMode(ItemStack stack) {
        if (stack.getTagCompound() != null
                && stack.getTagCompound().hasKey("bagMode"))
            return getMode(stack.getTagCompound().getString("bagMode"));
        return DEFAULT;
    }

}
