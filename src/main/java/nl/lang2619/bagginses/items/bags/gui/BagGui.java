package nl.lang2619.bagginses.items.bags.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import nl.lang2619.bagginses.items.bags.container.BagContainer;
import nl.lang2619.bagginses.references.TextureInfo;
import org.lwjgl.opengl.GL11;

/**
 * Created by Tim on 8-2-2015.
 */
public class BagGui extends GuiContainer {
    private String bagType;

    public BagGui(InventoryPlayer inventory, ItemStack currentEquippedItem, String type) {
        super(new BagContainer(inventory, currentEquippedItem));
        this.bagType = type;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        if (bagType.equals("tier2")) {
            mc.renderEngine.bindTexture(new ResourceLocation(TextureInfo.GUI_BACKPACK_T2));
        }
        if (bagType.equals("tier")) {
            mc.renderEngine.bindTexture(new ResourceLocation(TextureInfo.GUI_BACKPACK));
        }
        if (bagType.equals("void")) {
            mc.renderEngine.bindTexture(new ResourceLocation(TextureInfo.GUI_BACKPACK_VOID));
        }
        GL11.glColor4f(1, 1, 1, 1);
        int var5 = (width - xSize) / 2;
        int var6 = (height - ySize) / 2;
        drawTexturedModalRect(var5, var6, 0, 0, xSize, ySize);
    }

    @Override
    public void drawGuiContainerForegroundLayer(int i, int j) {
        String s = "";
        if (bagType.equals("tier2")) {
            s = "Bagginses' Bag Tier 2";
        }
        if (bagType.equals("tier")) {
            s = "Bagginses' Bag Tier 1";
        }
        if (bagType.equals("void")) {
            s = "Bagginses' Void Bag";
        }
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
    }
}
