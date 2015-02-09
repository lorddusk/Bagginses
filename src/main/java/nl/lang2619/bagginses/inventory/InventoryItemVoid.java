package nl.lang2619.bagginses.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

/**
 * Created by Tim on 8/24/2014.
 */
public class InventoryItemVoid implements IInventory {

    public ItemStack[] itemSlots = new ItemStack[64];
    public NBTTagCompound tag;
    public String name;
    public ItemStack item;
    public static boolean instantAdd = false;
    private boolean added = false;
    public int stackSize;

    public InventoryItemVoid(ItemStack item, int size, int stacksize) {
        itemSlots = new ItemStack[size];
        name = item.getUnlocalizedName();
        if (item.hasTagCompound() == false) {
            item.setTagCompound(new NBTTagCompound());
        }
        tag = item.getTagCompound();
        this.item = item;
        stackSize = stacksize;
    }

    @Override
    public void closeInventory() {
        writeToNBT(tag);
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        if (slot == 64) {
            return null;
        } else if (itemSlots[slot] != null) {
            ItemStack itemstack;
            if (itemSlots[slot].stackSize <= amount) {
                itemstack = itemSlots[slot];
                itemSlots[slot] = null;
                markDirty();
                return itemstack;
            } else {
                itemstack = itemSlots[slot].splitStack(amount);
                if (itemSlots[slot].stackSize == 0) {
                    itemSlots[slot] = null;
                }
                markDirty();
                return itemstack;
            }
        } else return null;
    }

    @Override
    public int getInventoryStackLimit() {
        return stackSize;
    }

    @Override
    public void markDirty() {
        writeToNBT(tag);
        setNBT(item);
    }

    @Override
    public String getInventoryName() {
        return "container_" + name;
    }

    @Override
    public int getSizeInventory() {
        return 65;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return slot == 64 ? null : itemSlots[slot];
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        return getStackInSlot(slot);
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemstack) {
        return slot <= 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory() {
        readFromNBT(tag);
    }

    public void readFromNBT(NBTTagCompound NBTTagCompound) {
        itemSlots = new ItemStack[getSizeInventory()];
        NBTTagList inventory = NBTTagCompound.getTagList("Items", 10);

        for (int i = 0; i < inventory.tagCount(); ++i) {
            NBTTagCompound Slots = inventory.getCompoundTagAt(i);
            byte slot = Slots.getByte("Slot");
            if ((slot >= 0) && (slot < itemSlots.length)) {
                itemSlots[slot] = ItemStack.loadItemStackFromNBT(Slots);
            }
        }
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        if (slot != 64) {
            itemSlots[slot] = stack;
            this.markDirty();
            if (instantAdd) {
                this.processInv();
            } else {
                added = true;
            }
        }
    }

    public void setNBT(ItemStack item) {
        item.setTagCompound(tag);
    }

    public void writeToNBT(NBTTagCompound NBTTagCompound) {
        NBTTagList inventory = new NBTTagList();
        for (int slot = 0; slot < itemSlots.length; ++slot) {
            if (itemSlots[slot] != null) {
                NBTTagCompound Slots = new NBTTagCompound();
                Slots.setByte("Slot", (byte) slot);
                itemSlots[slot].writeToNBT(Slots);
                inventory.appendTag(Slots);
            }
        }
        NBTTagCompound.setTag("Items", inventory);
        this.processInv();
    }

    public void processInv() {
        if (itemSlots[0] != null) {
            itemSlots[0] = null;
        }
        for (int i = 1; i < 64; ++i) {
            itemSlots[i] = null;
        }
    }
}

