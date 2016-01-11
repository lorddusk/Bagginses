package nl.lang2619.bagginses.items.bags;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import nl.lang2619.bagginses.Bagginses;
import nl.lang2619.bagginses.helpers.ItemHelper;
import nl.lang2619.bagginses.helpers.NBTHelper;
import nl.lang2619.bagginses.helpers.Names;
import nl.lang2619.bagginses.proxy.GuiInfo;
import nl.lang2619.bagginses.references.BagTypes;

import java.util.List;

/**
 * Created by Tim on 4/12/2015.
 */
public class Bags extends Item {
    String color;
    BagTypes type;

    public Bags(String color, BagTypes type) {
        super();
        maxStackSize = 1;
        setCreativeTab(Bagginses.BagTab);
        this.color = color;
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public BagTypes getType() {
        return type;
    }

    public String getUnlocalizedName(ItemStack itemStack) {
        return "backpack_" + color;
    }

    @Override
    public boolean getShareTag() {
        return true;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            if (!ItemHelper.hasOwnerUUID(itemStack)) {
                ItemHelper.setOwner(itemStack, player);
            }
            NBTHelper.setUUID(itemStack);
            NBTHelper.setBoolean(itemStack, Names.NBT.BAG_OPEN, true);

            if (type == BagTypes.TIER1)
                player.openGui(Bagginses.instance, GuiInfo.GUI_BACKPACK, world, 0, 0, 0);
            if (type == BagTypes.TIER2)
                player.openGui(Bagginses.instance, GuiInfo.GUI_BACKPACK_T2, world, 0, 0, 0);
            if (type == BagTypes.VOID)
                player.openGui(Bagginses.instance, GuiInfo.GUI_BACKPACK_VOID, world, 0, 0, 0);
        }
        return itemStack;
    }

    @Override
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List list, boolean p_77624_4_) {
        super.addInformation(p_77624_1_, p_77624_2_, list, p_77624_4_);
        if (type == BagTypes.TIER1)
            list.add("Tier 1");
        if (type == BagTypes.TIER2)
            list.add("Tier 2");
        if (type == BagTypes.VOID)
            list.add("Void Bag");
    }
}
