package nl.lang2619.bagginses.items.bags.foid;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import nl.lang2619.bagginses.inventory.ContainerMain;
import nl.lang2619.bagginses.inventory.InventoryItemVoid;
import nl.lang2619.bagginses.inventory.SlotNoPickup;
import nl.lang2619.bagginses.references.ContainerUtil;

/**
 * Created by Tim on 8/24/2014.
 */
public class ContainerVoid extends ContainerMain {
    private IInventory bag;
    public static boolean instantAdd = false;


    public ContainerVoid(InventoryPlayer playerInventory, ItemStack itemStack) {
        bag = new InventoryItemVoid(itemStack, 1, 64);

        ContainerUtil.addSlots(this, bag, 0, 1, 1, 80, 37);
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
        ((InventoryItemVoid) bag).setNBT(player.getCurrentEquippedItem());
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer p, int i) {
        ItemStack itemstack = null;
        Slot slot = (Slot) inventorySlots.get(i);

        if ((slot != null) && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (i == 0) {
                if (!mergeItemStack(itemstack1, 1, inventorySlots.size(), true)) {
                    return null;
                }

            } else {
                InventoryItemVoid.instantAdd = true;
                if (!mergeItemStack(itemstack1, 0, 1, false)) {
                    InventoryItemVoid.instantAdd = false;
                    return null;
                }
                InventoryItemVoid.instantAdd = false;
            }
            if (itemstack1.stackSize == 0) {
                slot.putStack((ItemStack) null);

            } else {
                slot.onSlotChanged();
            }
        }
        return itemstack;
    }
}
