package nl.lang2619.bagginses.items;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
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
    private static Item upgrade;
    public static Item[] tier1 = new Item[16];
    public static String[] BagColors = new String[16];
    public static Item[] tier2 = new Item[16];
    static int added = 0;

    public static void init() {
        fillTiers();

        if (added > 0) {
            upgrade = new Upgrade();
            registerItem(upgrade, ItemInfo.upgrade);
            GameRegistry.addShapedRecipe(new ItemStack(ModItems.upgrade), "SSS", "III", "WWW", 'S', Items.string, 'I', Items.iron_ingot, 'W', Blocks.planks);


            for (int i = 0; i < added; i++) {
                tier1[i] = new Bags(BagColors[i], BagTypes.TIER1);
                registerItem(tier1[i], BagColors[i]);
                String color = BagColors[i];
                GameRegistry.addShapedRecipe(new ItemStack(ModItems.tier1[i]), "sws", "wcw", "sws", 's', Items.string, 'w', new ItemStack(Blocks.wool, 1, getWoolForColor(color)), 'c', Blocks.chest);
            }

            for (int i = 0; i < added; i++) {
                tier2[i] = new Bags(BagColors[i], BagTypes.TIER2);
                registerItem(tier2[i], BagColors[i] + "T2");
                GameRegistry.addRecipe(new BagRecipe(new ItemStack(tier2[i]), new ItemStack(ModItems.tier1[i]), ModItems.upgrade));
            }
        }

        foid = new Bags(ItemInfo.foid, BagTypes.VOID);
        registerItem(foid, ItemInfo.foid);
        GameRegistry.addRecipe(new ItemStack(ModItems.foid), "sws", "wcw", "sws", 's', Items.string, 'w', Blocks.wool, 'c', Items.ender_pearl);

        ender = new Bags(ItemInfo.ender, BagTypes.ENDER);
        registerItem(ender, ItemInfo.ender);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.ender), ModItems.upgrade, Blocks.ender_chest);
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

    private static int getWoolForColor(String color) {
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
        return wool;
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
