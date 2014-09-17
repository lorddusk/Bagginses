package nl.lang2619.bagginses.items;

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
 * Created by Tim on 9/5/2014.
 */
public class Upgrade extends Item {
    public Upgrade() {
        super();
        maxStackSize = 1;
        setCreativeTab(Bagginses.BagTab);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        this.itemIcon = register.registerIcon("bagginses:" + "upgrade");
    }

    public String getUnlocalizedName(ItemStack itemStack) {
        return "upgrade";
    }
}
