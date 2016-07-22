package nl.lang2619.bagginses.event;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import nl.lang2619.bagginses.inventory.InventoryBag;
import nl.lang2619.bagginses.items.bags.Bag;
import nl.lang2619.bagginses.items.bags.container.BagContainer;
import nl.lang2619.bagginses.references.BagMode;
import nl.lang2619.bagginses.references.BagTypes;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Stolen with love from Modular Utilities
 * https://github.com/BrassGoggledCoders/ModularUtilities
 */
public class ItemDropEvent {

    //TODO add bags in hotbar too

    @SubscribeEvent
    public void onLivingDrops(LivingDropsEvent event) {
        if(event.getSource().getDamageType() == "player") {
            EntityPlayer player = (EntityPlayer) event.getSource().getEntity();
            ItemStack bagStack = getBag(player);
            if (bagStack != null) {
                Bag bag = (Bag) bagStack.getItem();
                //Only want pickup
                if (bag.getMode() != BagMode.PICKUP)
                    return;
                //Not void for now
                if (bag.getType() == BagTypes.VOID)
                    return;
                Iterator<EntityItem> drops = event.getDrops().iterator();
                ArrayList<EntityItem> toRemove = new ArrayList<EntityItem>();
                if (bag.getType() == BagTypes.ENDER) {
                    while(drops.hasNext()) {
                        EntityItem stack = drops.next();
                        if(player.getInventoryEnderChest().addItem(stack.getEntityItem()) == null) {
                            toRemove.add(stack);
                        }
                    }
                } else {
                    BagTypes type = bag.getType();
                    InventoryBag inv = new InventoryBag(bagStack);
                    BagContainer container = new BagContainer(player, inv);
                    while(drops.hasNext()) {
                        EntityItem stack = drops.next();
                        for (int i = 0; i < type.getColumns() * type.getRows(); i++) {
                            if (container.getSlot(i).isItemValid(stack.getEntityItem())) {
                                if (inv.getStackInSlot(i) == null) {
                                    inv.setInventorySlotContents(i, stack.getEntityItem());
                                    toRemove.add(stack);
                                    break;
                                } else if (doStacksMatch(inv.getStackInSlot(i), stack.getEntityItem())) {
                                    ItemStack invStack = inv.getStackInSlot(i);
                                    Slot slot = container.getSlot(i);
                                    int resultingStackSize = stack.getEntityItem().stackSize + invStack.stackSize;
                                    int max = Math.min(stack.getEntityItem().getMaxStackSize(), slot.getSlotStackLimit());
                                    if (resultingStackSize <= max) {
                                        invStack.stackSize = resultingStackSize;
                                        toRemove.add(stack);
                                        break;
                                    } else if (invStack.stackSize < max) {
                                        stack.getEntityItem().stackSize -= (max - invStack.stackSize);
                                        invStack.stackSize = max;
                                    }
                                }
                            }
                        }
                    }
                    inv.save();
                }
                event.getDrops().removeAll(toRemove);
            }
        }
    }

    @SubscribeEvent
    public void onBlockDrops(BlockEvent.HarvestDropsEvent event) {
        if(event.getHarvester() != null) {
            ItemStack bagStack = getBag(event.getHarvester());
            if (bagStack != null) {
                Bag bag = (Bag) bagStack.getItem();
                //Only want pickup
                if (bag.getMode() != BagMode.PICKUP)
                    return;
                //Not void for now
                if (bag.getType() == BagTypes.VOID)
                    return;
                Iterator<ItemStack> drops = event.getDrops().iterator();
                ArrayList<ItemStack> toRemove = new ArrayList<ItemStack>();
                if (bag.getType() == BagTypes.ENDER) {
                    while(drops.hasNext()) {
                        ItemStack stack = drops.next();
                        if(event.getHarvester().getInventoryEnderChest().addItem(stack) == null) {
                            toRemove.add(stack);
                        }
                    }
                } else {
                    BagTypes type = bag.getType();
                    InventoryBag inv = new InventoryBag(bagStack);
                    BagContainer container = new BagContainer(event.getHarvester(), inv);
                    while(drops.hasNext()) {
                        ItemStack stack = drops.next();
                        for (int i = 0; i < type.getColumns() * type.getRows(); i++) {
                            if (container.getSlot(i).isItemValid(stack)) {
                                if (inv.getStackInSlot(i) == null) {
                                    inv.setInventorySlotContents(i, stack);
                                    toRemove.add(stack);
                                    break;
                                } else if (doStacksMatch(inv.getStackInSlot(i), stack)) {
                                    ItemStack invStack = inv.getStackInSlot(i);
                                    Slot slot = container.getSlot(i);
                                    int resultingStackSize = stack.stackSize + invStack.stackSize;
                                    int max = Math.min(stack.getMaxStackSize(), slot.getSlotStackLimit());
                                    if (resultingStackSize <= max) {
                                        invStack.stackSize = resultingStackSize;
                                        toRemove.add(stack);
                                        break;
                                    } else if (invStack.stackSize < max) {
                                        stack.stackSize -= (max - invStack.stackSize);
                                        invStack.stackSize = max;
                                    }
                                }
                            }
                        }
                    }
                    inv.save();
                }
                event.getDrops().removeAll(toRemove);
            }
        }
    }

    public static boolean doStacksMatch(ItemStack stack1, ItemStack stack2) {
        return isItemNonNull(stack1) && isItemNonNull(stack2) && stack1.getItem() == stack2.getItem();
    }

    public static boolean isItemNonNull(ItemStack itemStack) {
        return itemStack != null && itemStack.getItem() != null;
    }

    public static ItemStack getBag(EntityPlayer player) {
        if (isItemNonNull(player.getHeldItemOffhand())
                && player.getHeldItemOffhand().getItem() instanceof Bag)
            return player.getHeldItemOffhand();

        for (int i = 0; i < 9; i++) {
            if (isItemNonNull(player.inventory.getStackInSlot(i))
                    && player.inventory.getStackInSlot(i).getItem() instanceof Bag
                    && ((Bag) player.inventory.getStackInSlot(i).getItem()).getMode() == BagMode.PICKUP)
                return player.inventory.getStackInSlot(i);
        }

        return null;
    }
}
