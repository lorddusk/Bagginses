package nl.lang2619.bagginses.event;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import nl.lang2619.bagginses.Bagginses;
import nl.lang2619.bagginses.items.bags.Bag;
import nl.lang2619.bagginses.references.Achievements;
import nl.lang2619.bagginses.references.BagTypes;

/**
 * Created by alex on 22/05/16.
 */
public class AchievementEventHandler {

    @SubscribeEvent
    public void event(PlayerEvent.ItemCraftedEvent e) {
        if (e.crafting.getItem() instanceof Bag) {
            e.player.addStat(Achievements.firstBag);
            Bag bag = (Bag) e.crafting.getItem();
            Bagginses.analytics.eventDesign("BagCrafted:" + bag.getType().name().toLowerCase() + ":" + bag.getColor());
            if (((Bag) e.crafting.getItem()).getType() == BagTypes.VOID) {
                e.player.addStat(Achievements.voidBag);
            }
            else if(((Bag) e.crafting.getItem()).getType() == BagTypes.ENDER) {
                e.player.addStat(Achievements.enderBag);
            }
            else if (((Bag) e.crafting.getItem()).getType() == BagTypes.TIER2) {
                e.player.addStat(Achievements.tier2Bag);
            }
        }
    }
}
