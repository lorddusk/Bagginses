package nl.lang2619.bagginses.references;

import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import nl.lang2619.bagginses.items.ModItems;

/**
 * Created by alex on 22/05/16.
 */
public class Achievements {

    public static Achievement firstBag;
    public static Achievement voidBag;
    public static Achievement enderBag;
    public static Achievement tier2Bag;
    public static Achievement tier3Bag;

    public static void init() {
        firstBag = new Achievement("achievement.firstbag", "firstbag", 0, 0, ModItems.foid, null);
        firstBag.registerStat();

        voidBag = new Achievement("achievement.voidbag", "voidbag", 2, -1, ModItems.foid, firstBag);
        voidBag.registerStat();

        enderBag = new Achievement("achievement.enderbag", "enderbag", -2, -1, ModItems.ender, firstBag);
        enderBag.registerStat();

        tier2Bag = new Achievement("achievement.tier2bag", "tier2bag", 0, -2, ModItems.foid, firstBag);
        tier2Bag.registerStat();

        tier3Bag = new Achievement("achievement.tier3bag", "tier3bag", 2, -4, ModItems.foid, tier2Bag);

        AchievementPage.registerAchievementPage(
                new AchievementPage("Bagginses",
                        new Achievement[] {
                                firstBag,
                        voidBag,
                        enderBag,
                        tier2Bag,
                        tier3Bag}));
    }
}
