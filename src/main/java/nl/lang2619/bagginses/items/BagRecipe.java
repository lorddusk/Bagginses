package nl.lang2619.bagginses.items;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import nl.lang2619.bagginses.items.bags.Bag;

/**
 * Created by Tim on 8/24/2014.
 */
public class BagRecipe extends ShapelessOreRecipe {
    public BagRecipe(ItemStack result, Object... recipe) {
        super(result, recipe);
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting) {
        ItemStack bag = null;
        for (int slot = 0; slot < inventoryCrafting.getSizeInventory(); slot++) {
            if (inventoryCrafting.getStackInSlot(slot) != null && inventoryCrafting.getStackInSlot(slot).getItem() instanceof Bag) {
                bag = inventoryCrafting.getStackInSlot(slot);
            }
        }
        if (bag != null) {
            if(bag.hasTagCompound()){
                ItemStack output = getRecipeOutput().copy();
                output.setTagCompound(bag.getTagCompound().copy());
                return output;
            }
        }
        return this.getRecipeOutput().copy();
    }
}
