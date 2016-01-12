package nl.lang2619.bagginses.event;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraft.item.ItemStack;
import nl.lang2619.bagginses.items.bags.Bags;
import nl.lang2619.bagginses.references.BlockList;

/**
 * Created by Tim on 11/27/2015.
 */
public class PickUpEventHandler {

    @SubscribeEvent
    public void pickUp(PlayerEvent.ItemPickupEvent event) {
        //System.out.println(event.pickedUp.getEntityItem().getDisplayName());
    }
}
