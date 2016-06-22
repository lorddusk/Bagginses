package nl.lang2619.bagginses.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import nl.lang2619.bagginses.items.ModItems;
import nl.lang2619.bagginses.items.bags.Bag;
import nl.lang2619.bagginses.references.BagMode;

/**
 * Stolen with love from Modular Utilities
 * https://github.com/BrassGoggledCoders/ModularUtilities
 */
public class ItemDropEvent {

    @SubscribeEvent
    public void onLivingDrops(LivingDropsEvent event) {
        if(event.getSource().getDamageType() == "player") {
            EntityPlayer player = (EntityPlayer) event.getSource().getEntity();

            if(player.inventory.offHandInventory[0].getItem() instanceof Bag) {
                Bag bag = (Bag) player.inventory.offHandInventory[0].getItem();
                if (bag.getMode() == BagMode.PICKUP) {
                    if (doItemsMatch(player.inventory.offHandInventory[0], ModItems.ender)) {
                        for(int i = 0; i < event.getDrops().size(); i++) {
                            if(player.getInventoryEnderChest().addItem(event.getDrops().get(i).getEntityItem()) == null)
                                event.getDrops().remove(0);
                        }
                    } else {
                        //TODO
                    /*for(int i = 0; i < event.getDrops().size(); i++) {
                        Bag bag = (Bag) player.inventory.offHandInventory[0].getItem();
                    }*/
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onBlockDrops(BlockEvent.HarvestDropsEvent event) {
        if(event.getHarvester() != null) {
            EntityPlayer player = event.getHarvester();
            if(player.inventory.offHandInventory[0].getItem() instanceof Bag) {
                Bag bag = (Bag) player.inventory.offHandInventory[0].getItem();
                if (bag.getMode() == BagMode.PICKUP) {
                    if (doItemsMatch(player.inventory.offHandInventory[0], ModItems.ender)) {
                        for(int i = 0; i < event.getDrops().size(); i++) {
                            if(player.getInventoryEnderChest().addItem(event.getDrops().get(i)) == null)
                                event.getDrops().remove(0);
                        }
                    } else {
                        //TODO
                    /*for(int i = 0; i < event.getDrops().size(); i++) {
                        Bag bag = (Bag) player.inventory.offHandInventory[0].getItem();

                    }*/
                    }
                }
            }
        }
    }

    public static boolean doItemsMatch(ItemStack itemStack, Item item)
    {
        return isItemNonNull(itemStack) && itemStack.getItem() == item;
    }

    public static boolean isItemNonNull(ItemStack itemStack)
    {
        return itemStack != null && itemStack.getItem() != null;
    }
}
