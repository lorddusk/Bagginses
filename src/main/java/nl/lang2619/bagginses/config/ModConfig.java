package nl.lang2619.bagginses.config;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Created by Tim on 8/24/2014.
 */
public class ModConfig {

    public static String black, red, green, brown, blue, purple, cyan, silver, gray, pink, lime, yellow, lightBlue, magenta, orange, white;

    public static void init(File file){
        Configuration config = new Configuration(file);
        config.load();
        config.setCategoryComment(Configuration.CATEGORY_GENERAL, "Input here all your whitelists per bag. \nIf empty, bag won't be added to the world.\nUse modid:* to whitelist the whole mod.\nFor example:\nminecraft:wool/2 will add Magenta wool to the whitelist. \nminecraft:wool will add every wool type. \nminecraft:wool/0+1+2 will add damage value 0,1 and 2.\nAdd multiple items by using a comma between items.");
        black = config.get(Configuration.CATEGORY_GENERAL,"Whitelist Items/Blocks for black bag","").getString();
        red = config.get(Configuration.CATEGORY_GENERAL,"Whitelist Items/Blocks for red bag","").getString();
        green = config.get(Configuration.CATEGORY_GENERAL,"Whitelist Items/Blocks for green bag","").getString();
        brown = config.get(Configuration.CATEGORY_GENERAL,"Whitelist Items/Blocks for brown bag","").getString();
        blue = config.get(Configuration.CATEGORY_GENERAL,"Whitelist Items/Blocks for blue bag","").getString();
        purple = config.get(Configuration.CATEGORY_GENERAL,"Whitelist Items/Blocks for purple bag","").getString();
        cyan = config.get(Configuration.CATEGORY_GENERAL,"Whitelist Items/Blocks for cyan bag","").getString();
        silver = config.get(Configuration.CATEGORY_GENERAL,"Whitelist Items/Blocks for silver bag","").getString();
        gray = config.get(Configuration.CATEGORY_GENERAL,"Whitelist Items/Blocks for gray bag","").getString();
        pink = config.get(Configuration.CATEGORY_GENERAL,"Whitelist Items/Blocks for pink bag","").getString();
        lime = config.get(Configuration.CATEGORY_GENERAL,"Whitelist Items/Blocks for lime bag","").getString();
        yellow = config.get(Configuration.CATEGORY_GENERAL,"Whitelist Items/Blocks for yellow bag","").getString();
        lightBlue = config.get(Configuration.CATEGORY_GENERAL,"Whitelist Items/Blocks for lightBlue bag","").getString();
        magenta = config.get(Configuration.CATEGORY_GENERAL,"Whitelist Items/Blocks for magenta bag","").getString();
        orange = config.get(Configuration.CATEGORY_GENERAL,"Whitelist Items/Blocks for orange bag","").getString();
        white = config.get(Configuration.CATEGORY_GENERAL,"Whitelist Items/Blocks for white bag","").getString();
        config.save();
    }
}
