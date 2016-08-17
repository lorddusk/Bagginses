package nl.lang2619.bagginses.helpers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import nl.lang2619.bagginses.items.bags.Bag;

/**
 * Created by alex_ on 17/08/2016.
 */
public class BagFinder {

    public static boolean isItemNonNull(ItemStack itemStack) {
        return itemStack != null && itemStack.getItem() != null;
    }

    public static ItemStack getBag(EntityPlayer player) {
        if (isItemNonNull(player.getHeldItemOffhand())
                && player.getHeldItemOffhand().getItem() instanceof Bag)
            return player.getHeldItemOffhand();
        for (int i = 0; i < 9; i++) {
            if (isItemNonNull(player.inventory.getStackInSlot(i))
                    && player.inventory.getStackInSlot(i).getItem() instanceof Bag) {
                return player.inventory.getStackInSlot(i);
            }
        }
        return null;
    }

}
