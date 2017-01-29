package nl.lang2619.bagginses.items.recipes;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import nl.lang2619.bagginses.items.Bag;

import javax.annotation.Nullable;

/**
 * Created by Alex on 28/01/2017.
 */
public class BagRecipe extends ShapelessOreRecipe {

    public BagRecipe(ItemStack output, Object... inputList) {
        super(output, inputList);
    }

    @Nullable
    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        //Find the bag
        ItemStack stack = null;
        for (int i = 0; i < inv.getSizeInventory(); i++) {
            if (inv.getStackInSlot(i) != null
                    && inv.getStackInSlot(i).getItem() instanceof Bag)
                stack = inv.getStackInSlot(i);
        }

        //Copy data across
        ItemStack output = this.getRecipeOutput().copy();

        if (stack == null)
            return output;

        if(stack.hasTagCompound()){
            output = getRecipeOutput().copy();
            output.setTagCompound(stack.getTagCompound().copy());
        }

        return output;
    }


}
