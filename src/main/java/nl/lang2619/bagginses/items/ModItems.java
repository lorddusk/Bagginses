package nl.lang2619.bagginses.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import nl.lang2619.bagginses.items.bags.foid.BagVoid;
import nl.lang2619.bagginses.items.bags.tier1.BagTier1;
import nl.lang2619.bagginses.items.bags.tier2.BagTier2;

/**
 * Created by Tim on 8/24/2014.
 */
public class ModItems {
    public static Item black, blackT2;
    public static Item red, redT2;
    public static Item green, greenT2;
    public static Item brown, brownT2;
    public static Item blue, blueT2;
    public static Item purple, purpleT2;
    public static Item cyan, cyanT2;
    public static Item silver, silverT2;
    public static Item gray, grayT2;
    public static Item pink, pinkT2;
    public static Item lime, limeT2;
    public static Item yellow, yellowT2;
    public static Item lightBlue, lightBlueT2;
    public static Item magenta, magentaT2;
    public static Item orange, orangeT2;
    public static Item white, whiteT2;
    public static Item foid;

    public static void init() {
        blackT2 = new BagTier2(ItemInfo.black + "T2");
        black = new BagTier1(ItemInfo.black);
        GameRegistry.registerItem(black, ItemInfo.black);
        GameRegistry.registerItem(blackT2, ItemInfo.black + "T2");
        GameRegistry.addRecipe(new ItemStack(ModItems.black), "sws", "wcw", "sws", 's', Items.string, 'w', new ItemStack(Blocks.wool, 1, 15), 'c', Blocks.chest);
        GameRegistry.addRecipe(new BagRecipe(new ItemStack(ModItems.blackT2),new ItemStack(ModItems.black),new ItemStack(Items.iron_ingot)));

        redT2 = new BagTier2(ItemInfo.red + "T2");
        red = new BagTier1(ItemInfo.red);
        GameRegistry.registerItem(red, ItemInfo.red);
        GameRegistry.registerItem(redT2, ItemInfo.red + "T2");
        GameRegistry.addRecipe(new ItemStack(ModItems.red), "sws", "wcw", "sws", 's', Items.string, 'w', new ItemStack(Blocks.wool, 1, 14), 'c', Blocks.chest);
        GameRegistry.addRecipe(new BagRecipe(new ItemStack(ModItems.redT2),new ItemStack(ModItems.red),new ItemStack(Items.iron_ingot)));

        greenT2 = new BagTier2(ItemInfo.green + "T2");
        green = new BagTier1(ItemInfo.green);
        GameRegistry.registerItem(green, ItemInfo.green);
        GameRegistry.registerItem(greenT2, ItemInfo.green + "T2");
        GameRegistry.addRecipe(new ItemStack(ModItems.green), "sws", "wcw", "sws", 's', Items.string, 'w', new ItemStack(Blocks.wool, 1, 13), 'c', Blocks.chest);
        GameRegistry.addRecipe(new BagRecipe(new ItemStack(ModItems.greenT2),new ItemStack(ModItems.green),new ItemStack(Items.iron_ingot)));

        brownT2 = new BagTier2(ItemInfo.brown + "T2");
        brown = new BagTier1(ItemInfo.brown);
        GameRegistry.registerItem(brown, ItemInfo.brown);
        GameRegistry.registerItem(brownT2, ItemInfo.brown + "T2");
        GameRegistry.addRecipe(new ItemStack(ModItems.brown), "sws", "wcw", "sws", 's', Items.string, 'w', new ItemStack(Blocks.wool, 1, 12), 'c', Blocks.chest);
        GameRegistry.addRecipe(new BagRecipe(new ItemStack(ModItems.brownT2),new ItemStack(ModItems.brown),new ItemStack(Items.iron_ingot)));

        blueT2 = new BagTier2(ItemInfo.blue + "T2");
        blue = new BagTier1(ItemInfo.blue);
        GameRegistry.registerItem(blue, ItemInfo.blue);
        GameRegistry.registerItem(blueT2, ItemInfo.blue + "T2");
        GameRegistry.addRecipe(new ItemStack(ModItems.blue), "sws", "wcw", "sws", 's', Items.string, 'w', new ItemStack(Blocks.wool, 1, 11), 'c', Blocks.chest);
        GameRegistry.addRecipe(new BagRecipe(new ItemStack(ModItems.blueT2),new ItemStack(ModItems.blue),new ItemStack(Items.iron_ingot)));

        purpleT2 = new BagTier2(ItemInfo.purple + "T2");
        purple = new BagTier1(ItemInfo.purple);
        GameRegistry.registerItem(purple, ItemInfo.purple);
        GameRegistry.registerItem(purpleT2, ItemInfo.purple + "T2");
        GameRegistry.addRecipe(new ItemStack(ModItems.purple), "sws", "wcw", "sws", 's', Items.string, 'w', new ItemStack(Blocks.wool, 1, 10), 'c', Blocks.chest);
        GameRegistry.addRecipe(new BagRecipe(new ItemStack(ModItems.purpleT2),new ItemStack(ModItems.purple),new ItemStack(Items.iron_ingot)));

        cyanT2 = new BagTier2(ItemInfo.cyan + "T2");
        cyan = new BagTier1(ItemInfo.cyan);
        GameRegistry.registerItem(cyan, ItemInfo.cyan);
        GameRegistry.registerItem(cyanT2, ItemInfo.cyan + "T2");
        GameRegistry.addRecipe(new ItemStack(ModItems.cyan), "sws", "wcw", "sws", 's', Items.string, 'w', new ItemStack(Blocks.wool, 1, 9), 'c', Blocks.chest);
        GameRegistry.addRecipe(new BagRecipe(new ItemStack(ModItems.cyanT2),new ItemStack(ModItems.cyan),new ItemStack(Items.iron_ingot)));

        silverT2 = new BagTier2(ItemInfo.silver + "T2");
        silver = new BagTier1(ItemInfo.silver);
        GameRegistry.registerItem(silver, ItemInfo.silver);
        GameRegistry.registerItem(silverT2, ItemInfo.silver + "T2");
        GameRegistry.addRecipe(new ItemStack(ModItems.silver), "sws", "wcw", "sws", 's', Items.string, 'w', new ItemStack(Blocks.wool, 1, 8), 'c', Blocks.chest);
        GameRegistry.addRecipe(new BagRecipe(new ItemStack(ModItems.silverT2),new ItemStack(ModItems.silver),new ItemStack(Items.iron_ingot)));

        grayT2 = new BagTier2(ItemInfo.gray + "T2");
        gray = new BagTier1(ItemInfo.gray);
        GameRegistry.registerItem(gray, ItemInfo.gray);
        GameRegistry.registerItem(grayT2, ItemInfo.gray + "T2");
        GameRegistry.addRecipe(new ItemStack(ModItems.gray), "sws", "wcw", "sws", 's', Items.string, 'w', new ItemStack(Blocks.wool, 1, 7), 'c', Blocks.chest);
        GameRegistry.addRecipe(new BagRecipe(new ItemStack(ModItems.grayT2),new ItemStack(ModItems.gray),new ItemStack(Items.iron_ingot)));

        pinkT2 = new BagTier2(ItemInfo.pink + "T2");
        pink = new BagTier1(ItemInfo.pink);
        GameRegistry.registerItem(pink, ItemInfo.pink);
        GameRegistry.registerItem(pinkT2, ItemInfo.pink + "T2");
        GameRegistry.addRecipe(new ItemStack(ModItems.pink), "sws", "wcw", "sws", 's', Items.string, 'w', new ItemStack(Blocks.wool, 1, 6), 'c', Blocks.chest);
        GameRegistry.addRecipe(new BagRecipe(new ItemStack(ModItems.pinkT2),new ItemStack(ModItems.pink),new ItemStack(Items.iron_ingot)));

        limeT2 = new BagTier2(ItemInfo.lime + "T2");
        lime = new BagTier1(ItemInfo.lime);
        GameRegistry.registerItem(lime, ItemInfo.lime);
        GameRegistry.registerItem(limeT2, ItemInfo.lime + "T2");
        GameRegistry.addRecipe(new ItemStack(ModItems.lime), "sws", "wcw", "sws", 's', Items.string, 'w', new ItemStack(Blocks.wool, 1, 5), 'c', Blocks.chest);
        GameRegistry.addRecipe(new BagRecipe(new ItemStack(ModItems.limeT2),new ItemStack(ModItems.lime),new ItemStack(Items.iron_ingot)));

        yellowT2 = new BagTier2(ItemInfo.yellow + "T2");
        yellow = new BagTier1(ItemInfo.yellow);
        GameRegistry.registerItem(yellow, ItemInfo.yellow);
        GameRegistry.registerItem(yellowT2, ItemInfo.yellow + "T2");
        GameRegistry.addRecipe(new ItemStack(ModItems.yellow), "sws", "wcw", "sws", 's', Items.string, 'w', new ItemStack(Blocks.wool, 1, 4), 'c', Blocks.chest);
        GameRegistry.addRecipe(new BagRecipe(new ItemStack(ModItems.yellowT2),new ItemStack(ModItems.yellow),new ItemStack(Items.iron_ingot)));

        lightBlueT2 = new BagTier2(ItemInfo.lightBlue + "T2");
        lightBlue = new BagTier1(ItemInfo.lightBlue);
        GameRegistry.registerItem(lightBlue, ItemInfo.lightBlue);
        GameRegistry.registerItem(lightBlueT2, ItemInfo.lightBlue + "T2");
        GameRegistry.addRecipe(new ItemStack(ModItems.lightBlue), "sws", "wcw", "sws", 's', Items.string, 'w', new ItemStack(Blocks.wool, 1, 3), 'c', Blocks.chest);
        GameRegistry.addRecipe(new BagRecipe(new ItemStack(ModItems.lightBlueT2),new ItemStack(ModItems.lightBlue),new ItemStack(Items.iron_ingot)));

        magentaT2 = new BagTier2(ItemInfo.magenta + "T2");
        magenta = new BagTier1(ItemInfo.magenta);
        GameRegistry.registerItem(magenta, ItemInfo.magenta);
        GameRegistry.registerItem(magentaT2, ItemInfo.magenta + "T2");
        GameRegistry.addRecipe(new ItemStack(ModItems.magenta), "sws", "wcw", "sws", 's', Items.string, 'w', new ItemStack(Blocks.wool, 1, 2), 'c', Blocks.chest);
        GameRegistry.addRecipe(new BagRecipe(new ItemStack(ModItems.magentaT2),new ItemStack(ModItems.magenta),new ItemStack(Items.iron_ingot)));

        orangeT2 = new BagTier2(ItemInfo.orange + "T2");
        orange = new BagTier1(ItemInfo.orange);
        GameRegistry.registerItem(orange, ItemInfo.orange);
        GameRegistry.registerItem(orangeT2, ItemInfo.orange + "T2");
        GameRegistry.addRecipe(new ItemStack(ModItems.orange), "sws", "wcw", "sws", 's', Items.string, 'w', new ItemStack(Blocks.wool, 1, 1), 'c', Blocks.chest);
        GameRegistry.addRecipe(new BagRecipe(new ItemStack(ModItems.orangeT2),new ItemStack(ModItems.orange),new ItemStack(Items.iron_ingot)));

        whiteT2 = new BagTier2(ItemInfo.white + "T2");
        white = new BagTier1(ItemInfo.white);
        GameRegistry.registerItem(white, ItemInfo.white);
        GameRegistry.registerItem(whiteT2, ItemInfo.white + "T2");
        GameRegistry.addRecipe(new ItemStack(ModItems.white), "sws", "wcw", "sws", 's', Items.string, 'w', new ItemStack(Blocks.wool, 1, 0), 'c', Blocks.chest);
        GameRegistry.addRecipe(new BagRecipe(new ItemStack(ModItems.whiteT2),new ItemStack(ModItems.white),new ItemStack(Items.iron_ingot)));

        foid = new BagVoid(ItemInfo.foid);
        GameRegistry.registerItem(foid, ItemInfo.foid);
        GameRegistry.addRecipe(new ItemStack(ModItems.foid), "sws", "wcw", "sws", 's', Items.string, 'w', Blocks.wool, 'c', Items.ender_pearl);

    }
}
