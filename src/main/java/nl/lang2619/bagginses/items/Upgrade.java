package nl.lang2619.bagginses.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import nl.lang2619.bagginses.Bagginses;

import java.util.List;

/**
 * Created by Alex on 28/01/2017.
 */
public class Upgrade extends BagginsesItem {

    public Upgrade() {
        super();
        maxStackSize = 64;
        this.setUnlocalizedName("upgrade");
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add("This won't do much by itself");
    }


}
