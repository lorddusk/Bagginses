package nl.lang2619.bagginses.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import nl.lang2619.bagginses.config.ModConfig;
import nl.lang2619.bagginses.items.bags.BagVoid;
import nl.lang2619.bagginses.items.bags.BagTier1;
import nl.lang2619.bagginses.items.bags.BagTier2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tim on 8/24/2014.
 */
public class ModItems {
    private static Item black, blackT2;
    private static Item red, redT2;
    private static Item green, greenT2;
    private static Item brown, brownT2;
    private static Item blue, blueT2;
    private static Item purple, purpleT2;
    private static Item cyan, cyanT2;
    private static Item silver, silverT2;
    private static Item gray, grayT2;
    private static Item pink, pinkT2;
    private static Item lime, limeT2;
    private static Item yellow, yellowT2;
    private static Item lightBlue, lightBlueT2;
    private static Item magenta, magentaT2;
    private static Item orange, orangeT2;
    private static Item white, whiteT2;
    public static Item foid;
    private static Item upgrade;
    public static Item[] tier1 = new Item[16];
    public static String[] BagColors = new String[16];
    public static Item[] tier2 = new Item[16];
    static int added = 0;

    public static void init() {
        fillTiers();

        if(added > 0) {
            upgrade = new Upgrade();
            GameRegistry.registerItem(upgrade, "upgrade");
            GameRegistry.addShapedRecipe(new ItemStack(ModItems.upgrade), "SSS", "III", "WWW", 'S', Items.string, 'I', Items.iron_ingot, 'W', Blocks.planks);


            for (int i = 0; i < added; i++) {
                tier1[i] = new BagTier1(BagColors[i]);
                GameRegistry.registerItem(tier1[i], BagColors[i]);
                GameRegistry.addShapedRecipe(new ItemStack(ModItems.tier1[i]), "sws", "wcw", "sws", 's', Items.string, 'w', new ItemStack(Blocks.wool, 1, added - i - 1), 'c', Blocks.chest);
            }

            for (int i = 0; i < added; i++) {
                tier2[i] = new BagTier2(BagColors[i] + "T2");
                GameRegistry.registerItem(tier2[i], BagColors[i] + "T2");
                GameRegistry.addRecipe(new BagRecipe(new ItemStack(tier2[i]), new ItemStack(ModItems.tier1[i]), ModItems.upgrade));
            }
        }

        foid = new BagVoid(ItemInfo.foid);
        GameRegistry.registerItem(foid, ItemInfo.foid);
        GameRegistry.addRecipe(new ItemStack(ModItems.foid), "sws", "wcw", "sws", 's', Items.string, 'w', Blocks.wool, 'c', Items.ender_pearl);

    }

    static void fillTiers() {
        if (!ModConfig.black.isEmpty()) {
            tier1[added] = black;
            tier2[added] = blackT2;
            BagColors[added] = "black";
            added++;
        }
        if (!ModConfig.red.isEmpty()) {
            tier1[added] = red;
            tier2[added] = redT2;
            BagColors[added] = "red";
            added++;
        }
        if (!ModConfig.brown.isEmpty()) {
            tier1[added] = brown;
            tier2[added] = brownT2;
            BagColors[added] = "brown";
            added++;
        }
        if (!ModConfig.blue.isEmpty()) {
            tier1[added] = blue;
            tier2[added] = blueT2;
            BagColors[added] = "blue";
            added++;
        }
        if (!ModConfig.cyan.isEmpty()) {
            tier1[added] = cyan;
            tier2[added] = cyanT2;
            BagColors[added] = "cyan";
            added++;
        }
        if (!ModConfig.gray.isEmpty()) {
            tier1[added] = gray;
            tier2[added] = grayT2;
            BagColors[added] = "gray";
            added++;
        }
        if (!ModConfig.green.isEmpty()) {
            tier1[added] = green;
            tier2[added] = greenT2;
            BagColors[added] = "green";
            added++;
        }
        if (!ModConfig.lightBlue.isEmpty()) {
            tier1[added] = lightBlue;
            tier2[added] = lightBlueT2;
            BagColors[added] = "lightBlue";
            added++;
        }
        if (!ModConfig.lime.isEmpty()) {
            tier1[added] = lime;
            tier2[added] = limeT2;
            BagColors[added] = "lime";
            added++;
        }
        if (!ModConfig.magenta.isEmpty()) {
            tier1[added] = magenta;
            tier2[added] = magentaT2;
            BagColors[added] = "magenta";
            added++;
        }
        if (!ModConfig.orange.isEmpty()) {
            tier1[added] = orange;
            tier2[added] = orangeT2;
            BagColors[added] = "orange";
            added++;
        }
        if (!ModConfig.pink.isEmpty()) {
            tier1[added] = pink;
            tier2[added] = pinkT2;
            BagColors[added] = "pink";
            added++;
        }
        if (!ModConfig.purple.isEmpty()) {
            tier1[added] = purple;
            tier2[added] = purpleT2;
            BagColors[added] = "purple";
            added++;
        }
        if (!ModConfig.silver.isEmpty()) {
            tier1[added] = silver;
            tier2[added] = silverT2;
            BagColors[added] = "silver";
            added++;
        }
        if (!ModConfig.white.isEmpty()) {
            tier1[added] = white;
            tier2[added] = whiteT2;
            BagColors[added] = "white";
            added++;
        }
        if (!ModConfig.yellow.isEmpty()) {
            tier1[added] = yellow;
            tier2[added] = yellowT2;
            BagColors[added] = "yellow";
            added++;
        }
    }
}
