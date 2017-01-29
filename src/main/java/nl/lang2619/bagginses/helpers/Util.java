package nl.lang2619.bagginses.helpers;

import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;

/**
 * Created by Alex on 28/01/2017.
 */
public class Util {

    public static Integer intFromColour(String colour) {
        for (EnumDyeColor dyeColor : EnumDyeColor.values()) {
            if (dyeColor.name().equalsIgnoreCase(colour))
                return dyeColor.getMetadata();
        }
        return 0;
    }

    public static boolean isSoulbound(ItemStack stack) {
        return stack != null
                && stack.getTagCompound() != null
                && stack.getTagCompound().hasKey("soulbound")
                && stack.getTagCompound().getBoolean("soulbound");
    }
}
