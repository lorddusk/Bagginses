package nl.lang2619.bagginses;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import nl.lang2619.bagginses.inventory.InventoryBag;
import nl.lang2619.bagginses.items.bags.Bag;
import nl.lang2619.bagginses.items.bags.container.BagContainer;
import nl.lang2619.bagginses.items.bags.gui.BagGui;
import nl.lang2619.bagginses.proxy.GuiInfo;

/**
 * Created by Tim on 8/24/2014.
 */
public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        if (ID == GuiInfo.GUI_BACKPACK) {
            return new BagContainer(player, new InventoryBag(getStack(player)));
        }
        if (ID == GuiInfo.GUI_BACKPACK_VOID) {
            return new BagContainer(player, new InventoryBag(getStack(player)));
        }
        if (ID == GuiInfo.GUI_BACKPACK_T2) {
            return new BagContainer(player, new InventoryBag(getStack(player)));
        }
        if (ID == GuiInfo.GUI_BACKPACK_T3) {
            return new BagContainer(player, new InventoryBag(getStack(player)));
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == GuiInfo.GUI_BACKPACK) {
            return new BagGui(player, new InventoryBag(getStack(player)));
        }
        if (ID == GuiInfo.GUI_BACKPACK_VOID) {
            return new BagGui(player, new InventoryBag(getStack(player)));
        }
        if (ID == GuiInfo.GUI_BACKPACK_T2) {
            return new BagGui(player, new InventoryBag(getStack(player)));
        }
        if (ID == GuiInfo.GUI_BACKPACK_T3) {
            return new BagGui(player, new InventoryBag(getStack(player)));
        }
        return null;
    }

    static ItemStack getStack(EntityPlayer p) {
        if (p.getHeldItemMainhand() != null
                && p.getHeldItemMainhand().getItem() instanceof Bag) {
            return p.getHeldItemMainhand();
        } else {
            return p.getHeldItemOffhand();
        }
    }
}
