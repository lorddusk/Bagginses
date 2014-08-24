package nl.lang2619.bagginses.config;

import java.io.File;

/**
 * Created by Tim on 8/24/2014.
 */
public class ConfigHandler {
    public static void init(String path) {
        ModConfig.init(new File(path + "bagginses.cfg"));
    }
}
