package nl.lang2619.bagginses.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

/**
 * Created by Tim on 8/24/2014.
 */
public class InventoryItemMain implements IInventory {

    public ItemStack[] inventoryContents;
    public NBTTagCompound tag;
    public String name;
    public ItemStack item;
    public int stackSize;

    public InventoryItemMain(ItemStack item, int size, int stacksize) {
        inventoryContents = new ItemStack[size];
        name = item.getUnlocalizedName();
        if(item.hasTagCompound() == false){
            item.setTagCompound(new NBTTagCompound());
        }
        tag = item.getTagCompound();
        this.item = item;
        stackSize = stacksize;
    }

    @Override
    public void closeInventory(){
        writeToNBT(tag);
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount)
    {
        if(inventoryContents[slot] != null)
        {
            ItemStack itemstack;
            if(inventoryContents[slot].stackSize <= amount)
            {
                itemstack = inventoryContents[slot];
                inventoryContents[slot] = null;
                markDirty();
                return itemstack;
            }
            else
            {
                itemstack = inventoryContents[slot].splitStack(amount);
                if(inventoryContents[slot].stackSize == 0)
                {
                    inventoryContents[slot] = null;
                }
                markDirty();
                return itemstack;
            }
        }
        else return null;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return stackSize;
    }

    @Override
    public void markDirty() {
        writeToNBT(tag);
        setNBT(item);
    }

    @Override
    public String getInventoryName()
    {
        return "container_" + name;
    }

    @Override
    public int getSizeInventory()
    {
        return inventoryContents.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot)
    {
        return inventoryContents[slot];
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot)
    {
        if(inventoryContents[slot] != null)
        {
            ItemStack itemstack = inventoryContents[slot];
            inventoryContents[slot] = null;
            return itemstack;
        }
        else return null;
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        return false;
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemstack)
    {
        return true;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return true;
    }

    @Override
    public void openInventory()
    {
        readFromNBT(tag);
    }

    public void readFromNBT(NBTTagCompound NBTTagCompound)
    {
        inventoryContents = new ItemStack[getSizeInventory()];
        NBTTagList inventory = NBTTagCompound.getTagList("Items",10);

        for(int i = 0; i < inventory.tagCount(); ++i)
        {
            NBTTagCompound Slots = inventory.getCompoundTagAt(i);
            byte slot = Slots.getByte("Slot");
            if((slot >= 0) && (slot < inventoryContents.length))
            {
                inventoryContents[slot] = ItemStack.loadItemStackFromNBT(Slots);
            }
        }
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack)
    {
        inventoryContents[slot] = stack;
        if((stack != null) && (stack.stackSize > getInventoryStackLimit()))
        {
            stack.stackSize = getInventoryStackLimit();
        }
        markDirty();
    }

    public void setNBT(ItemStack item)
    {
        item.setTagCompound(tag);
    }

    public void writeToNBT(NBTTagCompound NBTTagCompound)
    {
        NBTTagList inventory = new NBTTagList();
        for(int slot = 0; slot < inventoryContents.length; ++slot)
        {
            if(inventoryContents[slot] != null)
            {
                NBTTagCompound Slots = new NBTTagCompound();
                Slots.setByte("Slot", (byte)slot);
                inventoryContents[slot].writeToNBT(Slots);
                inventory.appendTag(Slots);
            }
        }
        NBTTagCompound.setTag("Items", inventory);
    }
}
