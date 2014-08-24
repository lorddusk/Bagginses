package nl.lang2619.bagginses.items.bags.tier2;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import nl.lang2619.bagginses.references.TextureInfo;
import org.lwjgl.opengl.GL11;

/**
 * Created by Tim on 8/24/2014.
 */
public class GuiTier2 extends GuiContainer {
    public GuiTier2(InventoryPlayer inventory, ItemStack currentEquippedItem) {
        super(new ContainerTier2(inventory,currentEquippedItem));
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f,int i, int j){

        GL11.glColor4f(1, 1, 1, 1);
        mc.renderEngine.bindTexture(new ResourceLocation(TextureInfo.GUI_BACKPACK_T2));
        int var5 = (width-xSize)/2;
        int var6 = (height-ySize)/2;
        drawTexturedModalRect(var5,var6,0,0,xSize,ySize);
    }

    @Override
    public void drawGuiContainerForegroundLayer(int i, int j)
    {
        String s = "Bagginses' Bag Tier 2";
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
    }
}
