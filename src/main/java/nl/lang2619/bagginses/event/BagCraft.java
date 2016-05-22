package nl.lang2619.bagginses.event;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import nl.lang2619.bagginses.Bagginses;
import nl.lang2619.bagginses.items.bags.Bags;

/**
 * Created by alex on 22/05/16.
 */
public class BagCraft {

    @SubscribeEvent
    public void event(PlayerEvent.ItemCraftedEvent e) {
        if (e.crafting.getItem() instanceof Bags) {
            System.out.println("MADE A BAG");
            e.player.addStat(Bagginses.achievementFirstBag);
        }
    }
}
