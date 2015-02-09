package nl.lang2619.bagginses.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import nl.lang2619.bagginses.items.bags.BagVoid;
import nl.lang2619.bagginses.items.bags.BagTier1;
import nl.lang2619.bagginses.items.bags.BagTier2;

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
    private static Item foid;
    private static Item upgrade;
    public static Item[] tier1 = new Item[]{black, red, green, brown, blue, purple, cyan, silver, gray, pink, lime, yellow, lightBlue, magenta, orange, white};
    public static String[] BagColors = new String[]{"black", "red", "green", "brown", "blue", "purple", "cyan", "silver", "gray", "pink", "lime", "yellow", "lightBlue", "magenta", "orange", "white"};
    public static Item[] tier2 = new Item[]{blackT2, redT2, greenT2, brownT2, blueT2, purpleT2, cyanT2, silverT2, grayT2, pinkT2, limeT2, yellowT2, lightBlueT2, magentaT2, orangeT2, whiteT2};

    public static void init() {

        upgrade = new Upgrade();
        GameRegistry.registerItem(upgrade, "upgrade");
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.upgrade),"SSS","III","WWW",'S', Items.string,'I',Items.iron_ingot,'W',Blocks.planks);

        for (int i = 0; i < tier1.length; i++) {
            tier1[i] = new BagTier1(BagColors[i]);
            GameRegistry.registerItem(tier1[i], BagColors[i]);
            GameRegistry.addShapedRecipe(new ItemStack(ModItems.tier1[i]), "sws", "wcw", "sws", 's', Items.string, 'w', new ItemStack(Blocks.wool, 1, tier1.length - i - 1), 'c', Blocks.chest);
        }

        for (int i = 0; i < tier2.length; i++) {
            tier2[i] = new BagTier2(BagColors[i] + "T2");
            GameRegistry.registerItem(tier2[i], BagColors[i] + "T2");
            GameRegistry.addRecipe(new BagRecipe(new ItemStack(tier2[i]), new ItemStack(ModItems.tier1[i]), ModItems.upgrade));

        }

        foid = new BagVoid(ItemInfo.foid);
        GameRegistry.registerItem(foid, ItemInfo.foid);
        GameRegistry.addRecipe(new ItemStack(ModItems.foid), "sws", "wcw", "sws", 's', Items.string, 'w', Blocks.wool, 'c', Items.ender_pearl);

    }
}
