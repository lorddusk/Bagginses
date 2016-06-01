package nl.lang2619.bagginses.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import nl.lang2619.bagginses.Bagginses;

import java.util.List;


/**
 * Created by Tim on 9/5/2014.
 */
public class Upgrade extends Item {
    public Upgrade() {
        super();
        maxStackSize = 64;
        setCreativeTab(Bagginses.BagTab);
        this.setUnlocalizedName(ItemInfo.upgrade);
    }

    public String getUnlocalizedName(ItemStack itemStack) {
        return "upgrade";
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add("This won't do much by itself");
    }
}
