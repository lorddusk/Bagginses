package nl.lang2619.bagginses.items.bags;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import nl.lang2619.bagginses.Bagginses;
import nl.lang2619.bagginses.proxy.GuiInfo;

/**
 * Created by Tim on 8/24/2014.
 */
public class BagVoid extends Item {
    String color;

    public BagVoid(String color) {
        super();
        maxStackSize = 1;
        setCreativeTab(Bagginses.BagTab);
        this.color = color;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        this.itemIcon = register.registerIcon("bagginses:" + "backpack_" + color);
    }

    public String getUnlocalizedName(ItemStack itemStack) {
        return "backpack_" + color;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {

            player.openGui(Bagginses.instance, GuiInfo.GUI_BACKPACK_VOID, world, 0, 0, 0);

        }
        return stack;
    }
}
