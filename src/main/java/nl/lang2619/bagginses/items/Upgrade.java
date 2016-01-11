package nl.lang2619.bagginses.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import nl.lang2619.bagginses.Bagginses;


/**
 * Created by Tim on 9/5/2014.
 */
public class Upgrade extends Item {
    public Upgrade() {
        super();
        maxStackSize = 1;
        setCreativeTab(Bagginses.BagTab);
        this.setUnlocalizedName(ItemInfo.upgrade);
    }

    public String getUnlocalizedName(ItemStack itemStack) {
        return "upgrade";
    }
}
