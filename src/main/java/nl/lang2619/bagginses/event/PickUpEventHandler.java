package nl.lang2619.bagginses.event;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraft.item.ItemStack;
import nl.lang2619.bagginses.helpers.Names;
import nl.lang2619.bagginses.items.bags.Bags;
import nl.lang2619.bagginses.references.BagTypes;
import nl.lang2619.bagginses.references.BlockList;

/**
 * Created by Tim on 11/27/2015.
 */
public class PickUpEventHandler {
//
//    @SubscribeEvent
//    public void pickUp(PlayerEvent.ItemPickupEvent event) {
//        ItemStack[] mainInventory = event.player.inventory.mainInventory;
//        System.out.println("Unlocalized : " + event.pickedUp.getEntityItem().getUnlocalizedName() + ". Display : " + event.pickedUp.getEntityItem().getDisplayName() + ".");
//
//        for (int i = 0; i < mainInventory.length; i++) {
//            ItemStack is = mainInventory[i];
//            if (is != null && is != event.pickedUp.getEntityItem()) {
//                if (!(event.pickedUp.getEntityItem().getItem() instanceof Bags)) {
//                    if (is.getItem() instanceof Bags) {
//                        if (((Bags) is.getItem()).getType() != BagTypes.VOID) {
//                            allowedInBag(event.pickedUp, is);
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    private void allowedInBag(EntityItem pickedUp, ItemStack bag) {
//        boolean allowed;
//        Bags bags = ((Bags) bag.getItem());
//        String color = bags.getColor();
//        allowed = BlockList.getList(color).containsKey(pickedUp.getEntityItem().getItem());
//        if (allowed) {
//            spaceInBag(pickedUp, bag, bags.getType());
//        }
//    }
//
//    private void spaceInBag(EntityItem pickedUp, ItemStack bag, BagTypes type) {
//
//    }

}
