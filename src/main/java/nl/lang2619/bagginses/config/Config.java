package nl.lang2619.bagginses.config;

import net.minecraftforge.common.config.Configuration;
import nl.lang2619.bagginses.ModInfo;

import java.io.File;

/**
 * Created by Alex on 29/01/2017.
 */
public class Config {

    private static Configuration config;

    public static File dir;

    public static boolean soulbound;

    public static void init(String path) {
        dir = new File(path + File.separator + ModInfo.MODID);

        dir.mkdirs();

        config = new Configuration(new File(dir + "/" + ModInfo.MODID + ".cfg"));
        config.load();

        soulbound = config.get(Configuration.CATEGORY_GENERAL, "Should soulbound bags be enabled?", true).getBoolean();

        if (config.hasChanged())
            config.save();

    }
}
