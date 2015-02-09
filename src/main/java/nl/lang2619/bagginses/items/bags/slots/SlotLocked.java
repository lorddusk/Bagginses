package nl.lang2619.bagginses.items.bags.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import nl.lang2619.bagginses.references.BlockList;

/**
 * Created by Tim on 8-2-2015.
 */
public class SlotLocked extends Slot {
    protected boolean isPhantom;
    protected boolean isInfinite;
    protected boolean canAdjustPhantom = true;
    protected boolean canShift = false;
    private String color;

    public SlotLocked(IInventory inventory, int slotIndex, int x, int y, String color) {
        super(inventory, slotIndex, x, y);
        this.color = color;
        if (inventory == null)
            throw new IllegalArgumentException("Inventory must not be null");
    }

    @Override
    public boolean isItemValid(ItemStack itemStack) {
        itemStack = itemStack.copy();
        itemStack.stackSize = 1;
        if (BlockList.contains(itemStack.getItem(),itemStack.getItemDamage(),color)) {
            return true;
        }
        return false;
    }

    public SlotLocked setInfinite() {
        this.isInfinite = true;
        return this;
    }

    public SlotLocked setPhantom() {
        isPhantom = true;
        return this;
    }

    public SlotLocked blockShift() {
        canShift = false;
        return this;
    }

    @Override
    public void putStack(ItemStack itemStack) {
        if (!isPhantom() || canAdjustPhantom())
            super.putStack(itemStack);
    }

    public SlotLocked setCanAdjustPhantom(boolean canAdjust) {
        this.canAdjustPhantom = canAdjust;
        return this;
    }

    public SlotLocked setCanShift(boolean canShift) {
        this.canShift = canShift;
        return this;
    }

    public boolean canShift() {
        return canShift;
    }

    public boolean isPhantom() {
        return this.isPhantom;
    }

    public boolean canAdjustPhantom() {
        return canAdjustPhantom;
    }
}
