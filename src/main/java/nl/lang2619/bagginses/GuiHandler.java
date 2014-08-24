package nl.lang2619.bagginses;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import nl.lang2619.bagginses.items.bags.foid.ContainerVoid;
import nl.lang2619.bagginses.items.bags.foid.GuiVoid;
import nl.lang2619.bagginses.items.bags.tier1.ContainerTier1;
import nl.lang2619.bagginses.items.bags.tier1.GuiTier1;
import nl.lang2619.bagginses.items.bags.tier2.ContainerTier2;
import nl.lang2619.bagginses.items.bags.tier2.GuiTier2;
import nl.lang2619.bagginses.proxy.GuiInfo;

/**
 * Created by Tim on 8/24/2014.
 */
public class GuiHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        if (ID == GuiInfo.GUI_BACKPACK) {
            return new ContainerTier1(player.inventory, player.getCurrentEquippedItem());
        }
        if (ID == GuiInfo.GUI_BACKPACK_VOID) {
            return new ContainerVoid(player.inventory, player.getCurrentEquippedItem());
        }
        if (ID == GuiInfo.GUI_BACKPACK_T2) {
            return new ContainerTier2(player.inventory, player.getCurrentEquippedItem());
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == GuiInfo.GUI_BACKPACK) {
            return new GuiTier1(player.inventory, player.getCurrentEquippedItem());
        }
        if (ID == GuiInfo.GUI_BACKPACK_VOID) {
            return new GuiVoid(player.inventory, player.getCurrentEquippedItem());
        }
        if (ID == GuiInfo.GUI_BACKPACK_T2) {
            return new GuiTier2(player.inventory, player.getCurrentEquippedItem());
        }
        return null;
    }
}
