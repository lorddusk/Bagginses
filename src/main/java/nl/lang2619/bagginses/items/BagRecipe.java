package nl.lang2619.bagginses.items;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import nl.lang2619.bagginses.items.bags.tier2.BagTier2;

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
            if (inventoryCrafting.getStackInSlot(slot) != null && inventoryCrafting.getStackInSlot(slot).getItem() instanceof BagTier2) {
                bag = inventoryCrafting.getStackInSlot(slot);
            }
        }
        if (bag != null) {
            bag = bag.copy();
            //bag.setItemDamage(this.getRecipeOutput().getItemDamage());
            return bag;
        }
        return this.getRecipeOutput();
    }
}
