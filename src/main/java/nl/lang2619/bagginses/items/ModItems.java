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
import nl.lang2619.bagginses.helpers.BagDescriptions;
import nl.lang2619.bagginses.helpers.Bags;
import nl.lang2619.bagginses.items.bags.Bag;
import nl.lang2619.bagginses.references.BagTypes;
import nl.lang2619.bagginses.references.Defaults;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    //public static ArrayList<Bags> bags = new ArrayList<Bags>();
    public static Map<String, Bags> bags = new HashMap<String, Bags>();

    public static ArrayList<String> bagColors = new ArrayList<String>() {{
        add("black");
        add("red");
        add("brown");
        add("blue");
        add("cyan");
        add("gray");
        add("green");
        add("lightBlue");
        add("lime");
        add("magenta");
        add("orange");
        add("pink");
        add("purple");
        add("silver");
        add("white");
        add("yellow");
        add("ender");
        add("void");
    }};

    public static Map<String, Integer> colorNumbers = new HashMap<String, Integer>() {{
        put("black", 15);
        put("red", 14);
        put("brown", 12);
        put("blue", 11);
        put("cyan", 9);
        put("gray", 7);
        put("green", 13);
        put("lightBlue", 3);
        put("lime", 5);
        put("magenta", 2);
        put("orange", 1);
        put("pink", 6);
        put("purple", 10);
        put("silver", 8);
        put("white", 0);
        put("yellow", 4);
        put("ender", 16);
        put("void", 17);
    }};

    public static void init() {
        fillTiers();

        upgrade = new Upgrade();
        registerItem(upgrade, ItemInfo.upgrade);
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.upgrade), "SSS", "III", "WWW", 'S', Items.STRING, 'I', Items.IRON_INGOT, 'W', Blocks.PLANKS);

        RecipeSorter.register(Defaults.MODID + ":bag", BagRecipe.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");
        RecipeSorter.register(Defaults.MODID + ":soulboundbag", SoulBoundBagRecipe.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");

        for (Bags bag : bags.values()) {
            if (!bag.isRegistered())
                continue;
            bag.setTier1(new Bag(bag.getColor(), BagTypes.TIER1));
            registerItem(bag.getTier1(), bag.getColor());
            String color = bag.getColor();
            if (bag.isRegistered())
                GameRegistry.addShapedRecipe(new ItemStack(bag.getTier1()), "sws", "wcw", "sws", 's', Items.STRING, 'w', new ItemStack(Blocks.WOOL, 1, colorNumbers.get(color)), 'c', Blocks.CHEST);

        }

        for (Bags bag: bags.values()) {
            if (!bag.isRegistered())
                continue;
            bag.setTier2(new Bag(bag.getColor(), BagTypes.TIER2));
            registerItem(bag.getTier2(), bag.getColor() + "T2");
            GameRegistry.addRecipe(new BagRecipe(new ItemStack(bag.getTier2()), new ItemStack(bag.getTier1()), ModItems.upgrade));
            if (ModConfig.soulbound) {
                GameRegistry.addRecipe(new SoulBoundBagRecipe(new ItemStack(bag.getTier1())));
                GameRegistry.addRecipe(new SoulBoundBagRecipe(new ItemStack(bag.getTier2())));
            }
        }

        foid = new Bag(ItemInfo.foid, BagTypes.VOID);
        registerItem(foid, ItemInfo.foid);
        GameRegistry.addRecipe(new ItemStack(ModItems.foid), "sws", "wcw", "sws", 's', Items.STRING, 'w', Blocks.WOOL, 'c', Items.ENDER_PEARL);
        if(ModConfig.soulbound)
            GameRegistry.addRecipe(new SoulBoundBagRecipe(new ItemStack(foid)));
        bags.put("void", new Bags(foid, null, "void", true));

        ender = new Bag(ItemInfo.ender, BagTypes.ENDER);
        registerItem(ender, ItemInfo.ender);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.ender), ModItems.upgrade, Blocks.ENDER_CHEST);
        if(ModConfig.soulbound)
            GameRegistry.addRecipe(new SoulBoundBagRecipe(new ItemStack(ender)));
        bags.put("ender", new Bags(ender, null, "ender", true));

        BagDescriptions.init();
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
        for (Bags bag : bags.values()) {
            if (bag.getColor().equals("ender")
                    || bag.getColor().equals("void")
                    || !bag.isRegistered())
                continue;
            ModelLoader.registerItemVariants(bag.getTier1());
            ModelLoader.setCustomModelResourceLocation(bag.getTier1(), 0, new ModelResourceLocation(Defaults.MODID + ":" + bag.getColor(), "inventory"));
            ModelLoader.registerItemVariants(bag.getTier2());
            ModelLoader.setCustomModelResourceLocation(bag.getTier2(), 0, new ModelResourceLocation(Defaults.MODID + ":" + bag.getColor(), "inventory"));
        }
    }

    private static void newBag(Item t1, Item t2, String color) {
        newBag(t1, t2, color, true);
    }

    private static void newBag(Item t1, Item t2, String color, boolean register) {
        bags.put(color, new Bags(t1, t2, color, register));
        //bags.add(new Bags(t1, t2, color, register));
    }

    static void fillTiers() {
        if (ModConfig.whitelist) {
            if (!ModConfig.black.isEmpty()) {
                newBag(black, blackT2, "black");
            } else {
                newBag(black, blackT2, "black", false);
            }

            if (!ModConfig.red.isEmpty()) {
                newBag(red, redT2, "red");
            } else {
                newBag(red, redT2, "red", false);
            }

            if (!ModConfig.brown.isEmpty()) {
                newBag(brown, brownT2, "brown");
            } else {
                newBag(brown, brownT2, "brown", false);
            }

            if (!ModConfig.blue.isEmpty()) {
                newBag(blue, blueT2, "blue");
            } else {
                newBag(blue, blueT2, "blue", false);
            }

            if (!ModConfig.cyan.isEmpty()) {
                newBag(cyan, cyanT2, "cyan");
            } else {
                newBag(cyan, cyanT2, "cyan", false);
            }

            if (!ModConfig.gray.isEmpty()) {
                newBag(gray, grayT2, "gray");
            } else {
                newBag(gray, grayT2, "gray", false);
            }

            if (!ModConfig.green.isEmpty()) {
                newBag(green, greenT2, "green");
            } else {
                newBag(green, greenT2, "green", false);
            }

            if (!ModConfig.lightBlue.isEmpty()) {
                newBag(lightBlue, lightBlueT2, "lightBlue");
            } else {
                newBag(lightBlue, lightBlueT2, "lightBlue", false);
            }

            if (!ModConfig.lime.isEmpty()) {
                newBag(lime, limeT2, "lime");
            } else {
                newBag(lime, limeT2, "lime", false);
            }

            if (!ModConfig.magenta.isEmpty()) {
                newBag(magenta, magentaT2, "magenta");
            } else {
                newBag(magenta, magentaT2, "magenta", false);
            }

            if (!ModConfig.orange.isEmpty()) {
                newBag(orange, orangeT2, "orange");
            } else {
                newBag(orange, orangeT2, "orange", false);
            }

            if (!ModConfig.pink.isEmpty()) {
                newBag(pink, pinkT2, "pink");
            } else {
                newBag(pink, pinkT2, "pink", false);
            }

            if (!ModConfig.purple.isEmpty()) {
                newBag(purple, purpleT2, "purple");
            } else {
                newBag(purple, purpleT2, "purple", false);
            }

            if (!ModConfig.silver.isEmpty()) {
                newBag(silver, silverT2, "silver");
            } else {
                newBag(silver, silverT2, "silver", false);
            }

            if (!ModConfig.white.isEmpty()) {
                newBag(white, whiteT2, "white");
            } else {
                newBag(white, whiteT2, "white", false);
            }

            if (!ModConfig.yellow.isEmpty()) {
                newBag(yellow, yellowT2, "yellow");
            } else {
                newBag(yellow, yellowT2, "yellow", false);
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
