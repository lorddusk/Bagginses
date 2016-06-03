package nl.lang2619.bagginses.items;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.RecipeSorter;
import nl.lang2619.bagginses.config.ModConfig;
import nl.lang2619.bagginses.items.bags.Bags;
import nl.lang2619.bagginses.references.BagTypes;
import nl.lang2619.bagginses.references.Defaults;

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
    public static Item ender;
    public static Item upgrade;
    public static Item[] tier1 = new Item[16];
    public static String[] BagColors = new String[16];
    public static Item[] tier2 = new Item[16];
    static int added = 0;

    public static String[] bagInfo = new String[18];

    public static void init() {
        fillTiers();

        upgrade = new Upgrade();
            registerItem(upgrade, ItemInfo.upgrade);
            GameRegistry.addShapedRecipe(new ItemStack(ModItems.upgrade), "SSS", "III", "WWW", 'S', Items.STRING, 'I', Items.IRON_INGOT, 'W', Blocks.PLANKS);


        if (added > 0) {
            for (int i = 0; i < added; i++) {
                tier1[i] = new Bags(BagColors[i], BagTypes.TIER1);
                registerItem(tier1[i], BagColors[i]);
                String color = BagColors[i];
                GameRegistry.addShapedRecipe(new ItemStack(ModItems.tier1[i]), "sws", "wcw", "sws", 's', Items.STRING, 'w', new ItemStack(Blocks.WOOL, 1, getWoolForColor(color)), 'c', Blocks.CHEST);
            }

            for (int i = 0; i < added; i++) {
                tier2[i] = new Bags(BagColors[i], BagTypes.TIER2);
                registerItem(tier2[i], BagColors[i] + "T2");
                GameRegistry.addRecipe(new SoulBoundBagRecipe(new ItemStack(tier1[i])));
                RecipeSorter.register(Defaults.MODID + ":soulbound" + BagColors[i] + "tier1bag", SoulBoundBagRecipe.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");
                GameRegistry.addRecipe(new SoulBoundBagRecipe(new ItemStack(tier2[i])));
                RecipeSorter.register(Defaults.MODID + ":soulbound" + BagColors[i] + "tier2bag", SoulBoundBagRecipe.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");

            }
        }

        foid = new Bags(ItemInfo.foid, BagTypes.VOID);
        registerItem(foid, ItemInfo.foid);
        GameRegistry.addRecipe(new ItemStack(ModItems.foid), "sws", "wcw", "sws", 's', Items.STRING, 'w', Blocks.WOOL, 'c', Items.ENDER_PEARL);
        GameRegistry.addRecipe(new SoulBoundBagRecipe(new ItemStack(foid)));
        RecipeSorter.register(Defaults.MODID + ":soulboundvoidbag", SoulBoundBagRecipe.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");

        ender = new Bags(ItemInfo.ender, BagTypes.ENDER);
        registerItem(ender, ItemInfo.ender);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.ender), ModItems.upgrade, Blocks.ENDER_CHEST);
        GameRegistry.addRecipe(new SoulBoundBagRecipe(new ItemStack(ender)));
        RecipeSorter.register(Defaults.MODID + ":soulboundenderbag", SoulBoundBagRecipe.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");

        getDescriptions();
    }

    static void registerItem(Item item, String name) {
        GameRegistry.register(item, new ResourceLocation(Defaults.MODID + ":" + name));
    }

    public static void registerModels() {
        ModelLoader.registerItemVariants(foid);
        ModelLoader.setCustomModelResourceLocation(foid, 0, new ModelResourceLocation(Defaults.MODID + ":" + ItemInfo.foid, "inventory"));
        ModelLoader.registerItemVariants(upgrade);
        ModelLoader.setCustomModelResourceLocation(upgrade, 0, new ModelResourceLocation(Defaults.MODID + ":" + ItemInfo.upgrade, "inventory"));
        ModelLoader.registerItemVariants(ender);
        ModelLoader.setCustomModelResourceLocation(ender, 0, new ModelResourceLocation(Defaults.MODID + ":" + ItemInfo.ender, "inventory"));
        if (added > 0) {
            for (int i = 0; i < added; i++) {
                ModelLoader.registerItemVariants(tier1[i]);
                ModelLoader.setCustomModelResourceLocation(tier1[i], 0, new ModelResourceLocation(Defaults.MODID + ":" + BagColors[i], "inventory"));
                ModelLoader.registerItemVariants(tier2[i]);
                ModelLoader.setCustomModelResourceLocation(tier2[i], 0, new ModelResourceLocation(Defaults.MODID + ":" + BagColors[i], "inventory"));
            }
        }
    }

    public static int getWoolForColor(String color) {
        int wool = 0;
        if (color.equalsIgnoreCase("black")) {
            wool = 15;
        }
        if (color.equalsIgnoreCase("red")) {
            wool = 14;
        }
        if (color.equalsIgnoreCase("brown")) {
            wool = 12;
        }
        if (color.equalsIgnoreCase("blue")) {
            wool = 11;
        }
        if (color.equalsIgnoreCase("cyan")) {
            wool = 9;
        }
        if (color.equalsIgnoreCase("gray")) {
            wool = 7;
        }
        if (color.equalsIgnoreCase("green")) {
            wool = 13;
        }
        if (color.equalsIgnoreCase("lightBlue")) {
            wool = 3;
        }
        if (color.equalsIgnoreCase("lime")) {
            wool = 5;
        }
        if (color.equalsIgnoreCase("magenta")) {
            wool = 2;
        }
        if (color.equalsIgnoreCase("orange")) {
            wool = 1;
        }
        if (color.equalsIgnoreCase("pink")) {
            wool = 6;
        }
        if (color.equalsIgnoreCase("purple")) {
            wool = 10;
        }
        if (color.equalsIgnoreCase("silver")) {
            wool = 8;
        }
        if (color.equalsIgnoreCase("white")) {
            wool = 0;
        }
        if (color.equalsIgnoreCase("yellow")) {
            wool = 4;
        }


        if (color.equalsIgnoreCase("ender")) {
            wool = 16;
        }
        if (color.equalsIgnoreCase("void")) {
            wool = 17;
        }
        return wool;
    }

    private static void newBag(Item t1, Item t2, String color) {
        tier1[added] = t1;
        tier2[added] = t2;
        BagColors[added] = color;
        added++;
    }

    static void getDescriptions() {
        if (!ModConfig.blackInfo.isEmpty()) {
            bagInfo[15] = ModConfig.blackInfo;
        }
        if (!ModConfig.redInfo.isEmpty()) {
            bagInfo[14] = ModConfig.redInfo;
        }
        if (!ModConfig.brownInfo.isEmpty()) {
            bagInfo[12] = ModConfig.brownInfo;
        }
        if (!ModConfig.blueInfo.isEmpty()) {
            bagInfo[11] = ModConfig.blueInfo;
        }
        if (!ModConfig.cyanInfo.isEmpty()) {
            bagInfo[9] = ModConfig.cyanInfo;
        }
        if (!ModConfig.grayInfo.isEmpty()) {
            bagInfo[7] = ModConfig.grayInfo;
        }
        if (!ModConfig.greenInfo.isEmpty()) {
            bagInfo[13] = ModConfig.greenInfo;
        }
        if (!ModConfig.lightBlueInfo.isEmpty()) {
            bagInfo[3] = ModConfig.lightBlueInfo;
        }
        if (!ModConfig.limeInfo.isEmpty()) {
            bagInfo[5] = ModConfig.limeInfo;
        }
        if (!ModConfig.magentaInfo.isEmpty()) {
            bagInfo[2] = ModConfig.magentaInfo;
        }
        if (!ModConfig.orangeInfo.isEmpty()) {
            bagInfo[1] = ModConfig.orangeInfo;
        }
        if (!ModConfig.pinkInfo.isEmpty()) {
            bagInfo[6] = ModConfig.pinkInfo;
        }
        if (!ModConfig.purpleInfo.isEmpty()) {
            bagInfo[10] = ModConfig.purpleInfo;
        }
        if (!ModConfig.silverInfo.isEmpty()) {
            bagInfo[8] = ModConfig.silverInfo;
        }
        if (!ModConfig.whiteInfo.isEmpty()) {
            bagInfo[0] = ModConfig.whiteInfo;
        }
        if (!ModConfig.yellowInfo.isEmpty()) {
            bagInfo[4] = ModConfig.yellowInfo;
        }
        if(!ModConfig.enderInfo.isEmpty()) {
            bagInfo[16] = ModConfig.enderInfo;
        }
        if(!ModConfig.voidInfo.isEmpty()) {
            bagInfo[17] = ModConfig.voidInfo;
        }

    }

    static void fillTiers() {
        if (ModConfig.whitelist) {
            if (!ModConfig.black.isEmpty()) {
                newBag(black, blackT2, "black");
            }
            if (!ModConfig.red.isEmpty()) {
                newBag(red, redT2, "red");
            }
            if (!ModConfig.brown.isEmpty()) {
                newBag(brown, brownT2, "brown");
            }
            if (!ModConfig.blue.isEmpty()) {
                newBag(blue, blueT2, "blue");
            }
            if (!ModConfig.cyan.isEmpty()) {
                newBag(cyan, cyanT2, "cyan");
            }
            if (!ModConfig.gray.isEmpty()) {
                newBag(gray, grayT2, "gray");
            }
            if (!ModConfig.green.isEmpty()) {
                newBag(green, greenT2, "green");
            }
            if (!ModConfig.lightBlue.isEmpty()) {
                newBag(lightBlue, lightBlueT2, "lightBlue");
            }
            if (!ModConfig.lime.isEmpty()) {
                newBag(lime, limeT2, "lime");
            }
            if (!ModConfig.magenta.isEmpty()) {
                newBag(magenta, magentaT2, "magenta");
            }
            if (!ModConfig.orange.isEmpty()) {
                newBag(orange, orangeT2, "orange");
            }
            if (!ModConfig.pink.isEmpty()) {
                newBag(pink, pinkT2, "pink");
            }
            if (!ModConfig.purple.isEmpty()) {
                newBag(purple, purpleT2, "purple");
            }
            if (!ModConfig.silver.isEmpty()) {
                newBag(silver, silverT2, "silver");
            }
            if (!ModConfig.white.isEmpty()) {
                newBag(white, whiteT2, "white");
            }
            if (!ModConfig.yellow.isEmpty()) {
                newBag(yellow, yellowT2, "yellow");
            }
        } else {
            newBag(black, blackT2, "black");
            newBag(red, redT2, "red");
            newBag(brown, brownT2, "brown");
            newBag(blue, blueT2, "blue");
            newBag(cyan, cyanT2, "cyan");
            newBag(gray, grayT2, "gray");
            newBag(green, greenT2, "green");
            newBag(lightBlue, lightBlueT2, "lightBlue");
            newBag(lime, limeT2, "lime");
            newBag(magenta, magentaT2, "magenta");
            newBag(orange, orangeT2, "orange");
            newBag(pink, pinkT2, "pink");
            newBag(purple, purpleT2, "purple");
            newBag(silver, silverT2, "silver");
            newBag(white, whiteT2, "white");
            newBag(yellow, yellowT2, "yellow");
        }
    }
}
