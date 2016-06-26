package nl.lang2619.bagginses.event;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import nl.lang2619.bagginses.items.ModItems;
import nl.lang2619.bagginses.items.bags.Bag;
import nl.lang2619.bagginses.references.BagMode;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Stolen with love from Modular Utilities
 * https://github.com/BrassGoggledCoders/ModularUtilities
 */
public class ItemDropEvent {

    @SubscribeEvent
    public void onLivingDrops(LivingDropsEvent event) {
        if(event.getSource().getDamageType() == "player") {
            EntityPlayer player = (EntityPlayer) event.getSource().getEntity();
            if(doItemsMatch(player.getHeldItemOffhand(), ModItems.ender)) {
                Bag bag = (Bag) player.getHeldItemOffhand().getItem();
                if (bag.getMode() != BagMode.PICKUP)
                    return;
                Iterator<EntityItem> drops = event.getDrops().iterator();
                ArrayList<EntityItem> toRemove = new ArrayList<EntityItem>();
                while(drops.hasNext()) {
                    EntityItem current = drops.next();
                    if(player.getInventoryEnderChest().addItem(current.getEntityItem()) == null)
                        toRemove.add(current);
                }
                event.getDrops().removeAll(toRemove);
            }
        }
    }

    @SubscribeEvent
    public void onBlockDrops(BlockEvent.HarvestDropsEvent event) {
        if(event.getHarvester() != null) {
            EntityPlayer player = event.getHarvester();
            if(doItemsMatch(player.getHeldItemOffhand(), ModItems.ender)) {
                Bag bag = (Bag) player.getHeldItemOffhand().getItem();
                if (bag.getMode() != BagMode.PICKUP)
                    return;
                Iterator<ItemStack> drops = event.getDrops().iterator();
                ArrayList<ItemStack> toRemove = new ArrayList<ItemStack>();
                while(drops.hasNext()) {
                    ItemStack current = drops.next();
                    if(player.getInventoryEnderChest().addItem(current) == null) {
                        toRemove.add(current);
                    }
                }
                event.getDrops().removeAll(toRemove);
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
