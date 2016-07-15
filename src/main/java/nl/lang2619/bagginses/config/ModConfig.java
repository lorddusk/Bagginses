package nl.lang2619.bagginses.config;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Created by Tim on 8/24/2014.
 */
public class ModConfig {

    public static String black,
            red,
            green,
            brown,
            blue,
            purple,
            cyan,
            silver,
            gray,
            pink,
            lime,
            yellow,
            lightBlue,
            magenta,
            orange,
            white;
    public static boolean whitelist;
    public static boolean bagPickUp, hotbarBagPickUp, soulbound, analytics;
    public static final String CATEGORY_WHITELIST = "whitelist";
    public static final String CATEGORY_MISC = "miscellaneous config options";

    public static void init(File file) {
        Configuration config = new Configuration(file);
        config.load();
        config.setCategoryComment(CATEGORY_WHITELIST, "");
        whitelist = config.get("whitelist", CATEGORY_WHITELIST, true, "If you want to blacklist items instead of whitelist, change this config to false").getBoolean();

        config.setCategoryComment(CATEGORY_WHITELIST, "Input here all your whitelists per bag. \nIf empty, bag won't be added to the world.\nUse modid:* to whitelist the whole mod.\nFor example:\nminecraft:wool/2 will add Magenta wool to the whitelist. \nminecraft:wool will add every wool type. \nminecraft:wool/0+1+2 will add damage value 0,1 and 2.\nAdd multiple items by using a comma between items.");
        black = config.get(CATEGORY_WHITELIST, "Whitelist Items/Blocks for black bag", "").getString();
        red = config.get(CATEGORY_WHITELIST, "Whitelist Items/Blocks for red bag", "").getString();
        green = config.get(CATEGORY_WHITELIST, "Whitelist Items/Blocks for green bag", "").getString();
        brown = config.get(CATEGORY_WHITELIST, "Whitelist Items/Blocks for brown bag", "").getString();
        blue = config.get(CATEGORY_WHITELIST, "Whitelist Items/Blocks for blue bag", "").getString();
        purple = config.get(CATEGORY_WHITELIST, "Whitelist Items/Blocks for purple bag", "").getString();
        cyan = config.get(CATEGORY_WHITELIST, "Whitelist Items/Blocks for cyan bag", "").getString();
        silver = config.get(CATEGORY_WHITELIST, "Whitelist Items/Blocks for silver bag", "").getString();
        gray = config.get(CATEGORY_WHITELIST, "Whitelist Items/Blocks for gray bag", "").getString();
        pink = config.get(CATEGORY_WHITELIST, "Whitelist Items/Blocks for pink bag", "").getString();
        lime = config.get(CATEGORY_WHITELIST, "Whitelist Items/Blocks for lime bag", "").getString();
        yellow = config.get(CATEGORY_WHITELIST, "Whitelist Items/Blocks for yellow bag", "").getString();
        lightBlue = config.get(CATEGORY_WHITELIST, "Whitelist Items/Blocks for lightBlue bag", "").getString();
        magenta = config.get(CATEGORY_WHITELIST, "Whitelist Items/Blocks for magenta bag", "").getString();
        orange = config.get(CATEGORY_WHITELIST, "Whitelist Items/Blocks for orange bag", "").getString();
        white = config.get(CATEGORY_WHITELIST, "Whitelist Items/Blocks for white bag", "").getString();

        config.setCategoryComment(CATEGORY_MISC, "Miscellaneous config options related to the mod");
        bagPickUp = config.get(CATEGORY_MISC, "Should bags be able to pick up items when dropped", true).getBoolean();
        hotbarBagPickUp = config.get(CATEGORY_MISC, "Should bag pickup be enabled for bags in your hotbar?", true).getBoolean();
        soulbound = config.get(CATEGORY_MISC, "Should soulbound bags be enabled?", true).getBoolean();
        analytics = config.get(CATEGORY_MISC, "Do you consent to anonymous usage tracking?", true, "Visit https://github.com/lorddusk/Bagginses/wiki/Analytics for more info").getBoolean();
        if (config.hasChanged())
            config.save();
    }
}
