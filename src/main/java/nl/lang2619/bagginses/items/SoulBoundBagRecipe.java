package nl.lang2619.bagginses.items;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;
import nl.lang2619.bagginses.items.bags.Bags;

/**
 * Created by alex on 23/05/16.
 */
public class SoulBoundBagRecipe extends ShapedRecipes {
    /*
    a skeleton head in each upper corner, a diamond in the upper middle, a bag in the middle middle, a diamond on each middle side, an eye of ender in each lower corner, and a ghast tear in the lower middle(edited)
     */

    public SoulBoundBagRecipe(ItemStack bag) {
        super(3, 3, new ItemStack[]{
                new ItemStack(Items.SKULL, 1, OreDictionary.WILDCARD_VALUE),
                new ItemStack(Items.DIAMOND),
                new ItemStack(Items.SKULL, 1, OreDictionary.WILDCARD_VALUE),

                new ItemStack(Items.DIAMOND),
                bag,
                new ItemStack(Items.DIAMOND),

                new ItemStack(Items.ENDER_EYE),
                new ItemStack(Items.GHAST_TEAR),
                new ItemStack(Items.ENDER_EYE)
        }, bag);
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        ItemStack bag = null;
        for (int slot = 0; slot < inv.getSizeInventory(); slot++) {
            if (inv.getStackInSlot(slot) != null && inv.getStackInSlot(slot).getItem() instanceof Bags) {
                bag = inv.getStackInSlot(slot);
            }
        }
        if (bag != null) {
            if(bag.hasTagCompound()
                    && bag.getTagCompound() != null) {
                if (!bag.getTagCompound().hasKey("soulbound")) {
                    ItemStack output = getRecipeOutput().copy();
                    NBTBase tag = bag.getTagCompound().copy();
                    output.setTagCompound((NBTTagCompound) tag);
                    output.getTagCompound().setBoolean("soulbound", true);
                    return output;
                } else {
                    return null;
                }
            } else {
                ItemStack output = getRecipeOutput().copy();
                output.setTagCompound(new NBTTagCompound());
                output.getTagCompound().setBoolean("soulbound", true);
                return output;
            }
        }
        return this.getRecipeOutput().copy();
    }

    public ItemStack getRecipeOutput() {
        ItemStack stack = super.getRecipeOutput().copy();
        stack.setTagCompound(new NBTTagCompound());
        stack.getTagCompound().setBoolean("soulbound", true);
        return stack;
    }
}
