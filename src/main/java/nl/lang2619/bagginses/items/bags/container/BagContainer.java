package nl.lang2619.bagginses.items.bags.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import nl.lang2619.bagginses.inventory.InventoryItemMain;
import nl.lang2619.bagginses.inventory.InventoryItemVoid;
import nl.lang2619.bagginses.items.bags.BagTier1;
import nl.lang2619.bagginses.items.bags.BagTier2;
import nl.lang2619.bagginses.items.bags.BagVoid;
import nl.lang2619.bagginses.items.bags.slots.SlotLocked;
import nl.lang2619.bagginses.items.bags.slots.SlotNoPickup;
import nl.lang2619.bagginses.references.StackUtils;

/**
 * Created by Tim on 8-2-2015.
 */
public class BagContainer extends Container {
    private IInventory bag;

    int lines = 0;
    int columns = 0;
    int startX = 0;
    int startY = 0;
    boolean foid = false;
    String color;

    public BagContainer(InventoryPlayer playerInventory, ItemStack itemStack) {
        if (itemStack.getItem() instanceof BagTier1) {
            lines = 3;
            columns = 5;
            startX = 44;
            startY = 19;
            color = ((BagTier1) itemStack.getItem()).getColor();
            bag = new InventoryItemMain(itemStack, 15, 64);
        } else if (itemStack.getItem() instanceof BagTier2) {
            lines = 3;
            columns = 9;
            startX = 8;
            startY = 19;
            color = ((BagTier2) itemStack.getItem()).getColor();
            bag = new InventoryItemMain(itemStack, 27, 64);
        } else if (itemStack.getItem() instanceof BagVoid) {
            lines = 1;
            columns = 1;
            startX = 80;
            startY = 37;
            bag = new InventoryItemVoid(itemStack, 1, 64);
            foid = true;
        }

        //Inventory
        for (int row = 0; row < lines; ++row) {
            for (int column = 0; column < columns; ++column) {
                int slotIndex = column + (row * columns);
                int x = startX + (column * 18);
                int y = startY + (row * 18);
                if (!foid) {
                    addSlotToContainer(new SlotLocked(bag, slotIndex, x, y,color).blockShift());
                } else {
                    addSlotToContainer(new Slot(bag, slotIndex, x, y));
                }
            }
        }

        //PlayerInventory
        for (int row = 0; row < 3; ++row) {
            for (int column = 0; column < 9; ++column) {
                addSlotToContainer(new Slot(playerInventory, column + (row * 9) + 9, 8 + (column * 18), 84 + (row * 18)));
            }
        }

        //Hotbar
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
    public ItemStack slotClick(int slotIndex, int button, int modifier, EntityPlayer player) {
        if (player == null)
            return null;
        return super.slotClick(slotIndex, button, modifier, player);
    }

    @Override
    public void onContainerClosed(EntityPlayer player) {
        bag.closeInventory();
        if (foid) {
            ((InventoryItemVoid) bag).setNBT(player.getCurrentEquippedItem());
        } else {
            ((InventoryItemMain) bag).setNBT(player.getCurrentEquippedItem());
        }
    }

    protected boolean shiftItemStack(ItemStack stackToShift, int start, int end) {
        boolean changed = false;
        if (stackToShift.isStackable())
            for (int slotIndex = start; stackToShift.stackSize > 0 && slotIndex < end; slotIndex++) {
                Slot slot = (Slot) inventorySlots.get(slotIndex);
                ItemStack stackInSlot = slot.getStack();
                if (stackInSlot != null && StackUtils.isIdenticalItem(stackInSlot, stackToShift)) {
                    int resultingStackSize = stackInSlot.stackSize + stackToShift.stackSize;
                    int max = Math.min(stackToShift.getMaxStackSize(), slot.getSlotStackLimit());
                    if (resultingStackSize <= max) {
                        stackToShift.stackSize = 0;
                        stackInSlot.stackSize = resultingStackSize;
                        slot.onSlotChanged();
                        changed = true;
                    } else if (stackInSlot.stackSize < max) {
                        stackToShift.stackSize -= max - stackInSlot.stackSize;
                        stackInSlot.stackSize = max;
                        slot.onSlotChanged();
                        changed = true;
                    }
                }
            }
        if (stackToShift.stackSize > 0)
            for (int slotIndex = start; stackToShift.stackSize > 0 && slotIndex < end; slotIndex++) {
                Slot slot = (Slot) inventorySlots.get(slotIndex);
                ItemStack stackInSlot = slot.getStack();
                if (stackInSlot == null) {
                    int max = Math.min(stackToShift.getMaxStackSize(), slot.getSlotStackLimit());
                    stackInSlot = stackToShift.copy();
                    stackInSlot.stackSize = Math.min(stackToShift.stackSize, max);
                    stackToShift.stackSize -= stackInSlot.stackSize;
                    slot.putStack(stackInSlot);
                    slot.onSlotChanged();
                    changed = true;
                }
            }
        return changed;
    }

    private boolean tryShiftItem(ItemStack stackToShift, int numSlots) {
        for (int machineIndex = 0; machineIndex < numSlots - 9 * 4; machineIndex++) {
            Slot slot = (Slot) inventorySlots.get(machineIndex);
            if (slot instanceof SlotLocked) {
                SlotLocked slotLocked = (SlotLocked) slot;
                if (!slotLocked.canShift())
                    continue;
                if (slotLocked.isPhantom())
                    continue;
            }
            if (!slot.isItemValid(stackToShift))
                continue;
            if (shiftItemStack(stackToShift, machineIndex, machineIndex + 1))
                return true;
        }
        return false;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer p, int i) {
        if (p == null)
            return null;

        if (!foid) {
            return transferTierBag(p, i);
        }
        if (foid) {
            return transferVoidBag(p, i);
        }
        return null;
    }

    private ItemStack transferVoidBag(EntityPlayer player, int slotIndex) {
        ItemStack originalStack = null;
        Slot slot = (Slot) inventorySlots.get(slotIndex);
        int numSlots = inventorySlots.size();
        if ((slot != null) && slot.getHasStack()) {
            ItemStack stackInSlot = slot.getStack();
            originalStack = stackInSlot.copy();
            if (slotIndex >= numSlots - 9 * 4 && tryShiftItem(stackInSlot, numSlots)) {
                // NOOP
            } else if (slotIndex >= numSlots - 9 * 4 && slotIndex < numSlots - 9) {
                if (!shiftItemStack(stackInSlot, numSlots - 9, numSlots))
                    return null;
            } else if (slotIndex >= numSlots - 9 && slotIndex < numSlots) {
                if (!shiftItemStack(stackInSlot, numSlots - 9 * 4, numSlots - 9))
                    return null;
            } else if (!shiftItemStack(stackInSlot, numSlots - 9 * 4, numSlots))
                return null;
            slot.onSlotChange(stackInSlot, originalStack);
            if (stackInSlot.stackSize <= 0)
                slot.putStack(null);
            else
                slot.onSlotChanged();
            if (stackInSlot.stackSize == originalStack.stackSize)
                return null;
            slot.onPickupFromSlot(player, stackInSlot);
        }
        return originalStack;
    }

    private ItemStack transferTierBag(EntityPlayer player, int slotIndex) {
        ItemStack originalStack = null;
        Slot slot = (Slot) inventorySlots.get(slotIndex);
        int numSlots = inventorySlots.size();
        if ((slot != null) && slot.getHasStack()) {
            ItemStack stackInSlot = slot.getStack();
            originalStack = stackInSlot.copy();
            if (slotIndex >= numSlots - 9 * 4 && tryShiftItem(stackInSlot, numSlots)) {
                // NOOP
            } else if (slotIndex >= numSlots - 9 * 4 && slotIndex < numSlots - 9) {
                if (!shiftItemStack(stackInSlot, numSlots - 9, numSlots))
                    return null;
            } else if (slotIndex >= numSlots - 9 && slotIndex < numSlots) {
                if (!shiftItemStack(stackInSlot, numSlots - 9 * 4, numSlots - 9))
                    return null;
            } else if (!shiftItemStack(stackInSlot, numSlots - 9 * 4, numSlots))
                return null;
            slot.onSlotChange(stackInSlot, originalStack);
            if (stackInSlot.stackSize <= 0)
                slot.putStack(null);
            else
                slot.onSlotChanged();
            if (stackInSlot.stackSize == originalStack.stackSize)
                return null;
            slot.onPickupFromSlot(player, stackInSlot);
        }
        return originalStack;
    }
}
