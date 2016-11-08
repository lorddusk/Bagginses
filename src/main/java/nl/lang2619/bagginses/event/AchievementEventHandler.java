package nl.lang2619.bagginses.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
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
            giveAchievement(e.player, Achievements.firstBag);
            Bag bag = (Bag) e.crafting.getItem();
            Bagginses.analytics.eventDesign("BagCrafted:" + bag.getType().name().toLowerCase() + bag.getColor() + ":" + (isSoulBound(e.crafting) ? "soulbound" : "notSoulbound"), Bagginses.analytics.userPrefix());
            if (((Bag) e.crafting.getItem()).getType() == BagTypes.VOID) {
                giveAchievement(e.player, Achievements.voidBag);
            }
            else if(((Bag) e.crafting.getItem()).getType() == BagTypes.ENDER) {
                giveAchievement(e.player, Achievements.enderBag);
            }
            else if (((Bag) e.crafting.getItem()).getType() == BagTypes.TIER2) {
                giveAchievement(e.player, Achievements.tier2Bag);
            }
            else if (((Bag) e.crafting.getItem()).getType() == BagTypes.TIER3) {
                giveAchievement(e.player, Achievements.tier3Bag);
            }
        }
    }

    private void giveAchievement(EntityPlayer player, Achievement achievement) {
        if (player.hasAchievement(achievement))
            return;

        player.addStat(achievement);
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
