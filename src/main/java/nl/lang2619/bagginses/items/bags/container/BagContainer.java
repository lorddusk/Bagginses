package nl.lang2619.bagginses.items.bags.container;

import net.minecraft.inventory.ClickType;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import nl.lang2619.bagginses.config.ModConfig;
import nl.lang2619.bagginses.helpers.NBTHelper;
import nl.lang2619.bagginses.helpers.Names;
import nl.lang2619.bagginses.inventory.InventoryBag;
import nl.lang2619.bagginses.items.bags.Bag;
import nl.lang2619.bagginses.references.BagTypes;
import nl.lang2619.bagginses.references.BlockList;
import nl.lang2619.bagginses.references.StackUtils;

import java.util.UUID;

/**
 * Created by Tim on 8-2-2015.
 */
public class BagContainer extends ContainerBagginses {
    int bagStartX = 0;
    int bagStartY = 0;

    private final EntityPlayer entityPlayer;
    public final InventoryBag inventoryBag;
    private int blockedSlot;

    private int bagInventoryRows;
    private int bagInventoryColumns;

    boolean foid = false;
    String color;

    public BagContainer(EntityPlayer entityPlayer, InventoryBag inventoryBag) {

        this.entityPlayer = entityPlayer;
        this.inventoryBag = inventoryBag;

        Bag item = (Bag) inventoryBag.parentItemStack.getItem();

        BagTypes type = item.getType();
        color = item.getColor();
        foid = type == BagTypes.VOID;

        bagInventoryRows = type.getRows();
        bagInventoryColumns = type.getColumns();
        bagStartX = type.getBagStartX();
        bagStartY = type.getBagStartY();

        int i = 0;

        //Inventory
        for (int bagRowIndex = 0; bagRowIndex < bagInventoryRows; ++bagRowIndex) {
            for (int bagColumnIndex = 0; bagColumnIndex < bagInventoryColumns; ++bagColumnIndex) {
                this.addSlotToContainer(new SlotBag(this, inventoryBag, entityPlayer, bagColumnIndex + bagRowIndex * bagInventoryColumns, bagStartX + bagColumnIndex * 18, bagStartY + bagRowIndex * 18));
                i++;
            }
        }

        int added = 0;
        if (foid)
            added = 36;

        //PlayerInventory
        for (int inventoryRowIndex = 0; inventoryRowIndex < PLAYER_INVENTORY_ROWS; ++inventoryRowIndex) {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < PLAYER_INVENTORY_COLUMNS; ++inventoryColumnIndex) {
                this.addSlotToContainer(new Slot(entityPlayer.inventory, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 8 + inventoryColumnIndex * 18, 30 + (18 * bagInventoryRows) + inventoryRowIndex * 18 + added));
            }
        }

        //Hotbar
        for (int actionBarSlotIndex = 0; actionBarSlotIndex < PLAYER_INVENTORY_COLUMNS; ++actionBarSlotIndex) {
            this.addSlotToContainer(new Slot(entityPlayer.inventory, actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 30 + (18 * bagInventoryRows) + 58 + added));
        }
        blockedSlot = entityPlayer.inventory.currentItem + 27 + i;
    }

    @Override
    public void onContainerClosed(EntityPlayer player) {
        super.onContainerClosed(player);
        if (!player.worldObj.isRemote) {
            InventoryPlayer invPlayer = player.inventory;
            for (ItemStack itemStack : invPlayer.mainInventory) {
                if (itemStack != null) {
                    if (NBTHelper.hasTag(itemStack, Names.NBT.BAG_OPEN)) {
                        NBTHelper.removeTag(itemStack, Names.NBT.BAG_OPEN);
                    }
                }
            }
            saveInventory(player);
        }
    }


    public boolean isItemStackParent(ItemStack itemStack) {
        if (NBTHelper.hasUUID(itemStack)) {
            UUID stackUUID = new UUID(itemStack.getTagCompound().getLong(Names.NBT.UUID_MOST_SIG), itemStack.getTagCompound().getLong(Names.NBT.UUID_LEAST_SIG));
            return inventoryBag.matchesUUID(stackUUID);
        }
        return false;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer p, int slotIndex) {
        if (!foid) {
            ItemStack newItemStack = null;
            Slot slot = (Slot) inventorySlots.get(slotIndex);

            if (slot != null && slot.getHasStack()) {
                ItemStack itemStack = slot.getStack();
                newItemStack = itemStack.copy();

                if (slotIndex < bagInventoryRows * bagInventoryColumns) {
                    if (!this.mergeItemStack(itemStack, bagInventoryRows * bagInventoryColumns, inventorySlots.size(), false)) {
                        return null;
                    }
                } else if (itemStack.getItem() instanceof Bag) {
                    if (slotIndex < (bagInventoryRows * bagInventoryColumns) + (PLAYER_INVENTORY_ROWS * PLAYER_INVENTORY_COLUMNS)) {
                        if (!this.mergeItemStack(itemStack, (bagInventoryRows * bagInventoryColumns) + (PLAYER_INVENTORY_ROWS * PLAYER_INVENTORY_COLUMNS), inventorySlots.size(), false)) {
                            return null;
                        }
                    } else if (!this.mergeItemStack(itemStack, bagInventoryRows * bagInventoryColumns, (bagInventoryRows * bagInventoryColumns) + (PLAYER_INVENTORY_ROWS * PLAYER_INVENTORY_COLUMNS), false)) {
                        return null;
                    }
                } else if (!this.mergeItemStack(itemStack, 0, bagInventoryRows * bagInventoryColumns, false)) {
                    return null;
                }
                if (itemStack.stackSize == 0) {
                    slot.putStack(null);
                } else {
                    slot.onSlotChanged();
                }
            }
            return newItemStack;
        } else {
            return transferVoid(p, slotIndex);
        }
    }

    private ItemStack transferVoid(EntityPlayer player, int slotIndex) {
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

    private boolean tryShiftItem(ItemStack stackToShift, int numSlots) {
        for (int machineIndex = 0; machineIndex < numSlots - 9 * 4; machineIndex++) {
            Slot slot = (Slot) inventorySlots.get(machineIndex);
            if (!slot.isItemValid(stackToShift))
                continue;
            if (shiftItemStack(stackToShift, machineIndex, machineIndex + 1))
                return true;
        }
        return false;
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

    public void saveInventory(EntityPlayer player) {
        inventoryBag.onGuiSaved(player);
    }

    public int getNumColumns() {
        return bagInventoryRows;
    }

    @Override
    public ItemStack slotClick(int slotID, int clickedButton, ClickType clickType, EntityPlayer player) {
        if (slotID == this.blockedSlot)
            return null;
        return super.slotClick(slotID, clickedButton, clickType, player);
    }

    private class SlotBag extends Slot {
        private final EntityPlayer entityPlayer;
        private BagContainer containerBag;

        public SlotBag(BagContainer containerBag, IInventory inventory, EntityPlayer entityPlayer, int slotIndex, int x, int y) {
            super(inventory, slotIndex, x, y);
            this.entityPlayer = entityPlayer;
            this.containerBag = containerBag;
        }

        @Override
        public void onSlotChanged() {
            super.onSlotChanged();

            if (!foid) {
                if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
                    containerBag.saveInventory(entityPlayer);
                }
            }
        }

        /**
         * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
         */
        @Override
        public boolean isItemValid(ItemStack itemStack) {
            if (itemStack == null) {
                return true;
            } else {
                itemStack = itemStack.copy();
                itemStack.stackSize = 1;
                if (!(itemStack.getItem() instanceof Bag)) {
                    if (foid) {
                        return true;
                    }
                    if (ModConfig.whitelist) {
                        //Whitelist
                        if (BlockList.contains(itemStack.getItem(), itemStack.getItemDamage(), color)) {
                            return true;
                        }else{
                            return false;
                        }
                    }else{
                        //Blacklist
                        if (BlockList.contains(itemStack.getItem(), itemStack.getItemDamage(), color)) {
                            return false;
                        }else{
                            return true;
                        }
                    }
                }
                return false;
            }
        }
    }
}
