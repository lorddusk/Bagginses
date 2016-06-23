package nl.lang2619.bagginses.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import nl.lang2619.bagginses.Bagginses;
import nl.lang2619.bagginses.config.ModConfig;
import nl.lang2619.bagginses.items.ModItems;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.logging.Logger;

/**
 * Created by alex on 22/06/16.
 */
public class BagDescriptions {

    //public static Map<String, String> desc = new HashMap<String, String>();

    public static ArrayList<BagInfo> descriptions;

    public static void init() {
        File file = new File(Bagginses.path + "descriptions.json");
        if (!file.exists()) {
            URL inputUrl = Bagginses.class.getResource("/descriptions.json");
            try {
                System.out.println("Creating config file");
                file.createNewFile();
                FileUtils.copyURLToFile(inputUrl, file);
            }
            catch (IOException e) {
            }
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            Gson gson = new GsonBuilder().create();
            descriptions = new ArrayList<BagInfo>(Arrays.asList(gson.fromJson(reader, BagInfo[].class)));
            reader.close();
        }
        catch (IOException e) {
            //TODO
        }


        for (BagInfo desc : descriptions) {
            System.out.println(desc.getColor());
            ModItems.bags.get(desc.getColor()).setDesc(desc.getDescription());
        }
    }
}
