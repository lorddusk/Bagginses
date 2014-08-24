package nl.lang2619.bagginses.items.bags.tier1;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import nl.lang2619.bagginses.inventory.ContainerMain;
import nl.lang2619.bagginses.inventory.InventoryItemMain;
import nl.lang2619.bagginses.inventory.SlotNoPickup;
import nl.lang2619.bagginses.references.ContainerUtil;

/**
 * Created by Tim on 8/24/2014.
 */
public class ContainerTier1 extends ContainerMain {
    private IInventory bag;

    public ContainerTier1(InventoryPlayer playerInventory, ItemStack itemStack) {
        bag = new InventoryItemMain(itemStack,15, 64);
        ContainerUtil.addSlots(this, bag, 0, 3, 5, 44, 19);
        ContainerUtil.addSlots(this, playerInventory, 9, 3, 9, 8, 84);

        for (int row = 0; row < 9; ++row) {
            if (row == playerInventory.currentItem) {
                addSlotToContainer(new SlotNoPickup(playerInventory, row, 8 + (row * 18), 142));
            } else {
                addSlotToContainer(new Slot(playerInventory, row, 8 + (row * 18), 142));
            }
        }
        bag.openInventory();
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return !player.isDead;
    }

    @Override

    public void onContainerClosed(EntityPlayer player) {
        bag.closeInventory();
        ((InventoryItemMain) bag).setNBT(player.getCurrentEquippedItem());
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer p, int i) {
        ItemStack itemstack = null;
        Slot slot = (Slot) inventorySlots.get(i);
        if ((slot != null) && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (i < bag.getSizeInventory()) {
                if (!mergeItemStack(itemstack1, bag.getSizeInventory(), inventorySlots.size(), true)) return null;
            } else {
                if (!mergeItemStack(itemstack1, 0, bag.getSizeInventory(), false)) return null;
            }
            if (itemstack1.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }
        }
        return itemstack;
    }
}