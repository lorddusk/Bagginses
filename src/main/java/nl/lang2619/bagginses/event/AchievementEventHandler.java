package nl.lang2619.bagginses.event;

import net.minecraft.item.ItemStack;
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
            Bagginses.analytics.eventDesign("BagCrafted:" + bag.getType().name().toLowerCase() + bag.getColor() + ":" + (isSoulBound(e.crafting) ? "soulbound" : "notSoulbound"), Bagginses.analytics.userPrefix());
            if (((Bag) e.crafting.getItem()).getType() == BagTypes.VOID) {
                e.player.addStat(Achievements.voidBag);
            }
            else if(((Bag) e.crafting.getItem()).getType() == BagTypes.ENDER) {
                e.player.addStat(Achievements.enderBag);
            }
            else if (((Bag) e.crafting.getItem()).getType() == BagTypes.TIER2) {
                e.player.addStat(Achievements.tier2Bag);
            }
            else if (((Bag) e.crafting.getItem()).getType() == BagTypes.TIER3) {
                e.player.addStat(Achievements.tier3Bag);
            }
        }
    }

    private boolean isSoulBound(ItemStack stack) {
        try {
            if (stack != null
                    && stack.getTagCompound() != null
                    && stack.getTagCompound().hasKey("soulbound")
                    && stack.getTagCompound().getBoolean("soulbound")) {
                return true;
            }
        } catch (Exception e){

        }
        return false;
    }
}
