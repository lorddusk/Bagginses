package nl.lang2619.bagginses.items.recipes;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import nl.lang2619.bagginses.items.Bag;

/**
 * Created by Alex on 28/01/2017.
 */
public class SoulboundRecipe extends ShapedOreRecipe {
    /*
    a skeleton head in each upper corner, a diamond in the upper middle, a bag in the middle middle, a diamond on each middle side, an eye of ender in each lower corner, and a ghast tear in the lower middle(edited)
     */

    public SoulboundRecipe(ItemStack bag) {
        super(bag, "sds", "dbd", "ege",
                's', new ItemStack(Items.SKULL, 1, OreDictionary.WILDCARD_VALUE),
                'd', "gemDiamond",
                'b', bag,
                'e', Items.ENDER_EYE,
                'g', Items.GHAST_TEAR
                );
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        ItemStack bag = null;
        for (int slot = 0; slot < inv.getSizeInventory(); slot++) {
            if (inv.getStackInSlot(slot) != null && inv.getStackInSlot(slot).getItem() instanceof Bag) {
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
