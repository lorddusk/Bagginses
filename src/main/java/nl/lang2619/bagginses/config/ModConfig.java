package nl.lang2619.bagginses.config;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Created by Tim on 8/24/2014.
 */
public class ModConfig {

    public static void init(File file){
        Configuration config = new Configuration(file);
        config.load();

        config.save();
    }
}
