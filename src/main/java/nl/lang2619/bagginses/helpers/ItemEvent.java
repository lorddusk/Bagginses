package nl.lang2619.bagginses.helpers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import nl.lang2619.bagginses.items.bags.container.BagContainer;

/**
 * Created by Tim on 4/13/2015.
 */
public class ItemEvent {
    @SubscribeEvent
    public void onItemTossEvent(ItemTossEvent itemTossEvent)
    {
        NBTHelper.clearStatefulNBTTags(itemTossEvent.entityItem.getEntityItem());

        //Close the Alchemical Bag GUI when the Alchemical bag is tossed
        if (itemTossEvent.player.openContainer instanceof BagContainer)
        {
            if (((BagContainer) itemTossEvent.player.openContainer).isItemStackParent(itemTossEvent.entityItem.getEntityItem()))
            {
                //We have to remove the itemstack we are throwing from the inventory now to prevent a loop (will also happen after this event has been fired)
                itemTossEvent.player.inventory.setItemStack(null);
                itemTossEvent.player.closeScreen();
            }
        }
    }
}
