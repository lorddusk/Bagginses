package nl.lang2619.bagginses.references;

import net.minecraft.item.ItemStack;

/**
 * Created by Tim on 2/9/2015.
 */
public class StackUtils {
    public static boolean isIdenticalItem(ItemStack lhs, ItemStack rhs) {
        if (lhs == null || rhs == null)
            return false;

        if (lhs.getItem() != rhs.getItem())
            return false;

        if (lhs.getItemDamage() != Defaults.WILDCARD)
            if (lhs.getItemDamage() != rhs.getItemDamage())
                return false;

        return ItemStack.areItemStackTagsEqual(lhs, rhs);
    }
}
